package flyxpert.flyxpert;

import java.util.ArrayList;

public class User {
    private String userName;
    private String password;
    private String email;
    private boolean isAdmin;
    public static User currentUser;
    public static ArrayList<User> userList = new ArrayList<User>();

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.isAdmin = false;
    }
    public User(User user){
        this.userName = user.userName;
        this.password = user.password;
        this.email = user.email;
        this.isAdmin = false;
    }
    public static String searchForUser(String username) {
        UserTrie userTrie = UserTrie.getInstance();
        return userTrie.getPassword(username);
    }

    public static boolean exists(String userName) {
        if (userName == null)
            return false;
        UserTrie userTrie = UserTrie.getInstance();
            if(userTrie.search(userName) != null)
                return true;
        return false;
    }
    public boolean equals(User u) {
        return (this.userName.equals(u.userName) || this.email.equals(u.userName)) && this.password.equals(u.password);
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIsAdmin(boolean state)
    {
        this.isAdmin = state;
    }
    public boolean getIsAdmin()
    {
        return this.isAdmin;
    }
}
