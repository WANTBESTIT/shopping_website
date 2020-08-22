package com.gec.shopping.service;

import com.gec.shopping.pojo.TbSeller;
import com.gec.shopping.pojo.entity.RestPage;

import java.util.List;

public interface SellerService {
    List<TbSeller> findAllSeller();

    // 根据条件查询
    List<TbSeller> findAllSeller(TbSeller tbSeller);

    public RestPage searchSeller(int pageNum, int pageSize, TbSeller tbSeller);

    public RestPage findPage(int pageNum, int pageSize);

    void add(TbSeller seller);

    void update(TbSeller seller);

    TbSeller findOne(String id);

    void delete(String[] ids);
}
