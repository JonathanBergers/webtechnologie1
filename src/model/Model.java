package model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by falco on 10-9-15.
 */
public class Model implements Serializable {



    private List<User> users;
    private List<Kamer> rooms;


    public Model(List<User> users, List<Kamer> rooms) {
        this.users = users;
        this.rooms = rooms;
    }



    public List<User> getUsers(){

        return users;
    }

    /**returns null if there is no user with the givven name
     *
     * @param username
     * @return
     */
    public User getUserByName(final String username){

        for(User u: users){

            if(u.hasName(username)){

                return u;
            }

        }

        return null;


    }


    public List<Kamer> getRooms() {
        return rooms;
    }

    /**gets the room by verhuurder
     *
     * @param user
     * @return null if there is no room for this verhurder
     */
    public Kamer getRoomByVerhuurder(final Verhuurder user){

        for(Kamer k: rooms){
            if(k.hasVerhuurder(user)){
                return k;
            }

        }

        return null;
    }

    public void addUser(User u){

        getUsers().add(u);

    }


    public boolean verifyUser(final String username, final String password){

        for(User u: users){

            if(u.hasNameAndPass(username, password)){
                return true;
            }
        }

        return false;

    }
    public void addRoom(Kamer k){
        getRooms().add(k);
    }


}
