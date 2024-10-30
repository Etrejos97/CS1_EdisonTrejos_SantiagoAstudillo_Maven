

package com.mycompany.projectsc1;

import java.io.IOException;

/**
 *
 * @author Edison
 */
public class ProjectSC1 {

    public static void main(String[] args) throws IOException {
        
        ChargedDates ch = new ChargedDates();
        ch.charge();
        Menu menu = new Menu();
        menu.menu1(); 
        
    }
}
