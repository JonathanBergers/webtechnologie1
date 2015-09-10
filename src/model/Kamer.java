package model;

/**
 * Created by jonathan on 7-9-15.
 */
public class Kamer {


    private int huurprijs;
    private final int grootte;
    private final String plaats;
    private final Verhuurder verhuurder;

    public Kamer(int huurprijs, int grootte, String plaats, Verhuurder verhuurder) {
        this.huurprijs = huurprijs;
        this.grootte = grootte;
        this.plaats = plaats;
        this.verhuurder = verhuurder;
    }
    public boolean hasVerhuurder(final Verhuurder v){

        return verhuurder.equals(v);
    }
    public boolean canBeRented(int huurprijs){
        return(huurprijs>=this.huurprijs);
    }
    public boolean isLoacatedAt(String plaats){
        return(plaats.equalsIgnoreCase(this.plaats));
    }
    public boolean hasSize(int grootte){
        return(grootte<=this.grootte);
    }

    @Override
    public String toString() {
        return "Kamer: huurprijs = "+huurprijs+", grootte = "+grootte+" en plaats = "+plaats;
    }
}
