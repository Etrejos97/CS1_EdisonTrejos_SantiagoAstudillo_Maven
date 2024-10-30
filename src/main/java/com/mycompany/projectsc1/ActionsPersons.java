package com.mycompany.projectsc1;

interface ActionsPersons {
    public default  void add(){

    }
    public default  void delete(){

    }

    public default boolean exists(String id){
        return false;
    }

    public default boolean isFull(){
        
        return false;
           
    }

}
