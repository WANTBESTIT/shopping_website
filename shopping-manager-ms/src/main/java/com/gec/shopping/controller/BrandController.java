package com.gec.shopping.controller;

import com.gec.shopping.service.BrandService;
import com.gec.shopping.pojo.TbBrand;
import com.gec.shopping.pojo.entity.RestPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand-Ms")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @RequestMapping("/findAll")
    public List<TbBrand> findAll() {
        List<TbBrand> list = brandService.findAllBrand();
        System.out.println("查询所有商品(Controller)" + list);
        return list;
    }

    @RequestMapping("/findBrandDetail/{id}")
    public TbBrand findBrandDetail(@PathVariable Long id) {
        System.out.println("findBrandDetail.id:" + id);
        return brandService.findBrandsById(id);
    }


    @GetMapping("/findByPage")
    public RestPage findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbBrand> pages = (Page<TbBrand>) brandService.findAllBrand();
        RestPage restPage = new RestPage();
        restPage.setRows(pages.getResult());
        restPage.setTotal(pages.getTotal());
        return restPage;
    }

    @PostMapping("/addBrand")
    public void addBrand(@RequestBody TbBrand tbBrand) {
        System.out.println("tbBrand id:" + tbBrand.getId());
        System.out.println("tbBrand title:" + tbBrand.getName());
        brandService.addBrand(tbBrand);
    }

    @PostMapping("/updateBrand")
    public void updateBrand(@RequestBody TbBrand tbBrand) {
        brandService.updateBrand(tbBrand);
    }

    @GetMapping("/deleteBrand")
    public void deleteBrand(Long[] ids) {
        System.out.println("ids[0]======" + ids[0]);
        brandService.deleteBrand(ids);
    }

    // 用下拉框查询所有的品牌
    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList(){
        return brandService.selectOptionList();
    }
}
