package com.gec.shopping.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.Dynamic;

/**
 * SKU=Stock Keeping Unit(库存量单位)
 * 针对电商而言:
 *     1、SKU是指一款商品，每款都有出现一个SKU，便于电商品牌识别商品。
 *     2、一款商品多色，则是有多个SKU，例:一件衣服，有红色、白色、蓝色，则SKU编码也不相同，如相同则会出现混淆，发错货。
 * 例如: iPhone ×64G银色则是一个SKU。
 */
public class TbItem implements Serializable{
	
	@Field
    private Long id;

	@Field("item_title") // 表示 持久化对象 的属性和solr业务字段的映射关系 
    private String title;  // 标题

    private String sellPoint;  // 卖点

    @Field("item_price")
    private Double price;  // 价格

    private Integer stockCount;

    private Integer num;  // 数量

    private String barcode;  // 条形码

    @Field("item_image")
    private String image;  // 商品图片

    private Long categoryid;  // 分类id

    private String status;  // 状态

    private Date createTime;  // 创建时间

    private Date updateTime;  // 修改时间

    private String itemSn;

    private Double costPirce;  // 打折价格

    private Double marketPrice; // 市场价格

    private String isDefault;  // 是否设为默认显示

    @Field("item_goodsid")
    private Long goodsId;

    private String sellerId;  // 销售id

    private String cartThumbnail;

    @Field("item_category")
    private String category;  // 类型

    @Field("item_brand")
    private String brand;  // 品牌

    private String spec;  // 规格

    @Field("item_seller")
    private String seller;  // 销售商
    
    @Dynamic
    @Field("item_spec_*")
    private Map<String,String> specMap;
    
    public Map<String, String> getSpecMap() {
		return specMap;
	}

	public void setSpecMap(Map<String, String> specMap) {
		this.specMap = specMap;
	}
	
	

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint == null ? null : sellPoint.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getItemSn() {
        return itemSn;
    }

    public void setItemSn(String itemSn) {
        this.itemSn = itemSn == null ? null : itemSn.trim();
    }

    public Double getCostPirce() {
        return costPirce;
    }

    public void setCostPirce(Double costPirce) {
        this.costPirce = costPirce;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault == null ? null : isDefault.trim();
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId == null ? null : sellerId.trim();
    }

    public String getCartThumbnail() {
        return cartThumbnail;
    }

    public void setCartThumbnail(String cartThumbnail) {
        this.cartThumbnail = cartThumbnail == null ? null : cartThumbnail.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec == null ? null : spec.trim();
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller == null ? null : seller.trim();
    }
}