/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectsc1;
import java.util.HashMap;
import javax.swing.JOptionPane;
/**
 *
 * @author Edison
 */
public class Club {
    private HashMap<String, Member> members = new HashMap<>();
    private HashMap<String, AuthorizedPersons> authorizedPerson = new HashMap<>();
    AuthorizedPersons newPerson;
    Member newMember;
    // Menu menu = new Menu();

    public Member getMember(String id) {
        return members.get(id);
    }

    public void addMember() {
        if (isFull(1)) {
            JOptionPane.showMessageDialog(null, "Se ha alcanzado el límite máximo de socios.",
                                    "Aviso", JOptionPane.WARNING_MESSAGE);

        }else{
            String id = JOptionPane.showInputDialog("Ingrese la cedula del nuevo socio: ");
            if (memberExists(id)) {
                JOptionPane.showMessageDialog(null, "El socio con esta cedula ya existe", 
                                        "Aviso", JOptionPane.WARNING_MESSAGE);
                // menu.menu1();
            } else {
                String name = JOptionPane.showInputDialog("Ingrese el nombre del nuevo socio: ");
                String suscriptionType = JOptionPane.showInputDialog(
                    "Ingrese el tipo de suscripción:\n" +
                    "1: Regular\n" +
                    "2: Vip");
                if (suscriptionType.equals("1")) {
                    suscriptionType = "Regular";
                } else if (suscriptionType.equals("2")) {
                    suscriptionType = "Vip";
                }
                newMember = new Member(name, id, suscriptionType);
                members.put(newMember.getId(), newMember);
                if(suscriptionType.equals("Regular")){
                    newMember.setAvailableFounds(50000);
                }else{
                    newMember.setAvailableFounds(100000);
                }
                JOptionPane.showMessageDialog(null, "Socio agregado exitosamente. \n" +
                                        "Información del socio: " + members.get(newMember.getId()),
                                        "Socio Agregado", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    public void addAuthorizedPersons(String id){
        if(!isFull( 2)){
            String idMember = id;
            String idPerson = JOptionPane.showInputDialog("Ingrese la cedula del nuevo autorizado: ");
            if(memberExists(idPerson) || personExists(idPerson)){
                JOptionPane.showMessageDialog(null, "El socio o el autorizado con esta cedula ya existe.",
                                             "Aviso", JOptionPane.WARNING_MESSAGE);
                // menu.menu2(idMember);
                
            }else{
                String namePerson = JOptionPane.showInputDialog("Ingrese el nombre del nuevo autorizado: ");
                newPerson = new AuthorizedPersons(namePerson, idPerson, members.get(idMember));
                authorizedPerson.put(newPerson.getId(), newPerson);
                JOptionPane.showMessageDialog(null, "Autorizado agregado exitosamente. \n" +
                                             "Información del autorizado: " + authorizedPerson.get(newPerson.getId()),
                                             "Autorizado Agregado", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            System.out.println("No se puede agregar más autorizados. Ha alcanzado el límite.");
        }
    }

    public boolean isFull(int num){
        if (num == 1){
            if(this.members.size() >= 35){
                return true;
            }else{
                return false;
            }
        }else{
            if(this.authorizedPerson.size() >= 10){
                return true;
            }else{
                return false;
            } 
        }   
    }

    public boolean memberExists(String id) {
        return this.members.containsKey(id);
    }

    public boolean personExists(String id) {
        return this.authorizedPerson.containsKey(id);
    }
}
