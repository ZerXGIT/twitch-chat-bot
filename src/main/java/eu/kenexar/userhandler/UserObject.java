package eu.kenexar.userhandler;

import java.util.ArrayList;

public class UserObject {

    private static final ArrayList<UserObject> Users = new ArrayList<>();
    private String Name;
    private String Token;

    public UserObject(String Name, String Token) {
        this.Name = Name;
        this.Token = Token;
    }

    public UserObject() {
    }

    public static ArrayList<UserObject> getUsers() {
        return Users;
    }

    public static String getToken(String Name) throws NullPointerException {
        for (UserObject user : Users)
            if (user.getName().equalsIgnoreCase(Name)) return user.getToken();

        return "";
    }

    public void addUser(String Name, String Token) {
        Users.add(new UserObject(Name, Token));
    }

    public String getName() {
        return Name;
    }

    public String getToken() {
        return Token;
    }


}
