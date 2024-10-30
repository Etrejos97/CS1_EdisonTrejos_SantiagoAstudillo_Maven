package com.mycompany.projectsc1;

public class Consumption extends Transaction{
    
    public Consumption(double value, String idMember) {
        super(value, idMember);
        
    }

    public double registerConsumption(double amount){
        this.value = amount;
        return value;
    }
}
