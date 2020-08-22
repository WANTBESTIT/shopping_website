package com.gec.shopping.service;

import com.gec.shopping.pojo.TbSpecification;
import com.gec.shopping.pojo.entity.RestPage;
import com.gec.shopping.pojo.entity.Specification;

import java.util.List;
import java.util.Map;

public interface SpecificationService {

    public List<TbSpecification> findAllSpecification();

    public Specification findSpecificationById(Long id);

    public void addSpecification(Specification specification);

    public void updateSpecification(Specification specification);

    public void deleteSpecification(Long[] ids);

    public List<Map> selectOptionList();

    public RestPage findPage(int pageNum, int pageSize);

    public RestPage search(int pageNum, int pageSize, TbSpecification tbSpecification);
}
