package com.android.gandharvms;

public class In_Tanker_Security_list {

    String SelectRegister,SerialNumber,date,intime,invoiceno,material,netweight,partyname,qty,uom,vehicalnumber;

    public In_Tanker_Security_list(){

    }


    public In_Tanker_Security_list(String selectRegister, String serialNumber, String date, String intime, String invoiceno, String material, String netweight, String partyname, String qty, String uom, String vehicalnumber) {
        this.SelectRegister = selectRegister;
        this.SerialNumber = serialNumber;
        this.date = date;
        this.intime = intime;
        this.invoiceno = invoiceno;
        this.material = material;
        this.netweight = netweight;
        this.partyname = partyname;
        this.qty = qty;
        this.uom = uom;
        this.vehicalnumber = vehicalnumber;
    }

    public String getSelectRegister() {
        return SelectRegister;
    }

    public void setSelectRegister(String selectRegister) {
        SelectRegister = selectRegister;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public String getInvoiceno() {
        return invoiceno;
    }

    public void setInvoiceno(String invoiceno) {
        this.invoiceno = invoiceno;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getNetweight() {
        return netweight;
    }

    public void setNetweight(String netweight) {
        this.netweight = netweight;
    }

    public String getPartyname() {
        return partyname;
    }

    public void setPartyname(String partyname) {
        this.partyname = partyname;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getVehicalnumber() {
        return vehicalnumber;
    }

    public void setVehicalnumber(String vehicalnumber) {
        this.vehicalnumber = vehicalnumber;
    }
}
