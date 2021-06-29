import com.sun.tools.javac.Main;

import java.rmi.*;

public class TTTClient {
    public static Game game = null;

    public static void main(String args[]) throws Exception {
        TTTService tttService = null;
        try{
            tttService = (TTTService) Naming.lookup("rmi://" + "localhost" +":"+3001+"/TTTService");
            System.out.println("Found server");
            game = new Game(tttService);

        }catch(RemoteException e) {System.out.println("[REMOTE]:" + e.getMessage()); System.exit(0);}
        catch(Exception e) {System.out.println("[Exception]:" + e.getMessage());System.exit(0);}

        MainMenu mainMenu = new MainMenu(game);
        mainMenu.startApp(game);
    }
}