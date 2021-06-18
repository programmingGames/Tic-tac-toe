import java.util.Scanner;
import java.rmi.RemoteException;

public class Game {
    private TTTService ttt;
    private Computer computer = new Computer();
    private Scanner keyboardSc= new Scanner(System.in);
    private int winner = 0;
    private int player = 1; 
    private int userId = -1;
    private int userBoardReference = -1;
    private String matchInfo[] = {"","","", "", "", ""};
    private String user, passwd;
    private int matchId = -1; // the id of the multiplayer match going on
    private String enimyInfo[] = {"", ""};// to store the current enimy info 
    private int multiPlayer = 0; // is the user wants to play whit pc or whit someone (pc = 1, someone=2)
    private boolean iRequest = false;
    private int level = 1; // the difficulty by playing with the computer
    private int myCard; // myCard = 1 -> means that the user will use "X",
                        //  and myCard = 2 means that the player will use "O"
    public Game(TTTService _ttt) throws RemoteException {

        ttt = _ttt;
        keyboardSc = new Scanner(System.in);
        ttt.initializingApp();
    }

    // return the eminy id
    public int getEnimyId(){
        return Integer.parseInt(enimyInfo[0]);
    }

    public void setMyBoard() throws RemoteException{
        this.userBoardReference = ttt.getUserValue(userId, "boardReference");
    }

    // waiting for opponent to enter in the match
    public boolean waitOpponent() throws RemoteException{
        return ttt.waitingOpponent(matchId);
    }

    // create a game request
    public void sendMatchRequest() throws RemoteException{
        char card;
        if(this.myCard==1)
            card = 'O';
        else
            card = 'X';
    
        this.matchId =  ttt.createRequest(this.userId, Integer.parseInt(this.enimyInfo[0]),card);
        if(this.matchId>=0){
            System.out.println("[UPDATE] Request created");
            this.iRequest = true;
            this.multiPlayer = 2;
        }
        else{
            System.out.println("[ERROR] Request already exist");
        }
        System.out.println("Board: "+this.userBoardReference);
        System.out.println("Match: "+this.matchId);
    }
    
    // to get all the request the user have
    public boolean getRequest() throws RemoteException{
        
        String requests = ttt.getRequests(userId);
        System.out.println(requests);
        String allRequest[] = requests.split("\n");
        
        if (!requests.equals("")){
            String requestInfo[] = {"","", ""};
            int nrActiveUsers, i=1, choice = -1;

            
            i=1; // to restart the counter
            System.out.println("All match Requests for you: \n");
            // to display all the user on the screen to be chosen the enimy
            for (String request: allRequest){
                requestInfo = request.split(" ");
                System.out.println("\t < "+i+" > "+requestInfo[0]);
                i++;
            }
            nrActiveUsers = i;

            do{
                
                System.out.print("\n  Your choice: ");
                choice = keyboardSc.nextInt();
                if(choice<0 || choice > nrActiveUsers)
                    System.out.println("[ERROR] Invalide choice.");

            }while(choice< 0 || choice > nrActiveUsers);
            matchInfo = allRequest[choice-1].split(" ");// to take the enimy chosen.
            userBoardReference = Integer.parseInt(matchInfo[1]);
            System.out.println(allRequest[choice-1]);
            matchId = Integer.parseInt(matchInfo[2]);

            char card = ttt.acceptRequest( matchId);

            // setting the player card
            System.out.println(card);
            if(Character.compare(card, 'X') == 0){
                this.myCard = 0;
            }
            else{    
                this.myCard = 1;
            }

            multiPlayer=2;
            iRequest = false;
            System.out.println("\nEnimy chosen: "+matchInfo[0]+"\n");
            System.out.println("Board: "+userBoardReference);
            System.out.println("Match: "+matchId);
            return true;
        }
        else{
            System.out.println("[NOTIFICATION] No request avalable for you.");
            return false;
        }
    }

    // to get all User info
    public String getAllUserInfo(int id) throws RemoteException{
        return ttt.getUserInfo(id);
    }

    public int getMatchId(){
        return this.matchId;
    }

    // to get all the active users
    public String[] getAllActiveUser() throws  RemoteException{
        return ttt.allActiveUser(userId).split("\n");
    }

    public String[] getEnimyInfo() {
        return enimyInfo;
    }

    public void setEnimyInfo(String[] enimyInfo) {
        this.enimyInfo = enimyInfo;
    }

