/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectsc1;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
/**
 *
 * @author Edison
 */
public class Club implements ActionsPersons{
    private HashMap<String, Member> members = new HashMap<>();
    
    
    Member newMember;
    int vip = 0; 
    public int getVip() {
        return vip;
    }
    public void setVip(int vip) {
        this.vip = vip;
    }
    public void setMembers(String id , Member member) {
        if (members.containsKey(id)) {
            members.replace(id, member);
        } else {
            members.put(id, member);
        }
    }
    public Member getMember(String id) {
        return members.get(id);
    }
    @Override
    public void add() {
        try{
            
            if (isFull()) {
                JOptionPane.showMessageDialog(null, "Se ha alcanzado el límite máximo de socios.",
                                        "Aviso", JOptionPane.WARNING_MESSAGE);
    
            }else{
                String id = JOptionPane.showInputDialog("Ingrese la cedula del nuevo socio: ");
                if (exists(id)) {
                    JOptionPane.showMessageDialog(null, "El socio con esta cedula ya existe", 
                                            "Aviso", JOptionPane.WARNING_MESSAGE);
                    
                } else {
                    String name = JOptionPane.showInputDialog("Ingrese el nombre del nuevo socio: ");
                    String suscriptionType = JOptionPane.showInputDialog(
                        "Ingrese el tipo de suscripción:\n" +
                        "1: Vip\n" +
                        "2: Regular");
                    if (suscriptionType.equals("1")) {
                        if(vip >= 3){
                            JOptionPane.showMessageDialog(null, "Se ha alcanzado el límite máximo de socios VIP.",
                                        "Aviso", JOptionPane.WARNING_MESSAGE);
                            
                        }else{
                            suscriptionType = suscriptionType(suscriptionType);
                            newMember = new Member(name, id, suscriptionType);
                            members.put(newMember.getId(), newMember);
                        }
                    }else{
                        suscriptionType = suscriptionType(suscriptionType);
                        newMember = new Member(name, id, suscriptionType);
                        members.put(newMember.getId(), newMember);
                    }
                    
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado: " + e.getMessage());
        }
    }
    
    
    @Override
    public boolean isFull(){
        
        if(this.members.size() >= 35){
            return true;
        }else{
            return false;
        }
           
    }
    @Override
    public boolean exists(String id) {
        return this.members.containsKey(id);
    }

    public String suscriptionType(String op){
        if (op.equals("1")) {
            return "Vip";
        } else {
            return "Regular";
        }
    }

    public void showMembers() {
    StringBuilder listString = new StringBuilder();
    listString.append("Lista de socios:\n");

    for (Map.Entry<String, Member> entry : members.entrySet()) {
        String id = entry.getKey();
        Member member = entry.getValue();

        listString.append("ID: ").append(id).append(", Nombre: ")
        .append(member.getName()).append(", Tipo: ")
        .append(member.getSuscriptionType()).append("\n");
        listString.append("Personas autorizadas: ").
        append(member.getAuthorizedPerson().size()).append("\n");


        listString.append("--------------------\n");
    }

    JOptionPane.showMessageDialog(null, listString.toString());
}
    @Override
    public void delete() {
        try{
        showMembers();
        String idToDelete = JOptionPane.showInputDialog("Ingrese el ID del socio a eliminar:");
        Member member = getMember(idToDelete);
        if (member == null) {
            JOptionPane.showMessageDialog(null, "No existe un socio con ese ID.");
            return;
        }
    
        if (member.getSuscriptionType().equalsIgnoreCase("VIP")) {
            JOptionPane.showMessageDialog(null, "No se pueden eliminar socios de tipo VIP.");
            return;
        }
    
        if (!member.getBillList().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El socio tiene facturas pendientes de pago.");
            return;
        }
    
        if (member.getAuthorizedPerson().size() > 1) {
            JOptionPane.showMessageDialog(null, "El socio tiene más de un autorizado.");
            return;
        }
    
        members.remove(idToDelete);
        JOptionPane.showMessageDialog(null, "Socio eliminado exitosamente.");
    } catch (NullPointerException e) {
        JOptionPane.showMessageDialog(null, "Error interno: miembro no encontrado.");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
    }
}

    
}
