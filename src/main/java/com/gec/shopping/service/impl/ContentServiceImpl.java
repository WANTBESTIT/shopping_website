package com.gec.shopping.service.impl;

import com.gec.shopping.mapper.TbContentCategoryMapper;
import com.gec.shopping.mapper.TbContentMapper;
import com.gec.shopping.pojo.TbContent;
import com.gec.shopping.pojo.TbContentCategory;
import com.gec.shopping.pojo.TbContentExample;
import com.gec.shopping.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;
    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    // 查询所有
    @Override
    public List<TbContent> findAll() {
        return tbContentMapper.selectByExample(null);
    }

    // 根据ID查询
    @Override
    public TbContent findContentsById(@PathVariable Long id) {
        System.out.println("findContentsById ========>" + id);
        return tbContentMapper.selectByPrimaryKey(id);
    }

    // 添加
    @Override
    public void add(TbContent content) {
        tbContentMapper.insert(content);
        // 清空缓存
        redisTemplate.boundHashOps("content").delete(content.getCategoryId());
    }

    // 修改
    @Override
    public void update(TbContent content) {
        TbContent oldContent = tbContentMapper.selectByPrimaryKey(content.getId());
        //  清楚之前分类的广告缓存
        redisTemplate.boundHashOps("content").delete(oldContent.getCategoryId());

        tbContentMapper.updateByPrimaryKey(content);
        System.out.println("content 修改======" + content.getCategoryId());
//         清除缓存
        redisTemplate.boundHashOps("content").delete(content.getCategoryId());

    }

    // 删除
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            TbContent content = tbContentMapper.selectByPrimaryKey(id);
            // 清楚之前分类的广告缓存
            redisTemplate.boundHashOps("content").delete(content.getCategoryId());
            tbContentMapper.deleteByPrimaryKey(id);
        }
    }

    // 查询所有广告类别
    @Override
    public List<TbContentCategory> findAllContentCategory() {
        return contentCategoryMapper.selectByExample(null);
    }

    /**
     * 首次访问时，如果redis没有数据，就访问数据库，把数据存到redis
     * 后续访问时，直接查询redis
     * <p>
     * 哈希结构
     * Key  content
     * field            value
     * categoryid 1     List<TbContent>
     */
    @Override
    public List<TbContent> findByCategoryId(Long categoryId) {
        //redisTemplate.opsForHash(); List<String> list = (List<String>) redisTemplate.opsForHash().get("content",categoryId);
        // 加入缓存的代码:
        List<TbContent> list = (List<TbContent>) redisTemplate.boundHashOps("content").get(categoryId);

        if (list == null) {
            System.out.println("查询数据库===================");
            TbContentExample example = new TbContentExample();
            TbContentExample.Criteria criteria = example.createCriteria();
            // 有效广告:
            criteria.andStatusEqualTo("1");

            criteria.andCategoryIdEqualTo(categoryId);
            // 排序
            example.setOrderByClause("sort_order");

            list = tbContentMapper.selectByExample(example);

            redisTemplate.boundHashOps("content").put(categoryId, list);
        } else {
            System.out.println("从缓存中获取====================");
        }
        return list;
    }

}
