import java.util.Scanner;
import java.rmi.*;

public class TTTClient {
    public static Game game = null;
    public static Scanner keyboard= new Scanner(System.in);

    public static void main(String args[]) throws Exception {
        TTTService tttService = null;
    	String ipServer = "";


        try{
            System.out.print("\tEnter Server IP Address: ");
            ipServer = keyboard.nextLine();
            tttService = (TTTService) Naming.lookup("rmi://" + ipServer +":"+3001+"/TTTService");
            System.out.println("Found server");
            game = new Game(tttService);

        }catch(RemoteException e) {System.out.println("[REMOTE]:" + e.getMessage()); System.exit(0);}
        catch(Exception e) {System.out.println("[Exception]:" + e.getMessage());System.exit(0);}

        MainMenu mainMenu = new MainMenu(game);
        mainMenu.startApp(game);
    }
}
