package com.mycompany.projectsc1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class ChargedDates {

    static String archivoCSV = "src\\main\\java\\com\\mycompany\\projectsc1\\target\\resources\\dates.csv";
    
    public void charge() throws IOException{
        File fileCsv = new File(archivoCSV);
        BufferedReader br = new BufferedReader(new FileReader(fileCsv));
        Club club = new Club();
        String line;
        
        while ((line = br.readLine()) != null) {
            String[] dates = line.split(",");

            if (dates.length != 3) {
                JOptionPane.showMessageDialog(null, "Error: Línea con formato incorrecto:" + line,
                                             "Aviso", JOptionPane.WARNING_MESSAGE);
                System.out.println();
                continue;
            }
        
            if(club.exists(dates[0])){
                JOptionPane.showMessageDialog(null, "El socio con esta cedula ya existe.",
                                             "Aviso", JOptionPane.WARNING_MESSAGE);
                continue;
            }

            if(dates[2].equalsIgnoreCase("Vip") && club.getVip() >= 3){
                JOptionPane.showMessageDialog(null, "El club alcanzó el límite de socios VIP.",
                                             "Aviso", JOptionPane.WARNING_MESSAGE);
                continue;
            }

            Member newMember = new Member(dates[1], dates[0], dates[2]);
            if (dates[2].equalsIgnoreCase("Vp")) {
                newMember.setAvailableFounds(100000);
            } else {
                newMember.setAvailableFounds(50000);
            }
            
            club.setMembers(dates[1], newMember);

            if(dates[2].equalsIgnoreCase("Vip")){
                club.setVip(1);
            }
        }
        br.close();
    }
}
