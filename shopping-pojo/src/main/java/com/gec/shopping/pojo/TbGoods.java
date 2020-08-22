package com.gec.shopping.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * SPU=Standard Product Unit (标准产品单位)
 * SPU是商品信息聚合的最小单位，是一组可复用、易检索的标准化信息的集合，该集合描述了一个产品的特性。
 * 在商品信息电子化过程中，商品的特性可以由多个"属性|属性值对"进行描述。
 * "属性|属性值对"完全相同的商品，可以抽象成为一个SPU。另—方面，这些属性|属性值对在SPU中固化下来，逐步标准化。"
 * 基于SPU的商品信息结构，可以实现丰富的应用，比如商品信息与资讯、评论、以及其它SPU的整合。
 * 例如:iPhone×可以确定一个产品即为一个SPU。
 */
public class TbGoods implements Serializable{
    private Long id;

    private String sellerId;

    private String goodsName;

    private Long defaultItemId;

    private String auditStatus;

    private String isMarketable;

    private Long brandId;

    private String caption;

    private Long category1Id;

    private Long category2Id;

    private Long category3Id;

    private String smallPic;

    private Double price;

    private Long typeTemplateId;

    private String isEnableSpec;

    private String isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId == null ? null : sellerId.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Long getDefaultItemId() {
        return defaultItemId;
    }

    public void setDefaultItemId(Long defaultItemId) {
        this.defaultItemId = defaultItemId;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public String getIsMarketable() {
        return isMarketable;
    }

    public void setIsMarketable(String isMarketable) {
        this.isMarketable = isMarketable == null ? null : isMarketable.trim();
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption == null ? null : caption.trim();
    }

    public Long getCategory1Id() {
        return category1Id;
    }

    public void setCategory1Id(Long category1Id) {
        this.category1Id = category1Id;
    }

    public Long getCategory2Id() {
        return category2Id;
    }

    public void setCategory2Id(Long category2Id) {
        this.category2Id = category2Id;
    }

    public Long getCategory3Id() {
        return category3Id;
    }

    public void setCategory3Id(Long category3Id) {
        this.category3Id = category3Id;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic == null ? null : smallPic.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getTypeTemplateId() {
        return typeTemplateId;
    }

    public void setTypeTemplateId(Long typeTemplateId) {
        this.typeTemplateId = typeTemplateId;
    }

    public String getIsEnableSpec() {
        return isEnableSpec;
    }

    public void setIsEnableSpec(String isEnableSpec) {
        this.isEnableSpec = isEnableSpec == null ? null : isEnableSpec.trim();
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {

        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    @Override
    public String toString() {
        return "TbGoods{" +
                "id=" + id +
                ", sellerId='" + sellerId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", defaultItemId=" + defaultItemId +
                ", auditStatus='" + auditStatus + '\'' +
                ", isMarketable='" + isMarketable + '\'' +
                ", brandId=" + brandId +
                ", caption='" + caption + '\'' +
                ", category1Id=" + category1Id +
                ", category2Id=" + category2Id +
                ", category3Id=" + category3Id +
                ", smallPic='" + smallPic + '\'' +
                ", price=" + price +
                ", typeTemplateId=" + typeTemplateId +
                ", isEnableSpec='" + isEnableSpec + '\'' +
                ", isDelete='" + isDelete + '\'' +
                '}';
    }
}