package com.gec.shopping.service.impl;

import com.gec.shopping.service.BrandService;
import com.gec.shopping.mapper.TbBrandMapper;
import com.gec.shopping.pojo.TbBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private TbBrandMapper tbBrandMapper;

    @Override
    public List<TbBrand> findAllBrand() {
        List<TbBrand> list = tbBrandMapper.selectByExample(null);
        System.out.println("查询所有品牌(Service):" + list);
        return list;
    }

    @Override
    public TbBrand findBrandsById(Long id) {
        return tbBrandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addBrand(TbBrand tbBrand) {
        tbBrandMapper.insert(tbBrand);
    }

      @Override
    public void updateBrand(TbBrand tbBrand) {
        tbBrandMapper.updateByPrimaryKey(tbBrand);
    }

    @Override
    public void deleteBrand(Long[] ids) {
        for (Long id : ids) {
            tbBrandMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public List<Map> selectOptionList() {
        return tbBrandMapper.selectOptionList();
    }

}
