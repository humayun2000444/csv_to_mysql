package com.example.csv_to_mysql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_mnp")
public class TbMnp {

    @Id
    @Column(name = "number", unique = true, nullable = false)
    private String number;

    @Column(name = "portedDate")
    private Date portedDate;

    @Column(name = "recipientRC", nullable = false, columnDefinition = "int(11) default '0'")
    private int recipientRC;

    @Column(name = "donerRC", nullable = false, columnDefinition = "int(11) default '0'")
    private int donerRC;

    @Column(name = "nrhRC", nullable = false, columnDefinition = "int(11) default '0'")
    private int nrhRC;

    @Column(name = "numberType", nullable = false)
    private String numberType;

    @Column(name = "portedAction", nullable = false)
    private String portedAction;



    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setPortedDate(Date portedDate) {
        this.portedDate = portedDate;
    }

    public Date getPortedDate() {
        return portedDate;
    }

    public void setRecipientRC(int recipientRC) {
        this.recipientRC = recipientRC;
    }

    public int getRecipientRC() {
        return recipientRC;
    }

    public void setDonerRC(int donerRC) {
        this.donerRC = donerRC;
    }

    public int getDonerRC() {
        return donerRC;
    }

    public void setNrhRC(int nrhRC) {
        this.nrhRC = nrhRC;
    }

    public int getNrhRC() {
        return nrhRC;
    }

    public void setNumberType(String numberType) {
        this.numberType = numberType;
    }

    public String getNumberType() {
        return numberType;
    }

    public void setPortedAction(String portedAction) {
        this.portedAction = portedAction;
    }

    public String getPortedAction() {
        return portedAction;
    }
}