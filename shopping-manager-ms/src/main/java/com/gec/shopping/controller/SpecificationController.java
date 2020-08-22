package com.gec.shopping.controller;

import com.gec.shopping.pojo.TbSpecification;
import com.gec.shopping.pojo.entity.RestBean;
import com.gec.shopping.pojo.entity.RestPage;
import com.gec.shopping.pojo.entity.Specification;
import com.gec.shopping.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/specification-ms")
public class SpecificationController {

	@Autowired
	private SpecificationService specificationService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbSpecification> findAll(){
		
		return specificationService.findAllSpecification();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public RestPage findPage(int pageNum, int pageSize){
		return specificationService.findPage(pageNum, pageSize);
	}
	
	/**
	 * 增加
	 * @param specification
	 * @return
	 */
	@PostMapping("/add")
	public RestBean add(@RequestBody Specification specification){
		try {
			specificationService.addSpecification(specification);
			return new RestBean(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new RestBean(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param specification
	 * @return
	 */
	@PostMapping("/update")
	public RestBean update(@RequestBody Specification specification){
		try {
			specificationService.updateSpecification(specification);
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
	public Specification findOne(Long id){
		return specificationService.findSpecificationById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public RestBean delete(Long [] ids){
		try {
			specificationService.deleteSpecification(ids);
			return new RestBean(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new RestBean(false, "删除失败");
		}
	}

	/**
	 * 查询+分页
	 */
	@PostMapping("/search")
	public RestPage search(int pageNum, int pageSize, @RequestBody TbSpecification specification) {
//        System.out.println(pageNum + "______pageNum______pageSize" + pageSize);
//        System.out.println(goods + "goods" );
		return specificationService.search(pageNum, pageSize, specification);
	}
	
	@RequestMapping("/selectOptionList")
	public List<Map> selectOptionList(){
		return specificationService.selectOptionList();
	}
	
}