    // public show all active user
    public void allActiveUser() throws RemoteException{
        String allUser[] = ttt.allActiveUser(userId).split("\n");
        String userInfo[] = {"", ""};
        int nrActiveUsers = allUser.length, i=1, choice = 0;

        do{
            i=1; // to restart the counter
            System.out.println("All Active User's: \n");
            // to display all the user on the screen to be chosen the enimy
            for (String user: allUser){
                userInfo = user.split(" ");
                System.out.println("\t < "+i+" > "+userInfo[1]);
                i++;
            }
            System.out.print("\n  Your choice: ");
            choice = keyboardSc.nextInt();
            if(choice>0&&choice>nrActiveUsers)
                System.out.println("[ERROR] Invalide choice.");

        }while(choice>0&&choice>nrActiveUsers);
        enimyInfo = allUser[choice-1].split(" ");// to take the enimy chosen.
        System.out.println("\nEnimy chosen: "+enimyInfo[0]+"\n");

        // System.out.println(allUser[0]);
        // System.out.println(ttt.allActiveUser());
    }


    // to allow the user to chose if he wants to play whit the pc or whit someone else
    public int multiplayerChoice()throws RemoteException{
        int choice = -1;
        do {
            System.out.println("Chose or card to play: \n");
            System.out.println("\t< 1 > Play whit Computer\n\t< 2 > Play whit someone\n\t< 3 > requests");
            System.out.print("\n  Choice: ");
            choice= keyboardSc.nextInt();
            if (choice == 1){
                multiPlayer = 1;
                matchId = -1;
                player = 1;
            }
            else if (choice == 2 || choice == 3)
                multiPlayer = 2;
            else
                System.out.println("[ERROR] choice not valide");
            
        }while(choice <1 && choice >3);
        return choice;
    }

    // Method to validate the user
    public int validate(String user, String passwd) throws  RemoteException{
        userId = ttt.validateUser(user, passwd);
        userBoardReference = ttt.getUserValue(userId, "boardReference");
        return userId;
    }

    // to add a new user
    public int addUser(String user, String passwd) throws RemoteException{
        userId = ttt.addUser(user, passwd);
        userBoardReference = ttt.getUserValue(userId, "boardReference");
        System.out.println(userId+" "+userBoardReference);
        return userId;
    }

    // method to allow the user to chose what player he is.
    public void setcardChoice(int card)  throws RemoteException{
        this.myCard = card;
        ttt.updateUser(userId, "myCard",myCard);
    }

    public int getMultiPlayer() {
        return multiPlayer;
    }

    public void setMultiPlayer(int multiPlayer) {
        this.multiPlayer = multiPlayer;
    }


    // method to get the player.
    public int getPlayer()  throws RemoteException{
        return this.player ;
    }
    public void setPlayer(){
        this.player = ++this.player % 2;
    }

    public int getMyCard(){
        return this.myCard;
    }

    // 
    public int readPlay() throws RemoteException{
        
        int play=0;
        String pause;
        char possibleMoves[] = {1, 2, 3, 4, 5 ,6 , 7, 8, 9};
        do {

            System.out.printf("\n  Player %d,\n  Please enter the number of the square ,"+ "\n  where you want to place your %c (or 0 to refresh the board): \n\tYour choice is: ", player, (player == 1) ? 'X' : 'O');
            if (player==this.myCard){
                
                play = keyboardSc.nextInt();
                System.out.println(play);
                if(play == 11){
                    System.out.println("foi um 11");
                    ttt.removeLastPlay(userBoardReference);
                    play = 0;
                }
                if(multiPlayer == 2){
                    System.out.println("iewrgwjer play: "+play);
                    ttt.makeMyPlay(matchId, play);
                }
                
            }
            else{
                if (multiPlayer==1){
                    possibleMoves = ttt.getPossibleMoves(userBoardReference);
                    play = computer.makePlay(level, possibleMoves);
                    System.out.println(play);
                    if(play == 11){
                        System.out.println("foi um 11");
                        ttt.removeLastPlay(userBoardReference);
                        play = 0;
                    }
                }
                else if(multiPlayer==2){
                    System.out.println("\n[NOTIFICATION] Waiting player to play ");
                    do{
                        play = ttt.waitingPlayerToPlay(matchId, userId);
                    }while(play <= 0);
                    
                    System.out.println(play);
                    if(play == 11){
                        System.out.println("foi um 11");
                        ttt.removeLastPlay(userBoardReference);
                        play = 0;
                    }
                }

            }

        } while (play > 9 || play < 0);

        return play;
    }

