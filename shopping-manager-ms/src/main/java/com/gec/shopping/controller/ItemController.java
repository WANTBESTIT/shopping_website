package com.gec.shopping.controller;

import com.gec.shopping.pojo.TbItem;
import com.gec.shopping.pojo.entity.RestBean;
import com.gec.shopping.pojo.entity.RestPage;
import com.gec.shopping.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/item-ms")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbItem> findAll(){			
		return itemService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public RestPage findPage(int pageNum, int pageSize){
		return itemService.findPage(pageNum, pageSize);
	}
	
	/**
	 * 增加
	 * @param item
	 * @return
	 */
	@RequestMapping("/add")
	public RestBean add(@RequestBody TbItem item){
		try {
			itemService.add(item);
			return new RestBean(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new RestBean(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param item
	 * @return
	 */
	@RequestMapping("/update")
	public RestBean update(@RequestBody TbItem item){
		try {
			itemService.update(item);
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
	public TbItem findOne(Long id){
		return itemService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public RestBean delete(Long [] ids){
		try {
			itemService.delete(ids);
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
	public RestPage search(@RequestBody TbItem item, int pageNum, int pageSize  ){
		return itemService.findPage(item, pageNum, pageSize);
	}
	
}
