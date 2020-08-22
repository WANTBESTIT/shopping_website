package com.gec.shopping.controller;

import com.gec.shopping.pojo.TbTypeTemplate;
import com.gec.shopping.pojo.entity.RestBean;
import com.gec.shopping.pojo.entity.RestPage;
import com.gec.shopping.service.TypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/typeTemplate-Ms")
public class TypeTemplateController {

	@Autowired
	private TypeTemplateService typeTemplateService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbTypeTemplate> findAll(){
		return typeTemplateService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public RestPage findPage(int pageNum, int pageSize){
		return typeTemplateService.findPage(pageNum, pageSize);
	}
	
	/**
	 * 增加
	 * @param typeTemplate
	 * @return
	 */
	@RequestMapping("/add")
	public RestBean add(@RequestBody TbTypeTemplate typeTemplate){
		System.out.println("============================================");
		System.out.println("====================="+typeTemplate.getName()+"====================");
		System.out.println("============================================");
		try {
			typeTemplateService.add(typeTemplate);
			return new RestBean(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new RestBean(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param typeTemplate
	 * @return
	 */
	@RequestMapping("/update")
	public RestBean update(@RequestBody TbTypeTemplate typeTemplate){
		try {
			typeTemplateService.update(typeTemplate);
			return new RestBean(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new RestBean(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public TbTypeTemplate findOne(Long id){
		return typeTemplateService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public RestBean delete(Long [] ids){
		try {
			typeTemplateService.delete(ids);
			return new RestBean(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new RestBean(false, "删除失败");
		}
	}
	
	/**
	 * 查询+分页
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/search")
	public RestPage search(@RequestBody TbTypeTemplate typeTemplate, int pageNum, int pageSize){
		return typeTemplateService.findPage(typeTemplate, pageNum, pageSize);
	}
	
	@RequestMapping("/findBySpecList")
	public List<Map> findSpecList(Long id){
		return typeTemplateService.findSpecList(id);
	}
	
	
}
