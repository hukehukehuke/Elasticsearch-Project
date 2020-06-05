package com.huke.elasticsearch.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "item",type = "docs",shards = 1,replicas = 0)
public class Item {
    @Id
    private Long id;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")  //标记是一个elasticSearch字段
    private String title;
    @Field(type = FieldType.Keyword)
    private String category;
    @Field(type = FieldType.Keyword)
    private String beand;
    @Field(type = FieldType.Double)
    private Double price;
    @Field(type = FieldType.Keyword)
    private String images;

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
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBeand() {
        return beand;
    }

    public void setBeand(String beand) {
        this.beand = beand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Item() {
    }

    public Item(Long id, String title, String category, String beand, Double price, String images) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.beand = beand;
        this.price = price;
        this.images = images;
    }
}
