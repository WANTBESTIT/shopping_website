package com.gec.shopping.controller;

import com.gec.shopping.pojo.TbTypeTemplate;
import com.gec.shopping.pojo.entity.RestBean;
import com.gec.shopping.pojo.entity.RestPage;
import com.gec.shopping.service.TemplateTypeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/typeTemplate-Ms")
public class TemplateTypeController {

    @Autowired
    private TemplateTypeService templateTypeService;

    @GetMapping("/findAll")
    public List<TbTypeTemplate> findAllTemplate() {
        List<TbTypeTemplate> list = templateTypeService.findAllTemplate();
        System.out.println("findAllTemplaye name:" + list.get(0).getName());
        return list;
    }

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public RestPage findPage(int pageNum, int pageSize) {
        return templateTypeService.findPage(pageNum, pageSize);
    }

    /**
     * /**
     * 查询+分页
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/search")
    public RestPage search(@RequestBody TbTypeTemplate typeTemplate, int pageNum, int pageSize) {
        return templateTypeService.search(typeTemplate, pageNum, pageSize);
    }

    // 根据id查询模板
    @GetMapping("/findOne")
    public TbTypeTemplate findTemplateDetail(Long id) {
        System.out.println("findTemplateDetail.id:" + id);
        return templateTypeService.findTemplateById(id);
    }

    // 分页
    @GetMapping("/findPage")
    public RestPage findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbTypeTemplate> pages = (Page<TbTypeTemplate>) templateTypeService.findAllTemplate();
        RestPage restPage = new RestPage();
        restPage.setRows(pages.getResult());
        restPage.setTotal(pages.getTotal());
        return restPage;
    }

    // 添加
    @PostMapping("/add")
    public RestBean addTemplate(@RequestBody TbTypeTemplate tbTypeTemplate) {
        try {
            System.out.println("添加的规格：" + tbTypeTemplate.getName());
            templateTypeService.addTemplate(tbTypeTemplate);
            return new RestBean(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RestBean(false, "添加失败");
        }
    }

    // 修改
    @PostMapping("/update")
    public RestBean updateemplate(@RequestBody TbTypeTemplate tbTypeTemplate) {
        try {
            System.out.println("修改的规格：" + tbTypeTemplate.getName());
            templateTypeService.updateTemplate(tbTypeTemplate);
            return new RestBean(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RestBean(false, "修改失败");
        }
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public RestBean delete(Long[] ids) {
        try {
            templateTypeService.deleteTemplate(ids);
            return new RestBean(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RestBean(false, "删除失败");
        }
    }

    // 获取规格信息
    @GetMapping("/findBySpecList")
    public List<Map> findSpecList(Long id) {
        System.out.println("商品模板id" + id);
        return templateTypeService.findSpecList(id);
    }
}
