package edu.mvc;

/**
 * Created by Admin on 18.11.2016.
 */
public  class Question {



    public   static enum TypeUser{Admin,User,Manager};
    private TypeUser typeUser;

    public TypeUser getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(TypeUser typeUser) {
        this.typeUser = typeUser;
    }

//    public static void main(String[] args) {
//
//        System.out.println(TypeUser.User);
//    }
}
