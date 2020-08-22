package com.gec.shopping.controller;
import java.util.List;

import com.gec.shopping.pojo.TbItemCat;
import com.gec.shopping.pojo.entity.RestBean;
import com.gec.shopping.pojo.entity.RestPage;
import com.gec.shopping.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * controller
 * @author Administrator
 */
@RestController
@RequestMapping("/itemCat-ms")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbItemCat> findAll(){			
		return itemCatService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public RestPage findPage(int pageNum, int pageSize){
		return itemCatService.findPage(pageNum, pageSize);
	}
	
	/**
	 * 增加
	 * @param itemCat
	 * @return
	 */
	@RequestMapping("/add")
	public RestBean add(@RequestBody TbItemCat itemCat){
		try {
			itemCatService.add(itemCat);
			return new RestBean(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new RestBean(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param itemCat
	 * @return
	 */
	@RequestMapping("/update")
	public RestBean update(@RequestBody TbItemCat itemCat){
		try {
			itemCatService.update(itemCat);
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
	public TbItemCat findOne(Long id){
		return itemCatService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public RestBean delete(Long [] ids){
		try {
			itemCatService.delete(ids);
			return new RestBean(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new RestBean(false, "删除失败");
		}
	}
	
	/**
	 * 查询+分页
	 * @return
	 */
	@RequestMapping("/search")
	public RestPage search(@RequestBody TbItemCat itemCat, int pageNum, int pageSize){
		return itemCatService.findPage(itemCat, pageNum, pageSize);
	}
	
	@RequestMapping("/findByParentId")
	public List<TbItemCat> findByParentId(Long parentId){
		return itemCatService.findByParentId(parentId);
	}
}
