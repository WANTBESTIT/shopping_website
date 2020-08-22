package com.gec.shopping.service;

import com.gec.shopping.pojo.TbGoods;
import com.gec.shopping.pojo.TbItem;
import com.gec.shopping.pojo.entity.Goods;
import com.gec.shopping.pojo.entity.RestPage;

import java.util.List;

public interface GoodsService {

    public List<TbGoods> findAllTbGoods();

    public Goods findGoodsById(Long id);

    public void add(Goods goods);

    public void update(Goods goods);

    public void delete(Long[] ids);

    public RestPage searchGoods(int pageNum, int pageSize,TbGoods goods);

    public RestPage findPage(int pageNum, int pageSize);

    public void updateStatus(Long[] ids, String status);

    public List<TbItem> findItemListByGoodsIdListAndStatus(Long[] goodsIds, String status);
}
