package com.gec.shopping.controller;

import com.alibaba.fastjson.JSON;
import com.gec.shopping.activemq.JmsConfig;
import com.gec.shopping.pojo.TbGoods;
import com.gec.shopping.pojo.TbItem;
import com.gec.shopping.pojo.entity.Goods;
import com.gec.shopping.pojo.entity.RestBean;
import com.gec.shopping.pojo.entity.RestPage;
import com.gec.shopping.pojo.entity.Result;
import com.gec.shopping.service.GoodsService;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.*;

import javax.jms.*;
import java.util.List;

@RestController
@RequestMapping("/goods-ms")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @GetMapping("/findAll")
    public List<TbGoods> findAll() {
        return goodsService.findAllTbGoods();
    }

    /**
     * 返回全部列表
     * @return
     */
    @GetMapping("/findPage")
    public RestPage findPage(int pageNum, int pageSize) {
        System.out.println("显示的是第" + pageNum + "页");
        System.out.println("第" + pageSize + "行");
        return goodsService.findPage(pageNum, pageSize);
    }

    /**
     * 增加
     * @param goods
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Goods goods) {
        System.out.println("Goods getGoodsName" + goods.getGoods().getGoodsName());
        System.out.println("Goods getItemImages" + goods.getGoodsDesc().getItemImages());
        System.out.println("Goods getSpecificationItems" + goods.getGoodsDesc().getSpecificationItems());
        System.out.println("Goods getCustomAttributeItems" + goods.getGoodsDesc().getCustomAttributeItems());

        try {
            // 获得商家信息:
            //String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
            String sellerId = "yqtech"; //暂时设定
            goods.getGoods().setSellerId(sellerId);

            goodsService.add(goods);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     * @param goods
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Goods goods) {
        System.out.println("进入商品修改界面......");
        // 获得商家信息:
        //String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
        String sellerId = "yqtech"; //暂时设定
        Goods goods2 = goodsService.findGoodsById(goods.getGoods().getId());
        if (!sellerId.equals(goods2.getGoods().getSellerId()) || !sellerId.equals(goods.getGoods().getSellerId())) {
            return new Result(false, "非法操作");
        }
        try {
            goodsService.update(goods);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 获取实体
     * @param id
     * @return
     */
    @GetMapping("/findOne")
    public Goods findOne(Long id) {
        System.out.println("根据商品id获取实体....." + id);
        return goodsService.findGoodsById(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            goodsService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 查询+分页
     */
    @PostMapping("/search")
    public RestPage search(int pageNum, int pageSize, @RequestBody TbGoods goods) {
//        System.out.println(pageNum + "______pageNum______pageSize" + pageSize);
//        System.out.println(goods + "goods" );
        //String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
        String sellerId = "yqtech"; //暂时设定
        goods.setSellerId(sellerId);
        return goodsService.searchGoods(pageNum, pageSize, goods);
    }

    /**
     * 审核通过后消息中间件将发送信息到前台
     */
    @Autowired
    private JmsTemplate jmsTemplate;

    @RequestMapping("/updateStatus")
    public RestBean sendMessage(Long[] ids, String text) {
        System.out.println("ids: " + ids);
        System.out.println("text: " + text);
        try {
            //            //        更改数据库中tb_goods表中audit_status 0--》1
            goodsService.updateStatus(ids, text);
            if ("1".equals(text)) {  // 审核通过
                //*****导入到索引库
                //得到需要导入的SKU列表    根据spu的id查询对应的sku的商品
                List<TbItem> itemsList = goodsService.findItemListByGoodsIdListAndStatus(ids, text);
                for (TbItem tbItem : itemsList) {
                    System.out.println("itemsList:"+tbItem.getTitle());
                }
                // 导入到solr库中
                String jsonString = JSON.toJSONString(itemsList);  // 转换为json传输
                System.out.println("Goods controller:" + jsonString);

                Topic topicSolr = new ActiveMQTopic(JmsConfig.TOPIC_SOLR);
                // 发送消息
                jmsTemplate.send(topicSolr, new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        TextMessage textMessage = session.createTextMessage(jsonString);
                        System.out.println("textMessage"+textMessage.getText());
                        return textMessage;   // 将数据发送到搜索库中
                    }
                });

                /**生成商品详情页 freemarker**/
                for (Long goodsId : ids) {
                    Topic topicDestination = new ActiveMQTopic(JmsConfig.TOPIC_HTML);  // 发送一个消息页面
                    jmsTemplate.send(topicDestination, new MessageCreator() {
                        @Override
                        public Message createMessage(Session session) throws JMSException {
                            return session.createTextMessage(goodsId + "");   // 生成的页面格式为 id+html
                        }
                    });
                }
            }
            //jmsTemplate.convertAndSend(destination, text);
            return new RestBean(true, "修改状态成功");

        } catch (Exception e) {
            e.printStackTrace();
            return new RestBean(false, "修改状态失败");
        }
    }
}
