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
    private String enimyInfo[] = {"", ""};// to store the current enimy info 
    private boolean multiPlayer = false; // is the user wants to play whit pc or whit someone
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

    // to get all User info
    public String getAllUserInfo(int id) throws RemoteException{
        return ttt.getUserInfo(id);
    }

    // public show all active user
    public void allActiveUser() throws RemoteException{
        String allUser[] = ttt.allActiveUser().split("\n");
        String userInfo[] = {"", ""};
        int nrActiveUsers = allUser.length, i=1, choice = -1;

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
        System.out.println("\nEnimy chosen: "+enimyInfo[1]+"\n");

        // System.out.println(allUser[0]);
        // System.out.println(ttt.allActiveUser());// where i'm going to wock in how the play chose his oponnent
    }

    // to allow the user to chose if he wants to playe whit the pc or whit someone else
    public int multiplayerChoice()throws RemoteException{
        int choice = -1;
        do {
            System.out.println("Chose or card to play: \n");
            System.out.println("\t< 1 > Play whit Computer\n\t< 2 > Play whit someone");
            System.out.print("\n  Choice: ");
            choice= keyboardSc.nextInt();
            if (choice == 1)
                multiPlayer = false;
            else if (choice == 2)
                multiPlayer = true;
            else
                System.out.println("[ERROR] choice not valide");
            
        }while(choice <1 && choice >2);
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
        return userId;
    }

    // method to allow the user to chose what player he is.
    public void cardChoice(int idPlayer,int idOpennet)  throws RemoteException{
        int opennentChoice=-1;
        if(idOpennet==-1){
            do {
                System.out.println("Chose or card to play: \n");
                System.out.println("\t< 0 > O\n\t< 1 > X");
                System.out.println("  Choice: ");
                myCard = keyboardSc.nextInt();
                if(myCard <0 && myCard >1)
                    System.out.println("[ERROR] choice not valide.");
            }while(myCard <0 && myCard >1);
        }
        else if(idOpennet!=-1 && ttt.ableToChose(idPlayer, idOpennet)){
            do {
                System.out.println("Chose or card to play: ");
                System.out.println("\t< 1 > X\n\t< 2 > O");
                System.out.println("\tChoice: ");
                myCard= keyboardSc.nextInt();
                if(myCard <1 && myCard >2)
                    System.out.println("[ERROR] choice not valide.");
            }while(myCard <1 && myCard >2);
        }
        else{
            System.out.println("Is upon the Enimy to chose the Card firts.\nPlease wait for it.");
            while (opennentChoice!=-1){
                opennentChoice = ttt.getUserValue(idOpennet, "myCard");
                if (opennentChoice==1){
                    myCard = 0;
                }
                else{
                    myCard=1;
                }
                System.out.println("...");
            }
        }
        ttt.updateUser(idPlayer, "myCard",myCard); 
    }

    public int readPlay() throws RemoteException{
        int play=0;
        char possibleMoves[] = {1, 2, 3, 4,5 ,6 , 7, 8, 9};
        do {
            System.out.printf("\n  Player %d,\n  Please enter the number of the square ,"+ "\n  where you want to place your %c (or 0 to refresh the board): \n\tYour choice is: ", player, (player == 1) ? 'X' : 'O');

            if (player==myCard){
                play = keyboardSc.nextInt();
                System.out.println(play);
                if(play == 11){
                    System.out.println("foi um 11");
                    ttt.removeLastPlay(userBoardReference);
                    play = 0;
                }
            }
            else{
                if (!multiPlayer){
                    possibleMoves = ttt.getPossibleMoves(userBoardReference);
                    play = computer.makePlay(level, possibleMoves);
                    System.out.println(play);
                    if(play == 11){
                        System.out.println("foi um 11");
                        ttt.removeLastPlay(userBoardReference);
                        play = 0;
                    }
                }
                else{

                }   
            }
        } while (play > 9 || play < 0);
        return play;
    }

    public void playGame() throws RemoteException{
        int play;
        boolean playAccepted;
        do {
            player = ++player % 2;
            System.out.println("vez de: "+player);
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
    }

    public void congratulate() throws RemoteException{
        if (winner == 2)
            System.out.printf("\nHow boring, it is a draw\n");
        else
            System.out.printf("\nCongratulations, player %d, YOU ARE THE WINNER!\n",winner);
    }
}