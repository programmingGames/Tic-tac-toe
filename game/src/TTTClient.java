import java.rmi.*;
import java.util.Locale;
import java.util.Scanner;

public class TTTClient {
    private Scanner keyboardSc= new Scanner(System.in);
    public static String user[]={"",""};
    public static int userId=0;
    public static Game game = null;
    public static Menu menu = new Menu();
    

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
        while(run) {
            loggedIn = TTTGame(loggedIn);
            System.out.println("Do you wish to play again? (y/n): ");
            String answer = input.nextLine();
            if (answer.toLowerCase(Locale.ROOT).equals("n"))
                run = false;
        }
    }
    public void menusChoice(){
        int esc = 0;
        System.out.println("\n ------ TTT ------");
        System.out.println("\n Escolha: ");
        esc = keyboardSc.nextInt();
        switch (esc){
            case 0:

        }
    }

    static boolean TTTGame (boolean loggedIn) throws RemoteException{
        int choice=0;

        // 
        if(!loggedIn){
            // login painell
            choice = menu.loginPainel();
            if (choice==1){
                do{
                    menu.login();
                    user = menu.getUserInfo();
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
                menu.signUp();
                user = menu.getUserInfo();
                userId = game.addUser(user[0], user[1]);
                loggedIn = true;
            }
        }
        // System.out.println(g.getAllUserInfo(userId));
        
        // choise whit card to use
        choice = game.multiplayerChoice();
        if (choice==1)
            game.cardChoice(userId);
        else if (choice == 3){
            game.getRequest();
            // game.setMyCard();
        }
        else{
            game.allActiveUser();
            game.cardChoice(userId);
            game.sendMatchRequest();
            game.waitOpponent();
        }
        //     // onde esta 1 futuramente tera o id do oponente
    
    
        
        if(loggedIn){
            game.playGame();
            game.congratulate();
        }
        return loggedIn;
    }
}