package com.gec.shopping.service;

import com.gec.shopping.pojo.TbContentCategory;
import com.gec.shopping.pojo.entity.ContentCategory;

import java.util.List;

public interface ContentCategoryService {
    TbContentCategory findCategoryById(Long id);

    List<TbContentCategory> findAll();

    void add(TbContentCategory category);

    void update(TbContentCategory category);

    void delete(Long[] ids);

    List<TbContentCategory> queryCategory(TbContentCategory contentCategory);
}
