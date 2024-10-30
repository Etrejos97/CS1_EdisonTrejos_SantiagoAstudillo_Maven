package com.mycompany.projectsc1;

public class Consumption extends Transaction{
    
    public Consumption(double value, String idMember) {
        super(value, idMember);
        
    }

    public double registerConsumption(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("El consumo no puede ser negativo.");
        }
        this.value = amount;
        return value;
    }
}
