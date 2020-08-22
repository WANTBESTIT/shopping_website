package com.gec.shopping.service;

import com.gec.shopping.pojo.TbItem;
import com.gec.shopping.pojo.entity.RestPage;

import java.util.List;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface ItemService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbItem> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public RestPage findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbItem item);
	
	
	/**
	 * 修改
	 */
	public void update(TbItem item);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbItem findOne(Long id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Long[] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public RestPage findPage(TbItem item, int pageNum, int pageSize);
	
}
