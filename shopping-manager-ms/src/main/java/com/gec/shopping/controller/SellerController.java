package com.gec.shopping.controller;

import com.gec.shopping.pojo.TbSeller;
import com.gec.shopping.pojo.entity.RestBean;
import com.gec.shopping.pojo.entity.RestPage;
import com.gec.shopping.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@RequestMapping("/seller-Ms")
public class SellerController {
	@Autowired
	private SellerService sellerService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbSeller> findAll(){
		return sellerService.findAllSeller();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public RestPage findPage(int page, int rows){
		return sellerService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param seller
	 * @return
	 */
	@RequestMapping("/add")
	public RestBean add(@RequestBody TbSeller seller){
		//密码加密
		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		String password = passwordEncoder.encode(seller.getPassword());//加密
		seller.setPassword(password);
		
		try {
			sellerService.add(seller);
			return new RestBean(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new RestBean(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param seller
	 * @return
	 */
	@RequestMapping("/update")
	public RestBean update(@RequestBody TbSeller seller){
		try {
			sellerService.update(seller);
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
	public TbSeller findOne(String id){
		return sellerService.findOne(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public RestBean delete(String [] ids){
		try {
			sellerService.delete(ids);
			return new RestBean(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new RestBean(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public RestPage search(int page, int rows,@RequestBody TbSeller seller){
		return sellerService.searchSeller(page, rows,seller);
	}
	
}
