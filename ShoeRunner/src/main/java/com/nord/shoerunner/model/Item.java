/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nord.shoerunner.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author xqn7
 */
@Entity
@Table(name = "Request_Item_Map")
public class Item implements Serializable {

    @Column(name = "item_upc")
    private String upc;

    @Column(name = "item_sku")
    private String sku;

    @Column(name = "description")
    private String description;

    @Column(name = "item_image_url")
    private String itemImageUrl;

    @Column(name = "color")
    private String color;

    @Column(name = "size")
    private String size;

    @Id
    @Column(name = "request_id", unique = true, nullable = false)
    @GeneratedValue(generator = "gen")
    @GenericGenerator(name = "gen", strategy = "foreign", parameters = @Parameter(name = "property", value = "shoeRunnerRequest"))
    private String requestId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private ShoeRunnerRequest shoeRunnerRequest;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Item() {

    }

    @JsonIgnore
    public ShoeRunnerRequest getShoeRunnerRequest() {
        return shoeRunnerRequest;
    }

    public void setShoeRunnerRequest(ShoeRunnerRequest shoeRunnerRequest) {
        this.shoeRunnerRequest = shoeRunnerRequest;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemImageUrl() {
        return itemImageUrl;
    }

    public void setItemImageUrl(String itemImageUrl) {
        this.itemImageUrl = itemImageUrl;
    }

}
