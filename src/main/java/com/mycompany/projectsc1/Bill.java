package com.mycompany.projectsc1;
public class Bill extends Transaction {
    boolean paid;
    String concept;
    int numBill;
    
    public Bill(double value, String idMember,boolean paid,String concept) {
        super(value, idMember);
        this.paid = paid;
        this.concept = concept;
    }

    public String getConcept() {
        return concept;
    }

    public void pay(){
        paid = true;
    }

    public boolean isPaid() {
        return paid;
    }
}
