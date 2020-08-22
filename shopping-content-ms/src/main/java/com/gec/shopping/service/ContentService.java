package com.gec.shopping.service;

import com.gec.shopping.pojo.TbContent;
import com.gec.shopping.pojo.TbContentCategory;

import java.util.List;

public interface ContentService {
    // 查询所有商品品牌
    public List<TbContent> findAll();

    public List<TbContent> findByCategoryId(Long categoryId);
    // 根据Id查询广告
    public TbContent findContentsById(Long id);

    public void add(TbContent content);

    public void update(TbContent content);

    public void delete(Long[] ids);

    public List<TbContentCategory> findAllContentCategory();
}
