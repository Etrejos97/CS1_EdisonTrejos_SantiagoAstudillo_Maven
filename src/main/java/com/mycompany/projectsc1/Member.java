/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectsc1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

/**
 *
 * @author Edison
 */
public class Member extends ClubMember implements ActionsPersons{
    private String suscriptionType;
    private double availableFounds;
    private HashMap<String, AuthorizedPersons> authorizedPerson = new HashMap<>();
    private ArrayList<Bill> billList = new ArrayList<Bill>();
    
    public Member(String name, String id, String suscriptionType) {
        super(name,id);
        this.suscriptionType = suscriptionType;
        
    }
    public HashMap<String, AuthorizedPersons> getAuthorizedPerson() {
        return authorizedPerson;
    }
    public void setAuthorizedPerson(HashMap<String, AuthorizedPersons> authorizedPerson) {
        this.authorizedPerson = authorizedPerson;
    }
    public ArrayList<Bill> getBillList() {
        return billList;
    }

    public void setBillList(ArrayList<Bill> billList) {
        this.billList = billList;
    }


    public String getSuscriptionType() {
        return suscriptionType;
    }

    public void setSuscriptionType(String suscriptionType) {
        this.suscriptionType = suscriptionType;
    }

    public double getAvailableFounds() {
        return availableFounds;
    }

    public void setAvailableFounds(double availableFounds) {
        this.availableFounds = availableFounds;
    }

    @Override
    public void add() {
        AuthorizedPersons newPerson;
        Club club = new Club();
        if(!isFull()){
            String idMember = this.id;
            String idPerson = JOptionPane.showInputDialog("Ingrese la cedula del nuevo autorizado: ");
            if(club.exists(idPerson) || this.exists(idPerson)){
                JOptionPane.showMessageDialog(null, "El socio o el autorizado con esta cedula ya existe.",
                                             "Aviso", JOptionPane.WARNING_MESSAGE);
                // menu.menu2(idMember);
                
            }else{
                String namePerson = JOptionPane.showInputDialog("Ingrese el nombre del nuevo autorizado: ");
                newPerson = new AuthorizedPersons(namePerson, idPerson, idMember);
                authorizedPerson.put(newPerson.getId(), newPerson);
                JOptionPane.showMessageDialog(null, "Autorizado agregado exitosamente. \n" +
                                             "Información del autorizado: " + authorizedPerson.get(newPerson.getId()),
                                             "Autorizado Agregado", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            System.out.println("No se puede agregar más autorizados. Ha alcanzado el límite.");
        }
    }
    @Override
    public boolean isFull(){
        
        if(this.authorizedPerson.size() >= 10){
            return true;
        }else{
            return false;
        }    
    }
    @Override 
    public boolean exists(String id) {
        return this.authorizedPerson.containsKey(id);
    }
    @Override
    public void delete() {
        try {
            
            showPersonList();

            String idToDelete = JOptionPane.showInputDialog("Ingrese el ID de la persona a eliminar:");

            // Verificar si la persona existe en el HashMap y eliminarla
            if (authorizedPerson.containsKey(idToDelete)) {
                authorizedPerson.remove(idToDelete);
                JOptionPane.showMessageDialog(null, "Persona eliminada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una persona con ese ID.");
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Error: Ha ocurrido un problema al eliminar la persona.");
            System.err.println("Error detallado: " + e.getMessage());
        }
    }
    public void showPersonList() {
    
    HashMap<String, AuthorizedPersons> authorizedPersons = this.authorizedPerson;

    StringBuilder listPersons = new StringBuilder();
    for (Map.Entry<String, AuthorizedPersons> entry : authorizedPersons.entrySet()) {
        String id = entry.getKey();
        String name = entry.getValue().getName(); 
        listPersons.append(id).append(" - ").append(name).append("\n");

        listPersons.append("--------------------\n");
    }

    JOptionPane.showMessageDialog(null, listPersons.toString(), "Lista de Personas", JOptionPane.INFORMATION_MESSAGE);
}


public void addFounds(double foundsd, int n) {
    if(n == 1 && this.availableFounds + foundsd > 5000000){
        JOptionPane.showMessageDialog(null, "No se puede agregar más fondos. Ha superado el límite de 5 millones.",
        "Aviso", JOptionPane.WARNING_MESSAGE);
    }else if(n == 2 && this.availableFounds + foundsd > 1000000){
        JOptionPane.showMessageDialog(null, "No se puede agregar más fondos. Ha superado el límite de 1 millón.",
        "Aviso", JOptionPane.WARNING_MESSAGE);
        }else{
            this.availableFounds += foundsd;
            JOptionPane.showMessageDialog(null, "Fondos agregados exitosamente. \n" +
            "Fondos disponibles: $" + availableFounds,
                                             "Fondos Agregados", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void addBill(double value,String concept){
        Bill bill = new Bill(value, id, false, concept);
        this.billList.add(bill);
        JOptionPane.showMessageDialog(null, "Factura agregada exitosamente. \n" +
                                             "Factura número: " + (this.billList.size()),
                                             "Factura Agregada", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showBillList(){
        if(this.billList.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay facturas por pagar.",
            "Aviso", JOptionPane.WARNING_MESSAGE);
        }else{
            StringBuilder sb = new StringBuilder();
            for(Bill b : this.billList){
                sb.append("Factura número: ").append(this.billList.indexOf(b) + 1).append("\n")
                .append("Valor: $").append(b.getValue()).append("\n")
                .append("Estado: Por pagar\n")
                .append("Concepto: ").append(b.getConcept()).append("\n");
                
                sb.append("--------------------\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString(), "Listado de Facturas", 
            JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void payBill(){
        showBillList();
        String input = JOptionPane.showInputDialog("Ingrese el número de factura a pagar:");
        try{

            int index = Integer.parseInt(input);
            index--;
            if (index >= 0 && index < billList.size()) {
                Bill bill = this.billList.get(index);
                if(bill.value <= this.availableFounds){
                    bill.pay();
                    JOptionPane.showMessageDialog(null, "Factura pagada exitosamente. \n" +
                    "Factura número: " + (index + 1),
                    "Factura Pagada", JOptionPane.INFORMATION_MESSAGE);
                    billList.remove(bill);
                    this.availableFounds -= bill.value;
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Fondos insuficientes",
                                                    "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(null, "Índice de factura inválido.",
                                              "Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
        }
        
    }
    @Override
    public String toString() {
        return  "Cedula: "+ id + "\nNombre: " + name  +
                "\nTipo de suscripcion: " + suscriptionType;
    }
}
