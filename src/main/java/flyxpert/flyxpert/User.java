package flyxpert.flyxpert;

import java.util.ArrayList;

public class User
{
    private String userName, password, email;

    public static ArrayList<User> userList = new ArrayList<User>();

    public User(String userName, String email, String password)
    {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    public static User isFound(User u)
    {
        for(User user : userList)
        {
            if(user.equals(u))
                return u;
        }
        return null;
    }

    public static boolean exists(String userName)
    {
        for(User user : userList)
            if(user.userName.equals(userName) || user.email.equals(userName))
                return true;
        return false;
    }
    public boolean equals(User u)
    {
        return (this.userName.equals(u.userName) || this.email.equals(u.userName)) && this.password.equals(u.password);
    }
    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }


}
