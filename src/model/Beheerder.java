package model;

/**
 * Created by jonathan on 7-9-15.
 */
public class Beheerder extends User {


    public Beheerder(String name, String password) {
        super(name, password);
    }

    @Override
    public String toString() {
        return "Beheerder " + super.toString();
    }

}
