import com.sun.tools.javac.Main;

import java.rmi.*;
import java.util.Locale;
import java.util.Scanner;

public class TTTClient {
    private Scanner keyboardSc= new Scanner(System.in);
    public static String user[]={"",""};
    public static int userId=0;
    public static Game game = null;
    

    public static void main(String args[]) throws Exception {
        TTTService tttService = null;
        boolean run = true;
        boolean loggedIn = false;

        try{
            tttService = (TTTService) Naming.lookup("rmi://" + "localhost" +":"+3001+"/TTTService");
            System.out.println("Found server");
            game = new Game(tttService);

        }catch(RemoteException e) {System.out.println("[REMOTE]:" + e.getMessage()); System.exit(0);}
        catch(Exception e) {System.out.println("[Exception]:" + e.getMessage());System.exit(0);}

        Scanner input = new Scanner(System.in);
        MainMenu mainMenu = new MainMenu(game);
        mainMenu.startApp(game);
        /*while(run) {
            //mainMenu.startApp();
            loggedIn = TTTGame(loggedIn);
            System.out.println("Do you wish to play again? (y/n): ");
            String answer = input.nextLine();
            if (answer.toLowerCase(Locale.ROOT).equals("n"))
                run = false;
        }*/
    }

    /*static boolean TTTGame (boolean loggedIn) throws RemoteException{
        int choice=0, gameLevel=0;
        boolean request=false;

        // 
        if(!loggedIn){
            // login painel
            choice = game.loginPainel();
            if (choice==1){
                do{
                    game.login();
                    user = game.getUserInfo();
                    userId =game.validate(user[0], user[1]);
                    if(userId==-1){
                        System.out.println("[ERROR] User not Found.");
                    }
                    else
                        System.out.println("[SUCESS] you are logged in.");
                }while(userId==-1);
                loggedIn = true;
            }
            else{
                game.signUp();
                user = game.getUserInfo();
                userId = game.addUser(user[0], user[1]);
                loggedIn = true;
            }
        }
        // System.out.println(g.getAllUserInfo(userId));
        
        // choise whit card to use
        choice = game.multiplayerChoice();
        if (choice==1){
            gameLevel = game.levelChoice();
            game.cardChoice(userId);
            game.setMyBoard();
        }
        else if (choice == 3){
            request=game.getRequest();
            // game.setMyCard();
        }
        else{
            game.allActiveUser();
            game.cardChoice(userId);
            game.sendMatchRequest();
            game.waitOpponent();
        }
        //     // onde esta 1 futuramente tera o id do oponente
        
        if((loggedIn && choice != 3)||(loggedIn && choice == 3 && request)){
            game.playGame();
            game.congratulate();
        }
        else
            System.out.println("[NOTIFICATION] There is no game for you");
        return loggedIn;
    }*/
}