package com.gec.shopping.service.impl;

import com.gec.shopping.mapper.TbContentCategoryMapper;
import com.gec.shopping.pojo.TbContentCategory;
import com.gec.shopping.pojo.TbContentCategoryExample;
import com.gec.shopping.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public TbContentCategory findCategoryById(Long id) {
        System.out.println("findCategoryById ========>" + id);
        return contentCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TbContentCategory> findAll() {
        return contentCategoryMapper.selectByExample(null);
    }

    @Override
    public void add(TbContentCategory category) {
        contentCategoryMapper.insert(category);
    }

    @Override
    public void update(TbContentCategory category) {
        contentCategoryMapper.updateByPrimaryKey(category);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            contentCategoryMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public List<TbContentCategory> queryCategory(TbContentCategory contentCategory) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        if (contentCategory.getName() != null && !"".equals(contentCategory.getName())) {
            criteria.andNameLike("%" + contentCategory.getName() + "%");
        }
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        return list;
    }
}
