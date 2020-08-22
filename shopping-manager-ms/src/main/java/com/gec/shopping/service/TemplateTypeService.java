package com.gec.shopping.service;

import com.gec.shopping.pojo.TbTypeTemplate;
import com.gec.shopping.pojo.entity.RestPage;

import java.util.List;
import java.util.Map;

public interface TemplateTypeService {

    public List<TbTypeTemplate> findAllTemplate();

    public TbTypeTemplate findTemplateById(Long id);

    public void addTemplate(TbTypeTemplate tbTypeTemplate);

    public void updateTemplate(TbTypeTemplate tbTypeTemplate);

    public List<Map> findSpecList(Long id);

    public void deleteTemplate(Long[] ids);

    // 分页查询
    public RestPage search(TbTypeTemplate typeTemplate, int pageNum, int pageSize);

    public RestPage findPage(int pageNum,int pageSize);
}
