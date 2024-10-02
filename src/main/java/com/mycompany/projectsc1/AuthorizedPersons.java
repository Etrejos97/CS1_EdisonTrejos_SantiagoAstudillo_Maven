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
    private Member member;


    public AuthorizedPersons(String name, String id, Member member) {
        super(name,id);
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return  "Cedula: "+ id + "\nNombre: " + name  +
                "\nCedula del socio al que esta afiliado: " + member.getId();
    }
}