    public void restartBoard() throws RemoteException{
        ttt.restart(userBoardReference, userId);
        this.player = 1;
    }

    public int getComputerPlay() throws  RemoteException{
        char possibleMoves[] = {1, 2, 3, 4, 5 ,6 , 7, 8, 9};
        possibleMoves = ttt.getPossibleMoves(userBoardReference);
        return computer.makePlay(level, possibleMoves);
    }

    public boolean play(int play) throws RemoteException{
        boolean accepted;
            accepted =  ttt.play( --play / 3, play % 3, player, userBoardReference);
        System.out.println(ttt.currentBoard(userBoardReference));
        return accepted;
    }

    public int getWinner() throws  RemoteException{
        return ttt.checkWinner(userBoardReference);
    }

    public void playGame() throws RemoteException{
        int play;
        boolean playAccepted;
        do {
            player = ++player % 2;
            
            // System.out.println("vez de: "+player+" macthID: "+matchId);
                do {
                    System.out.println(ttt.currentBoard(userBoardReference));
                    play = readPlay();
                    if (play != 0) {
                        playAccepted = ttt.play( --play / 3, play % 3, player, userBoardReference);
                        if (!playAccepted)
                            System.out.println("Invalid play! Try again.");
    
                    } else
                        playAccepted = false;
                        
                } while (!playAccepted);
            winner = ttt.checkWinner(userBoardReference);
            
        } while (winner == -1);
        ttt.restart(userBoardReference, userId);

        if (multiPlayer == 2){
            ttt.endMatch(matchId, this.userId); // ending the match
            iRequest = false;
            multiPlayer = 0;
            userBoardReference = ttt.getUserValue(userId, "boardReference");
            matchId = -1; // 

        }
        this.player = 1;
        congratulate();
    }

    public void congratulate() throws RemoteException{
        System.out.println("\tGame result: \n");
        if (winner == 2){
            System.out.printf("\n--------------------------------------");
            System.out.printf("\n--------------------------------------\n");
            System.out.printf("\n            There is a tie            \n");
            System.out.printf("\n--------------------------------------");
            System.out.printf("\n--------------------------------------\n\n");
            ttt.updateUser(userId, "nrEmpates", ttt.getUserValue(userId,"nrEmpates")+1);
        }
        else if (winner == this.myCard){
                System.out.printf("\n--------------------------------------");
                System.out.printf("\n--------------------------------------\n");
                System.out.printf("\n           You are the Winner         \n");
                System.out.printf("\n--------------------------------------");
                System.out.printf("\n--------------------------------------\n");
                ttt.updateUser(userId, "nrVitorias", ttt.getUserValue(userId,"nrVitorias")+1);
        }
        else{
                System.out.printf("\n--------------------------------------");
                System.out.printf("\n--------------------------------------\n");
                System.out.printf("\n               You lose               \n");
                System.out.printf("\n--------------------------------------");
                System.out.printf("\n--------------------------------------\n\n");
                ttt.updateUser(userId, "nrDerrotas", ttt.getUserValue(userId,"nrDerrotas")+1);
            }
    }

    // method to get the user input
    public String[] getUserInfo(){
        String userInfo[] = {user, passwd};
        return userInfo;

    }
    public void setMultiplayerInfoToDefault() throws RemoteException{
        ttt.endMatch(matchId, this.userId); // ending the match
        iRequest = false;
        multiPlayer = 0;
        userBoardReference = ttt.getUserValue(userId, "boardReference");
        matchId = -1; //
    }

    public void levelChoice(int level){
        this.level = level;
    }

    public void setCounterWin() throws RemoteException{
        ttt.updateUser(this.userId, "nrVitorias", ttt.getUserValue(this.userId, "nrVitorias")+1);
    }
    public void setCounterLose() throws RemoteException{
        ttt.updateUser(this.userId, "nrDerrotas", ttt.getUserValue(this.userId, "nrDerrotas")+1);
    }
    public void setCounterTie() throws RemoteException{
        ttt.updateUser(this.userId, "nrEmpates", ttt.getUserValue(this.userId, "nrEmpates")+1);
    }
}