package com.gec.shopping.controller;

import com.gec.shopping.pojo.TbContentCategory;
import com.gec.shopping.pojo.entity.RestBean;
import com.gec.shopping.pojo.entity.RestPage;
import com.gec.shopping.service.ContentCategoryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content-caegory-ms")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/findAll")
    public List<TbContentCategory> findAll() {
        List<TbContentCategory> list = contentCategoryService.findAll();
        System.out.println("查询所有广告类别(Controller)" + list);
        return list;
    }

    @RequestMapping("/findOne/{id}")
    public TbContentCategory findOne(@PathVariable Long id) {
        return contentCategoryService.findCategoryById(id);
    }

    @GetMapping("/findByPage")
    public RestPage findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbContentCategory> pages = (Page<TbContentCategory>) contentCategoryService.findAll();
        RestPage restPage = new RestPage();
        restPage.setRows(pages.getResult());
        restPage.setTotal(pages.getTotal());
        return restPage;
    }

    // 查找分类模块名称
    @PostMapping("/query")
    public List<TbContentCategory> queryTemplate(@RequestBody TbContentCategory contentCategory) {
        List<TbContentCategory> list = contentCategoryService.queryCategory(contentCategory);
        System.out.println("queryTemplate name:" + list.get(0).getName());
        return list;
    }

    @PostMapping("/add")
    public RestBean add(@RequestBody TbContentCategory content) {
        try {
            System.out.println("addTbContentCategory  id:" + content.getId());
            contentCategoryService.add(content);
            return new RestBean(true, "新建成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RestBean(false, "新建失败");
        }
    }

    @PostMapping("/update")
    public RestBean update(@RequestBody TbContentCategory content) {
        try {
            System.out.println("updateTbContent  id:" + content.getId());
            contentCategoryService.update(content);
            return new RestBean(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RestBean(false, "修改失败");
        }
    }

    @GetMapping("/delete")
    public RestBean deleteContent(Long[] ids) {
        try {
            System.out.println("ids[0]======" + ids[0]);
            contentCategoryService.delete(ids);
            return new RestBean(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RestBean(false, "删除失败");
        }
    }
}
