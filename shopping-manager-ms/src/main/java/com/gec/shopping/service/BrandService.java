package com.gec.shopping.service;

import com.gec.shopping.pojo.TbBrand;

import java.util.List;
import java.util.Map;

/**
 * 商品品牌业务块
 */
public interface BrandService {

    // 查询所有商品品牌
    public List<TbBrand> findAllBrand();

    // 根据Id查询商品品牌
    public TbBrand findBrandsById(Long id);

    public void addBrand(TbBrand tbBrand);

    public void updateBrand(TbBrand tbBrand);

    public void deleteBrand(Long[] ids);

    public List<Map> selectOptionList();
}
