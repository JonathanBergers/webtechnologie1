package model;

/**
 * Created by jonathan on 7-9-15.
 */
public class Huurder extends User {

    public Huurder(String name, String password) {
        super(name, password);
    }

    @Override
    public String toString() {
        return "Huurder " + super.toString();
    }
}
