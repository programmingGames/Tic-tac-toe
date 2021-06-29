import java.rmi.RemoteException;

public class Game {
    private TTTService ttt;
    private Computer computer;
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
    }

    public void setMyBoard() throws RemoteException{
        this.userBoardReference = ttt.getUserValue(userId, "boardReference");
    }

    // waiting for opponent to enter in the match
    public boolean waitOpponent() throws RemoteException{
        return ttt.waitingOpponent(matchId);
    }

    public void setOpponentPlayToDefault() throws RemoteException{
        ttt.setPlayToDefault(this.matchId);
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
            this.iRequest = true;
            this.userBoardReference = ttt.getMatchBoard(this.matchId);
            this.multiPlayer = 2;
        }
        /*else{
            System.out.println("[ERROR] Request already exist");
        }*/
    }

    public String[] getAllMatchRequest() throws RemoteException{
        return ttt.getRequests(this.userId).split("\n");
    }

    public void acceptRequest(String enemy) throws RemoteException{
        matchInfo = enemy.split(" ");// to take the enimy chosen.
        userBoardReference = Integer.parseInt(matchInfo[1]);
        matchId = Integer.parseInt(matchInfo[2]);

        char card = ttt.acceptRequest(matchId);

        // setting the player card
        if(Character.compare(card, 'X') == 0){
            this.myCard = 0;
        }
        else{
            this.myCard = 1;
        }

        multiPlayer=2;
        iRequest = false;
    }

    // to get all User info
    public String getAllUserInfo() throws RemoteException{
        return ttt.getUserInfo(this.userId);
    }

    public int getMatchId(){
        return this.matchId;
    }

    // to get all the active users
    public String[] getAllActiveUser() throws  RemoteException{
        return ttt.allActiveUser(userId).split("\n");
    }

    public void setEnimyInfo(String[] enimyInfo) {
        this.enimyInfo = enimyInfo;
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
    public void setcardChoice(int card)  throws RemoteException{
        this.myCard = card;
        if (this.multiPlayer==1)
            this.computer = new Computer(this.myCard);
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

    public int getOpponentPlay() throws RemoteException {
        return ttt.waitingPlayerToPlay(this.matchId, this.userId);
    }

    public void makeMyPlay(int play) throws RemoteException{
        ttt.makeMyPlay(this.matchId, play);
    }

    public void restartBoard() throws RemoteException{
        ttt.restart(userBoardReference, userId);
        this.player = 1;
    }

    public int getComputerPlay() throws  RemoteException{
        char possibleMoves[] = {1, 2, 3, 4, 5 ,6 , 7, 8, 9};
        possibleMoves = ttt.getBoard(userBoardReference);
        return computer.makePlay(level, possibleMoves);
    }

    public boolean play(int play) throws RemoteException{
        boolean accepted;
            accepted =  ttt.play( --play / 3, play % 3, player, userBoardReference);
        return accepted;
    }

    public int getWinner() throws  RemoteException{
        return ttt.checkWinner(userBoardReference);
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

    public boolean isMatchFinished(int matchId) throws RemoteException{
        return ttt.isMatchEnded(matchId, this.userId);
    }

}