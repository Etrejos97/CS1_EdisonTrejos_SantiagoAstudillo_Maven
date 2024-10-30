/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectsc1;

/**
 *
 * @author Edison
 */
public class AuthorizedPersons extends ClubMember{
    private String idMember;


    public AuthorizedPersons(String name, String id, String idMember) {
        super(name,id);
        this.idMember = idMember;
    }

    @Override
    public String toString() {
        return  "Cedula: "+ id + "\nNombre: " + name  +
                "\nCedula del socio al que esta afiliado: " + idMember;
    }
}
