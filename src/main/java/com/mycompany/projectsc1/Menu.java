/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectsc1;
import javax.swing.JOptionPane;
import java.io.IOException;
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
    public void menu1() throws IOException{
        try{ 
            do{
                option = JOptionPane.showInputDialog(
                        "Bienvenido al Club...\n" +
                        "Ingrese una opción: \n" +
                        "1. Nuevo socio\n" +
                        "2. Socio Existente\n" +
                        "3. Eliminar Socio\n" +
                        "Para salir presione cancel. ");
                
                switch (option) {
                    case "1":
                        
                        miClub.add();
                        menu1();
                        
                        break;
                    case "2":
                        id = JOptionPane.showInputDialog("Ingrese cedula del socio: ");
                        if(!miClub.exists(id)){
                            JOptionPane.showMessageDialog(null, "El socio no existe. Por favor, verifique la cédula.", 
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                            menu1();
                        }else{  
                            menu2(id);
                        }
                        break;
                    case "3":
                        miClub.delete();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opcion no valida.", "Aviso", JOptionPane.WARNING_MESSAGE);
                        menu1();
                        break;
                }           
            }while(!option.equals("0"));
        } catch (NullPointerException e) {
            
            JOptionPane.showMessageDialog(null, "Error interno: puntero nulo.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
        } 
        
    }

    public void menu2(String id) throws IOException{
        try{
            do{
                option = JOptionPane.showInputDialog(
                                            "Menu\n" +
                                            "Ingrese una opción: \n" +
                                            "0. Volver al menu anterior\n" +
                                            "1. Registrar nueva persona autorizada\n" +
                                            "2. Eliminar persona autorizada\n" +
                                            "3. Buscar socio por cedula\n" +
                                            "4. Consultar fondos\n" +
                                            "5. Aumentar fondos\n" +
                                            "6. Registrar consumo\n" +
                                            "7. Facturas por pagar\n" +
                                            "Para salir presione cancel. ");
                    switch (option) {
                        case "0":
                            menu1();
                            break;
                        case "1":
                        miClub.getMember(id).add();
                            break;
                        case "2":
                            miClub.getMember(id).delete();
                            break;
                        case "3":
                            id = JOptionPane.showInputDialog("Ingrese la cedula del socio que desea buscar: ");
                            Member member = miClub.getMember(id);
                            if(!miClub.exists(id)){
                                JOptionPane.showMessageDialog(null, "El socio no existe.", "Aviso", 
                                                                JOptionPane.WARNING_MESSAGE);
                                menu2(id);
                                
                            }else {
                                JOptionPane.showMessageDialog(null, "Socio encontrado: " + member, "Resultado de la búsqueda", 
                                                            JOptionPane.INFORMATION_MESSAGE);
                                menu2(id);
                            }
                            break;
                        case "4":
                            JOptionPane.showMessageDialog(null, "El socio: " + miClub.getMember(id).getName()
                            + " cuenta con: $"+miClub.getMember(id).getAvailableFounds(), "Fondos", 
                            JOptionPane.INFORMATION_MESSAGE);
                            
                            break;
                        case "5":
                            if(miClub.getMember(id).getSuscriptionType().equalsIgnoreCase("Vip")){
                                if(miClub.getMember(id).getAvailableFounds() < 5000000){
                                    String founds = JOptionPane.showInputDialog(null,
                                                                    "Ingrese el monto a aumentar:");
                                    double foundsd = Double.parseDouble(founds);
                                    miClub.getMember(id).addFounds(foundsd, 1);
                                }else{
                                    JOptionPane.showMessageDialog(null, "No puede aumentar más fondos, Limite: $5,000,000.", 
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                } 
                            }
                            if(miClub.getMember(id).getSuscriptionType().equalsIgnoreCase("Regular")){
                                if(miClub.getMember(id).getAvailableFounds() < 1000000){
                                    String founds = JOptionPane.showInputDialog(null,
                                                                    "Ingrese el monto a aumentar:");
                                    double foundsd = Double.parseDouble(founds);
                                    miClub.getMember(id).addFounds(foundsd, 2);
                                }else{
                                    JOptionPane.showMessageDialog(null, "No puede aumentar más fondos, Limite: $5,000,000.", 
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }     
                            break;
                        case "6":
                            menuServices();
                            break;
                        case "7":
                            miClub.getMember(id).payBill();
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opcion no valida.", "Aviso", JOptionPane.WARNING_MESSAGE);
                            break;
                    }
            }while(!option.equals("0"));
        } catch (NullPointerException e) {
                
            JOptionPane.showMessageDialog(null, "Error interno: puntero nulo.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
        } 
    }

    public void menuServices() throws IOException{
        do{
            option = JOptionPane.showInputDialog(
                                        "Menu Servicios\n" +
                                        "Ingrese una opción: \n" +
                                        "0. Volver al menu anterior\n" +
                                        "1. Reserva de espacios\n" +
                                        "2. Talleres\n" +
                                        "3. Restaurante\n" +
                                        "4. Alquiler de equipo\n" +
                                        "5. Entrenador personalizado: $100000\n" +
                                        "Para salir presione cancel. ");
                switch (option) {
                    case "0":
                        menu2(id);
                        break;
                    case "1":
                        menuSpaces();
                        break;
                    case "2":
                        menuWorkshops();
                        break;
                    case "3":
                        menuRestaurant();
                        break;
                    case "4":
                        menuEquipament();
                        break;
                    case "5":
                        Consumption consum = new Consumption(0, id);
                        double value = consum.registerConsumption(100000);
                        JOptionPane.showMessageDialog(null, "Se ha registrado el consumo de entrenador personalizado.", 
                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        miClub.getMember(id).addBill(value, "Entrenador personalizado");
                        break;
                }
        }while(!option.equals("0"));
    }

    public void menuSpaces() throws IOException{
        try{
            double totalConsumption = 0;
            do{ 
                Consumption consum = new Consumption(totalConsumption, id);
                option = JOptionPane.showInputDialog(
                                            "Menu Espacios\n" +
                                            "Ingrese una opción: \n" +
                                            "0. Volver al menu anterior\n" +
                                            "1. Salon social: $150.000\n" +
                                            "2. Canchas: $50.000\n" +
                                            "3. Spa: $75.000\n" +
                                            "4. Piscinas $50.000\n" +
                                            "Para salir presione cancel. ");
                    switch (option) {
                        case "0":
                            
                            break;
                        case "1": 
                            totalConsumption += consum.registerConsumption(150000);
                            JOptionPane.showMessageDialog(null, "Se ha registrado la reserva de salon social.", 
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case "2": 
                            totalConsumption += consum.registerConsumption(50000);
                            JOptionPane.showMessageDialog(null, "Se ha registrado la reserva de canchas.", 
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case "3": 
                            totalConsumption += consum.registerConsumption(75000);
                            JOptionPane.showMessageDialog(null, "Se ha registrado la reserva de Spa.", 
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case "4": 
                            totalConsumption += consum.registerConsumption(50000);
                            JOptionPane.showMessageDialog(null, "Se ha registrado la reserva de Piscina.", 
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción inválida.", 
                                                    "Aviso", JOptionPane.ERROR_MESSAGE);
                            break;                    
                    }
            }while(!option.equals("0"));
            miClub.getMember(id).addBill(totalConsumption, "Espacios");
            menuServices();
        } catch (NullPointerException e) {
                
            JOptionPane.showMessageDialog(null, "Error interno: puntero nulo.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
        }     
    }
    public void menuWorkshops() throws IOException{
        try{
            double totalConsumption = 0;
            do{
                Consumption consum = new Consumption(totalConsumption, id);
                option = JOptionPane.showInputDialog(
                                            "Menu Talleres\n" +
                                            "Ingrese una opción: \n" +
                                            "0. Volver al menu anterior\n" +
                                            "1. Cocina internacional:  $100.000\n" +
                                            "2. Meditación: $80.000\n" +
                                            "3. Baile latino: $120.000\n" +
                                            "4. Arte y creatividad: $90.000\n" +
                                            "5. Fitness funcional: $110.000\n" +
                                            "Para salir presione cancel. ");
                    switch (option) {
                        case "0":
                            
                            break;
                        case "1": 
                            totalConsumption += consum.registerConsumption(100000);
                            JOptionPane.showMessageDialog(null, "Se ha registrado el taller: Cocina internacional.", 
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case "2": 
                            totalConsumption += consum.registerConsumption(80000);
                            JOptionPane.showMessageDialog(null, "Se ha registrado el taller: Meditación.", 
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case "3": 
                            totalConsumption += consum.registerConsumption(120000);
                            JOptionPane.showMessageDialog(null, "Se ha registrado el taller: Baile latino.", 
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case "4": 
                            totalConsumption += consum.registerConsumption(90000);
                            JOptionPane.showMessageDialog(null, "Se ha registrado el taller: Arte y creatividad.", 
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case "5": 
                            totalConsumption += consum.registerConsumption(110000);
                            JOptionPane.showMessageDialog(null, "Se ha registrado el taller: Fitness funcional.", 
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción inválida.", 
                                                    "Aviso", JOptionPane.ERROR_MESSAGE);
                            break;  
                    }
            }while(!option.equals("0"));
            miClub.getMember(id).addBill(totalConsumption, "Talleres");
            menuServices();
        } catch (NullPointerException e) {
                
            JOptionPane.showMessageDialog(null, "Error interno: puntero nulo.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
        } 
    }
    public void menuRestaurant() throws IOException{
        try{
            double totalConsumption = 0;
            do{
                Consumption consum = new Consumption(totalConsumption, id);
                option = JOptionPane.showInputDialog(
                                            "Menu Espacios\n" +
                                            "Ingrese una opción: \n" +
                                            "0. Volver al menu anterior\n" +
                                            "1. Desayuno:  $15.000\n" +
                                            "2. Almuerzo:  $25.000\n" +
                                            "3. Cena:   $30.000\n" +
                                            "4. Snack:    $10.000\n" +
                                            "5. Gaseosa:   $5.000\n" +
                                            "6. Cerveza:    $6.000\n" +
                                            "Para salir presione cancel. ");
                    switch (option) {
                        case "0":
                            
                            break;
                        case "1": 
                            totalConsumption += consum.registerConsumption(15000);
                            JOptionPane.showMessageDialog(null, "Se ha registrado el consumo de desayuno.", 
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case "2": 
                            totalConsumption += consum.registerConsumption(25000);
                            JOptionPane.showMessageDialog(null, "Se ha registrado el consumo de almuerzo.", 
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case "3": 
                            totalConsumption += consum.registerConsumption(30000);
                            JOptionPane.showMessageDialog(null, "Se ha registrado el consumo de cena.", 
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case "4": 
                            totalConsumption += consum.registerConsumption(10000);
                            JOptionPane.showMessageDialog(null, "Se ha registrado el consumo de snack.", 
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case "5": 
                            totalConsumption += consum.registerConsumption(5000);
                            JOptionPane.showMessageDialog(null, "Se ha registrado el consumo de gaseosa.", 
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case "6": 
                            totalConsumption += consum.registerConsumption(6000);
                            JOptionPane.showMessageDialog(null, "Se ha registrado el consumo de cerveza.", 
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción inválida.", 
                                                    "Aviso", JOptionPane.ERROR_MESSAGE);
                            break;  
                    }
            }while(!option.equals("0"));
            miClub.getMember(id).addBill(totalConsumption, "Restaurante");
            menuServices();
        } catch (NullPointerException e) {
                
            JOptionPane.showMessageDialog(null, "Error interno: puntero nulo.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
        } 
    }
    public void menuEquipament() throws IOException{
        try{
            double totalConsumption = 0;
            do{
                Consumption consum = new Consumption(totalConsumption, id);
                option = JOptionPane.showInputDialog(
                                            "Menu Espacios\n" +
                                            "Ingrese una opción: \n" +
                                            "0. Volver al menu anterior\n" +
                                            "1. Balon:  $10000\n" +
                                            "2. Combo Tenis:  $20000\n" +
                                            "3. Combo pin pon:   $15000\n" +
                                            "4. Combo Golf:   $45000\n" +
                                            "5. Combo Piscina:   $30000\n" +
                                            "Para salir presione cancel. ");
                switch (option) {
                    case "0":
                        
                        break;
                    case "1": 
                        totalConsumption += consum.registerConsumption(10000);
                        JOptionPane.showMessageDialog(null, "Se ha registrado el alquiler de: Balon", 
                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case "2": 
                        totalConsumption += consum.registerConsumption(20000);
                        JOptionPane.showMessageDialog(null, "Se ha registrado el alquiler de: Combo Tenis", 
                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case "3": 
                        totalConsumption += consum.registerConsumption(15000);
                        JOptionPane.showMessageDialog(null, "Se ha registrado el alquiler de: Combo pin pon", 
                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case "4": 
                        totalConsumption += consum.registerConsumption(45000);
                        JOptionPane.showMessageDialog(null, "Se ha registrado el alquiler de: Combo Golf", 
                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case "5": 
                        totalConsumption += consum.registerConsumption(30000);
                        JOptionPane.showMessageDialog(null, "Se ha registrado la compra de: Combo Piscina", 
                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida.", 
                                                "Aviso", JOptionPane.ERROR_MESSAGE);
                        break;  
                    }
            }while(!option.equals("0"));
            miClub.getMember(id).addBill(totalConsumption, "Equpamento");
            menuServices();
        } catch (NullPointerException e) {
                    
            JOptionPane.showMessageDialog(null, "Error interno: puntero nulo.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
        } 
    }
    
}
