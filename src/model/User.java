package model;

import java.io.Serializable;

/**
 * Created by jonathan on 3-9-15.
 */
public abstract class User implements Serializable{

    private String name, password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public boolean hasNameAndPass(String username, String pass){

        return name.equals(username) && password.equals(pass);
    }

    @Override
    public String toString() {
        return "Naam: " + name + " pass: " + password;
    }



}
