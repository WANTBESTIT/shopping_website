package com.gec.shopping.pojo.entity;

import com.gec.shopping.pojo.TbContent;
import com.gec.shopping.pojo.TbContentCategory;

import java.io.Serializable;
import java.util.List;

public class ContentCategory  implements Serializable {

    private  List<TbContent> contents;

    private TbContentCategory contentCategorie;

    public List<TbContent> getContents() {
        return contents;
    }

    public void setContents(List<TbContent> contents) {
        this.contents = contents;
    }

    public TbContentCategory getContentCategorie() {
        return contentCategorie;
    }

    public void setContentCategorie(TbContentCategory contentCategorie) {
        this.contentCategorie = contentCategorie;
    }
}
