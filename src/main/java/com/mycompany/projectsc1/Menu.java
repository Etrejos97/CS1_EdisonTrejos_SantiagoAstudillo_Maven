/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectsc1;
import javax.swing.JOptionPane;
/**
 *
 * @author Edison
 */
public class Menu {
    String id;
    Club miClub = new Club();
    AuthorizedPersons newPerson;
    String option;
    public Menu(){
    }
    public void menu1(){ 
        do{
            option = JOptionPane.showInputDialog(
                    "Bienvenido al Club...\n" +
                    "Ingrese una opción: \n" +
                    "1. Registrarse como nuevo socio\n" +
                    "2. Ya eres socio\n" +
                    "Para salir presione cancel. ");
            
            switch (option) {
                case "1":
                    
                    miClub.addMember();
                    menu1();
                    
                    break;
                case "2":
                    id = JOptionPane.showInputDialog("Ingrese su cedula: ");
                    if(!miClub.memberExists(id)){
                        JOptionPane.showMessageDialog(null, "El socio no existe. Por favor, verifique la cédula.", 
                                                "Error", JOptionPane.ERROR_MESSAGE);
                        menu1();
                    }else{  
                        menu2(id);
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion no valida.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    menu1();
                    break;
                }           
            }while(option.equals("0")); 
        
    }

    public void menu2(String id){
        do{
            option = JOptionPane.showInputDialog(
                                        "Menu\n" +
                                        "Ingrese una opción: \n" +
                                        "0. Volver al menu anterior\n" +
                                        "1. Registrar nueva persona autorizada\n" +
                                        "2. Buscar socio por cedula\n" +
                                        "Para salir presione cancel. ");
                switch (option) {
                    case "0":
                        menu1();
                        break;
                    case "1":
                        miClub.addAuthorizedPersons(id);
                        break;
                    case "2":
                        id = JOptionPane.showInputDialog("Ingrese la cedula del socio que desea buscar: ");
                        Member member = miClub.getMember(id);
                        if(!miClub.memberExists(id)){
                            JOptionPane.showMessageDialog(null, "El socio no existe.", "Aviso", 
                                                            JOptionPane.WARNING_MESSAGE);
                            menu2(id);
                            
                        }else {
                            JOptionPane.showMessageDialog(null, "Socio encontrado: " + member, "Resultado de la búsqueda", 
                                                        JOptionPane.INFORMATION_MESSAGE);
                            menu2(id);
                        }
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opcion no valida.", "Aviso", JOptionPane.WARNING_MESSAGE);
                        break;
                }
        }while(option.equals("0"));
    }
}
