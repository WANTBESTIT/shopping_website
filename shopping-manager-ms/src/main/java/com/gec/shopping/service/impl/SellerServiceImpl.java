package com.gec.shopping.service.impl;

import com.gec.shopping.mapper.TbSellerMapper;
import com.gec.shopping.pojo.TbSeller;
import com.gec.shopping.pojo.TbSellerExample;
import com.gec.shopping.pojo.entity.RestPage;
import com.gec.shopping.service.SellerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private TbSellerMapper tbSellerMapper;

    @Override
    public List<TbSeller> findAllSeller() {
        return tbSellerMapper.selectByExample(null);
    }

    /**
     * 根据公司名称和店铺名称查询商家信息
     *
     * @param tbSeller
     * @return
     */
    @Override
    public List<TbSeller> findAllSeller(TbSeller tbSeller) {
        TbSellerExample example = new TbSellerExample();
        TbSellerExample.Criteria criteria = example.createCriteria();

        if (tbSeller.getName() != null && !"".equals(tbSeller.getName())) {
            criteria.andNameLike("%" + tbSeller.getName() + "%");
        }
        if (tbSeller.getNickName() != null && !"".equals(tbSeller.getNickName())) {
            criteria.andNickNameLike("%" + tbSeller.getNickName() + "%");
        }
        List<TbSeller> sellerList = tbSellerMapper.selectByExample(example);
        return sellerList;
    }

    @Override
    public RestPage searchSeller(int pageNum, int pageSize, TbSeller tbSeller) {
        PageHelper.startPage(pageNum, pageSize);

        TbSellerExample example = new TbSellerExample();
        TbSellerExample.Criteria criteria = example.createCriteria();

        //criteria.andIsDeleteIsNull();

        if (tbSeller != null) {
            if (tbSeller.getName() != null && !"".equals(tbSeller.getName())) {
                criteria.andNameLike("%" + tbSeller.getName() + "%");
            }
            if (tbSeller.getNickName() != null && !"".equals(tbSeller.getNickName())) {
                criteria.andNickNameLike("%" + tbSeller.getNickName() + "%");
            }
        }
        Page<TbSeller> page= (Page<TbSeller>)tbSellerMapper.selectByExample(example);
        System.out.println("##### "+page.getResult());
        System.out.println(page.getTotal());
        return new RestPage(page.getTotal(), page.getResult());
    }

    @Override
    public RestPage findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbSeller> page= (Page<TbSeller>) tbSellerMapper.selectByExample(null);
        return new RestPage(page.getTotal(), page.getResult());
    }

    @Override
    public void add(TbSeller seller) {

    }

    @Override
    public void update(TbSeller seller) {

    }

    @Override
    public TbSeller findOne(String id) {
        return null;
    }

    @Override
    public void delete(String[] ids) {

    }
}
