/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nord.shoerunner.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author xqn7
 */
@Entity
@Table(name = "Shoerunner_request")
public class ShoeRunnerRequest implements Serializable {

    @Column(name = "employee_id")
    private String employeeId;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @Column(name = "shoerunner_id")
    private String shoeRunnerId;

    @OneToOne(mappedBy = "shoeRunnerRequest", cascade = CascadeType.ALL)
    private Item item;

    @Column(name = "request_status_id")
    private int requestStatusId = 1;

    @JsonIgnore
    @Column(name = "created_time")
    private Timestamp createdDateTime;

    public ShoeRunnerRequest() {

    }

    public Timestamp getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Timestamp createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getShoeRunnerId() {
        return shoeRunnerId;
    }

    public void setShoeRunnerId(String shoeRunnerId) {
        this.shoeRunnerId = shoeRunnerId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getRequestStatusId() {
        return requestStatusId;
    }

    public void setRequestStatusId(int requestStatusId) {
        this.requestStatusId = requestStatusId;
    }

}
