package com.mycompany.projectsc1;

abstract class Transaction {
    double value;
    String idMember;
    public Transaction(double value, String idMember) {
        this.value = value;
        this.idMember = idMember;
    }
    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
    public String getIdMember() {
        return idMember;
    }
    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }
    
        
}
