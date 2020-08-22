package com.gec.shopping.controller;

import com.gec.shopping.pojo.TbContent;
import com.gec.shopping.pojo.TbContentCategory;
import com.gec.shopping.pojo.entity.RestBean;
import com.gec.shopping.pojo.entity.RestPage;
import com.gec.shopping.service.ContentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content-ms")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/findAll")
    public List<TbContent> findAll() {
        List<TbContent> list = contentService.findAll();
        System.out.println("查询所有广告(Controller)" + list);
        return list;
    }

    @RequestMapping("/findOne/{id}")
    public TbContent findOne(@PathVariable Long id) {
        return contentService.findContentsById(id);
    }

    /**
     * 查询所有广告的类别
     *
     * @return
     */
    @RequestMapping("/findAllCategory")
    public List<TbContentCategory> findAllContentCategory() {
        List<TbContentCategory> list = contentService.findAllContentCategory();
        System.out.println("查询所有广告类别(Controller)" + list);
        return list;
    }

    @RequestMapping("/findByCategoryId")
    public List<TbContent> findByCategoryId(Long categoryId) {
        System.out.println("findByCategoryId：------》" + categoryId);
        return contentService.findByCategoryId(categoryId);
    }

    @GetMapping("/findByPage")
    public RestPage findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbContent> pages = (Page<TbContent>) contentService.findAll();
        RestPage restPage = new RestPage();
        restPage.setRows(pages.getResult());
        restPage.setTotal(pages.getTotal());
        return restPage;
    }

    @PostMapping("/add")
    public RestBean add(@RequestBody TbContent content) {
        try {
            System.out.println("addTbContent  id:" + content.getId());
            contentService.add(content);
            return new RestBean(true, "新建成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RestBean(false, "新建失败");
        }
    }

    @PostMapping("/update")
    public RestBean update(@RequestBody TbContent content) {
        try {
            System.out.println("updateTbContent  id:" + content.getId());
            contentService.update(content);
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
            contentService.delete(ids);
            return new RestBean(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RestBean(false, "删除失败");
        }
    }
}