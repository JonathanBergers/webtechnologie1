package model;

/**
 * Created by jonathan on 7-9-15.
 */
public class Verhuurder extends User {


    public Verhuurder(String name, String password) {
        super(name, password);
    }


    @Override
    public String toString() {
        return "Verhuurder " + super.toString();
    }
}
