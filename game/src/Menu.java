import java.rmi.RemoteException;
import java.util.Scanner;

public class Menu {
    int curent_menu=0;
    private Scanner keyboardSc= new Scanner(System.in);
    private String user, passwd;

    // chose if login or sign up
    public int loginPainel() throws RemoteException{
        int esc=0;
        do{
            System.out.println("\n ------ TTT ------");
            System.out.println("\n Escolha o metedo de login: ");
            System.out.println("\n\t< 1 > Login \n\t< 2 > Sign up");
            System.out.print("\n Escolha: ");
            esc = keyboardSc.nextInt();

        }while(esc<1 && esc>2);
        return esc;
    }
    // allow the user to enter his info
    public void login() throws RemoteException{
        String pause;
        System.out.println("\n ------ TTT Login ------");
        do{
            pause = keyboardSc.nextLine();
            System.out.print("\n User: ");
            user = keyboardSc.nextLine();
            System.out.print("\n Password: ");
            passwd = keyboardSc.nextLine();
            if(user.equals("\n")||user.equals("")||passwd.equals("")){
                System.out.println("Values with erro.\nPress [ENTER] to continue");
            }
        }while(user.equals("\n")||user.equals("")||passwd.equals(""));
            
    }
    // method to get signed up
    public void signUp(){
        String repasswd;
        String pause;
        do{
            pause = keyboardSc.nextLine();
            System.out.print("\n User: ");
            user = keyboardSc.nextLine();
            System.out.print("\n Password: ");
            passwd = keyboardSc.nextLine();
            System.out.print("\n Re-enter Password: ");
            repasswd = keyboardSc.nextLine();
            if(!repasswd.equals(passwd)||user.equals("\n")||user.equals("")){
                System.out.println("\nPassword don't match.\nPress [ENTER] to continue");
            }
        }while(!repasswd.equals(passwd)||user.equals("\n")||user.equals(""));
    }
    // method to get the user input
    public String[] getUserInfo(){
        String userInfo[] = {user, passwd};
        return userInfo;
    }
}
