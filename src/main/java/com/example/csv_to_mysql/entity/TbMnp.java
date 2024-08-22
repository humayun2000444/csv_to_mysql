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

    @Column(name = "portedDate", nullable = false)
    private String portedDate;

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

    @Column(name = "donorrc", nullable = true)
    private int donorrc;

    @Column(name = "number_type", nullable = true)
    private String number_type;

    @Column(name = "ported_action", nullable = true)
    private String ported_action;

    @Column(name = "ported_date", nullable = true)
    private Date ported_date;

    // Getters and setters

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPortedDate(String portedDate) {
        return this.portedDate;
    }

    public void setPortedDate(String portedDate) {
        this.portedDate = portedDate;
    }

    public int getRecipientRC() {
        return recipientRC;
    }

    public void setRecipientRC(int recipientRC) {
        this.recipientRC = recipientRC;
    }

    public int getDonerRC() {
        return donerRC;
    }

    public void setDonerRC(int donerRC) {
        this.donerRC = donerRC;
    }

    public int getNrhRC() {
        return nrhRC;
    }

    public void setNrhRC(int nrhRC) {
        this.nrhRC = nrhRC;
    }

    public String getNumberType() {
        return numberType;
    }

    public void setNumberType(String numberType) {
        this.numberType = numberType;
    }

    public String getPortedAction() {
        return portedAction;
    }

    public void setPortedAction(String portedAction) {
        this.portedAction = portedAction;
    }
    public int getDonorrc() {
        return donorrc;
    }

    public void setDonorrc(int donorrc) {
        this.donorrc = donorrc;
    }

    public String getNumber_type() {
        return number_type;
    }

    public void setNumber_type(String number_type) {
        this.number_type = number_type;
    }

    public String getPorted_action() {
        return ported_action;
    }

    public void setPorted_action(String ported_action) {
        this.ported_action = ported_action;
    }

    public Date getPorted_date() {
        return ported_date;
    }

    public void setPorted_date(Date ported_date) {
        this.ported_date = ported_date;
    }
}
