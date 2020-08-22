package com.gec.shopping.pojo.entity;

import java.util.List;

/**
 * 分页封装类
 */
public class RestPage {

    private List rows;    // 查询分页区间数
    private Long total;   // 总记录数

    public RestPage() {
    }

    public RestPage(long total, List rows) {
        super();
        this.total = total;
        this.rows = rows;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
