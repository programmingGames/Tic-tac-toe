import java.rmi.RemoteException;
import java.util.Scanner;

public class Menu {
    int curent_menu=0;

    private String user, passwd;

    public void setUserInfo(String Us){

    }

    // method to get the user input
    public String[] getUserInfo(){
        String userInfo[] = {user, passwd};
        return userInfo;
    }

}
