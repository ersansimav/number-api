package com.ersan.assignment.number.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "numbers")
public class Number {

    @Id
    private Integer number;
    private Date createDate;

    public Number() {

    }

    public Number(Integer number, Date createDate) {
        this.number = number;
        this.createDate = createDate;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
