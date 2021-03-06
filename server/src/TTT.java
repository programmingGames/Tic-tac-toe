import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.rmi.server.UnicastRemoteObject;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.ArrayList;

public class TTT extends UnicastRemoteObject implements TTTinterface, TTTService{
    private LinkedList<TTT.play> Plays = new LinkedList<TTT.play>();
    private int idBoard=1, idMatch=0, userId=0;
    // list of all the board
    private LinkedList<TTT.Board> allBoard =new LinkedList<TTT.Board>();
    private ArrayList<Match> matches = new ArrayList<Match>();

    private static final long serialVersionUID = 4L;

    protected TTT() throws IOException {
/*TODO*/
    }

    public class Board {
        public char board[][] = {{'1','2','3'},
                                 {'4','5','6'},
                                 {'7','8','9'}};
        private int nextPlayer = 0;
        private int numPlays = 0;
    }

    private char boardRestart[][] = {
                {'1','2','3'}, /* Initial values are reference numbers */
                {'4','5','6'}, /* used to select a vacant square for */
                {'7','8','9'} /* a turn. */
        };

        public class play {
            public int row;
            public int column;
            public int player;
            public play(int _row, int _column, int _player){
                row = _row;
                column = _column;
                player = _player;
            }
            public int getRow(){
                return row;
            }

            public int getColumn(){
                return column;
            }
            public int getPlayer(){
                return player;
            }
        }

        public String currentBoard(int boardReference) {
            return "\n\n\t\t " +
                    allBoard.get(boardReference - 1).board[0][0]+" | " +
                    allBoard.get(boardReference - 1).board[0][1]+" | " +
                    allBoard.get(boardReference - 1).board[0][2]+" " +
                    "\n\t\t---+---+---\n\t\t " +
                    allBoard.get(boardReference - 1).board[1][0]+" | " +
                    allBoard.get(boardReference - 1).board[1][1]+" | " +
                    allBoard.get(boardReference - 1).board[1][2]+" " +
                    "\n\t\t---+---+---\n\t\t " +
                    allBoard.get(boardReference - 1).board[2][0]+" | " +
                    allBoard.get(boardReference - 1).board[2][1]+" | " +
                    allBoard.get(boardReference - 1).board[2][2] + " \n";
        }
        public boolean play(int row, int column, int player, int boardReference) {

            if (!(row >=0 && row <3 && column >= 0 && column < 3)) {
               //System.out.println("1");
                return false;
            }
            if (allBoard.get(boardReference - 1).board[row][column] > '9') {
               //System.out.println("2");
                return false;
            }
            if (player != allBoard.get(boardReference - 1).nextPlayer) {
                //System.out.println("3");
                return false;
            }

            if (allBoard.get(boardReference - 1).numPlays == 9) {
               //System.out.println("4");
                return false;
            }

            Plays.add(new TTT.play(row, column, player));

            allBoard.get(boardReference - 1).board[row][column] = (player == 1) ? 'X' : 'O'; /* Insert player symbol */
            allBoard.get(boardReference - 1).nextPlayer = (allBoard.get(boardReference - 1).nextPlayer + 1) % 2;
            allBoard.get(boardReference - 1).numPlays ++;
            return true;

        }
        public int checkWinner(int boardReference) {
            int i;
            /* Check for a winning line - diagonals first */
            if(boardReference < 0 ){
                return 0;
            }
            if((allBoard.get(boardReference - 1).board[0][0] == allBoard.get(boardReference - 1).board[1][1] &&
                    allBoard.get(boardReference - 1).board[0][0] == allBoard.get(boardReference - 1).board[2][2]) ||
                    (allBoard.get(boardReference - 1).board[0][2] == allBoard.get(boardReference - 1).board[1][1] &&
                            allBoard.get(boardReference - 1).board[0][2] == allBoard.get(boardReference - 1).board[2][0])) {
                if (allBoard.get(boardReference - 1).board[1][1]=='X')
                    return 1;
                else
                    return 0;
            }
            else
                /* Check rows and columns for a winning line */
                for(i = 0; i <= 2; i ++){
                    if((allBoard.get(boardReference - 1).board[i][0] == allBoard.get(boardReference - 1).board[i][1] &&
                            allBoard.get(boardReference - 1).board[i][0] == allBoard.get(boardReference - 1).board[i][2])) {
                        if (allBoard.get(boardReference - 1).board[i][0]=='X')
                            return 1;
                        else
                            return 0;
                    }
                    if ((allBoard.get(boardReference - 1).board[0][i] == allBoard.get(boardReference - 1).board[1][i] &&
                            allBoard.get(boardReference - 1).board[0][i] == allBoard.get(boardReference - 1).board[2][i])) {
                        if (allBoard.get(boardReference - 1).board[0][i]=='X')
                            return 1;
                        else
                            return 0;
                    }
                }
            if (allBoard.get(boardReference - 1).numPlays == 9)
                return 2; /* A draw! */
            else
                return -1; /* Game is not over yet */
        }
        public void restart(int boardReference, int id ) {

           char boardReset[][] = {
                {'1','2','3'}, /* Initial values are reference numbers */
                {'4','5','6'}, /* used to select a vacant square for */
                {'7','8','9'} /* a turn. */
           };
           System.out.print(ConsoleColors.BLUE_BOLD_BRIGHT+"   [BOARD_RESET] ");
           System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT+"User: "+getUserInfo(id).split(" ")[0]+"; Id: "+id+"; Board Reference: "+boardReference);
           allBoard.get(boardReference - 1).board = boardReset;
            allBoard.get(boardReference - 1).nextPlayer = 0;
            allBoard.get(boardReference - 1).numPlays = 0;
        }
        public void removeLastPlay(int boardReference){
            int _column, _row;
            for(int k=2; k>0; k--){
                if(Plays.size() > 0){
                    _row = Plays.getLast().getRow();
                    _column = Plays.getLast().getColumn();
                    allBoard.get(boardReference - 1).board[_row][_column] = (char) (48 + (( (3*_row) + (1+_column))));
                    allBoard.get(boardReference - 1).numPlays--;
                    Plays.removeLast();
                }
            }
        }

        public int myCard(int idMatch, boolean opponnent) {
            int index;
            if (opponnent)
                index = 1;
            else
                index = 0;

            Match match = matches.get(idMatch);
            if (match.getCards()[index] == 'X')
                return 1;
            else
                return 0;
        }

        // Method to allow the player to store his play on the data base
        public void makeMyPlay(int idMatch, int play){
            for (Match match: matches){
                if(match.getIdMatch()==idMatch){
                    match.setPlay(play);
                }
            }
           // System.out.println(matches.get(idMatch-1).getPlay());
        }

        // Method that will wait for the opponent to play
        public int waitingPlayerToPlay(int idMatch, int idPlayer) {
            for (Match match: matches){
                if(match.getIdMatch()==idMatch){
                    return match.getPlay();
                }
            }
            return -1;
        }
        public void setPlayToDefault(int idMatch){
            for (Match match: matches){
                if(match.getIdMatch()==idMatch){
                    match.setPlayDefault();
                }
            }
        }

        // return the result of the request for game
        public int createRequest(int idClient, int idOpponent, char myCard) {
            boolean exist=false;
            char cards[];

            if (Character.compare(myCard,'X')==0) {
                cards = new char[]{'X', 'O'};
            }
            else {
                cards = new char[]{'O', 'X'};
            }
            int requestId = -1;
            for (Match match: matches){
                if(match.getIdOpponent()==idOpponent && match.getIdClient() == idClient && !match.isMatchIsFinished()){
                    exist = true;
                }
            }
            if (!exist){
                int board = addingBoard();
                Match match = new Match(idClient, idOpponent, board, idMatch);
                match.setCards(cards);
                matches.add(match);
                requestId = idMatch;
                idMatch++;
                System.out.print(ConsoleColors.PURPLE_BOLD_BRIGHT+"   [MATCH_REQUEST] ");
                System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT+"User: "+getUserInfo(idClient).split(" ")[0]+" challenge User: "+getUserInfo(idOpponent).split(" ")[0]+" to play"+
                        "; matchId: "+requestId+"; boardId: "+board);
            }
            return requestId;
        }

        //
        public int getMatchBoard(int matchId){
            int board = -1;
            for (Match match: matches){
                if(match.getIdMatch()==matchId){
                    return match.getIdBoardReference();
                }
            }
            return board;
        }

        // Method to end the match
        public void endMatch(int idMatch, int id) {
            String name = "";
            for (Match match : matches) {
                if (match.getIdMatch() == idMatch){
                    match.setMatchIsFinished(true);
                    name = getUserInfo(id).split(" ")[0];
                }
            }
            System.out.print(ConsoleColors.PURPLE_BOLD_BRIGHT+"   [MATCH_END] ");
            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT+"User: " + name + " end multiplayer match");
        }

    // Method to end the match
    public boolean isMatchEnded(int idMatch, int id) {
        for (Match match : matches) {
            if (match.getIdMatch() == idMatch  ){
                return match.isMatchIsFinished();
            }
        }
        return false;
    }

        // to delete the match from the list of matches
        public void deleteMatch(int idMatch, int id){
            int index = 0; boolean inList=false;
            if (matches.size()>0) {
                for (Match match : matches) {
                    if (match.getIdMatch() == idMatch)
                        inList = true;
                    if(!inList)
                        index++;
                }
                matches.remove(index);
            }
        }

        // Client waiting to play
        public boolean waitingOpponent(int idMatch){
            for (Match match: matches){
                if(match.getIdMatch()==idMatch){
                    return match.isAccepted();
                }
            }
            return false;
        }

        // this method wait for the opponent to return his choice
        public char acceptRequest(int idMatch){
            for (Match match: matches){
                if(match.getIdMatch()==idMatch){
                    match.setAccepted(true);
                    System.out.print(ConsoleColors.PURPLE_BOLD_BRIGHT+"   [MATCH_ACCEPT] ");
                    System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT+"User: "+getUserInfo(match.getIdOpponent()).split(" ")[0]+
                            " has accepted to play whit User: "+getUserInfo(match.getIdClient()).split(" ")[0]+
                            "; boardId: "+match.getIdBoardReference()
                    );
                    return match.getCards()[1];
                }
            }
            return 'a';
        }

        // return all the request for the user
        public String getRequests(int idClient){
            String allRequests="";
            for (Match match: matches){
                if(match.getIdOpponent()==idClient && !match.isMatchIsFinished()){
                    allRequests=allRequests+getUserInfo(match.getIdClient()).split(" ")[0]+" "+match.getIdBoardReference()+" "+
                            match.getIdMatch()+"\n";
                }
            }
            return allRequests;
        }

        // return the id of the user if autenticated and -1 if not
        public int validateUser(String userName, String userPasswd) {
            // json file connection
            JSONParser jsonParser = new JSONParser();

            try(FileReader reader = new FileReader("users.json")){
                Object obj = jsonParser.parse(reader);
                JSONArray usersList = (JSONArray) obj;
                JSONObject userObj = new JSONObject();

                for(Object user : usersList){
                    userObj = (JSONObject) user ;

                    if(userName.equals(userObj.get("nome")) && userPasswd.equals(userObj.get("passwd")) && Integer.parseInt(userObj.get("state").toString())==0){
                        System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT+"   [LOGIN] ");
                        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT+"User: "+userObj.get("nome")+"; Id: "+Integer.parseInt(userObj.get("id").toString()));
                        updateUser(Integer.parseInt(userObj.get("id").toString()), "state", 1);
                        return Integer.parseInt(userObj.get("id").toString());
                    }
                }
            }catch(Exception e){
                System.out.println(e);
            }
            return -1;
        }

        // return all the user info by the given id
        public String getUserInfo(int id){
            JSONParser jsonParser = new JSONParser();

            try(FileReader reader = new FileReader("users.json")){
                Object obj = jsonParser.parse(reader);
                JSONArray usersList = (JSONArray) obj;
                JSONObject userObj = new JSONObject();

                for(Object user : usersList){
                    userObj = (JSONObject) user ;
                    if(Integer.parseInt(userObj.get("id").toString())==id){
                        return userObj.get("nome")+" "+userObj.get("nrVitorias")+" "+userObj.get("nrEmpates")+" "+userObj.get("nrDerrotas");
                    }
                }
            }catch(Exception e){
                System.out.println(e);
            }
            return "";
        }


    // Method that will check if the user already exist
    public boolean thereIsAEqualUser(String username){
        JSONParser jsonParser = new JSONParser();

        try(FileReader reader = new FileReader("users.json")){
            Object obj = jsonParser.parse(reader);
            JSONArray usersList = (JSONArray) obj;
            JSONObject userObj = new JSONObject();

            for(Object user : usersList){
                userObj = (JSONObject) user ;
                if(userObj.get("nome").equals(username)){
                    return true;
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    // Add new user to the json file
    public int addUser(String user, String passwd) throws RemoteException {
        if(!thereIsAEqualUser(user)){
            JSONParser jsonParser = new JSONParser();
            JSONArray userList = new JSONArray();

            // getting all the previous user in the json file
            try(FileReader reader = new FileReader("users.json")){
                Object obj = jsonParser.parse(reader);
                JSONArray usersList = (JSONArray) obj;
                JSONObject userObj = new JSONObject();

                for(Object userObjs : usersList){
                    userObj = (JSONObject) userObjs ;
                    userList.add(userObj);
                }
            }catch(Exception e){
                System.out.println(e);
            }

            int board = addingBoard();
            // adding the new user

            JSONObject newUser = new JSONObject();
            newUser.put("id", this.userId+1);
            newUser.put("nome", user);
            newUser.put("passwd", passwd);
            newUser.put("nrVitorias", 0);
            newUser.put("nrDerrotas", 0);
            newUser.put("nrEmpates", 0);
            newUser.put("state", 1);
            newUser.put("myCard", "");
            newUser.put("boardReference", board);


            userList.add(newUser);
            try(FileWriter file = new FileWriter("users.json")){
                file.write(userList.toString());
                System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT+"   [SIGN_UP] ");
                System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT+"User: "+user+"; Id:"+(this.userId));
                file.flush();
                this.userId++;
                return userId;
            }catch (Exception e){
                System.out.println(e);
                return -1;
            }
        }
        return -1;
    }

        // method to add a new board on the list of bord
        public int addingBoard(){
            Board newBoard = new Board();// creating a new board to this user
            allBoard.add(newBoard);// adding the new board to the list of all the boards
            idBoard ++;// to increment the id to the next board
            return idBoard-1;
        }

        // Method that will be called when some one try to log in the app
        public void initializingApp() throws RemoteException {
            JSONParser jsonParser = new JSONParser();
            JSONArray userList = new JSONArray();

        // getting all the previous user in the json file
            try(FileReader reader = new FileReader("users.json")){
                Object obj = jsonParser.parse(reader);
                JSONArray usersList = (JSONArray) obj;
                JSONObject userObj = new JSONObject();

            // to make all the board's for the existent player's/user's
                for(Object userObjs : usersList){
                    userObj = (JSONObject) userObjs ;
                    int board = addingBoard();
                    ((JSONObject) userObjs).put("boardReference", board);
                    ((JSONObject) userObjs).put("state", 0);
                    userList.add(userObj);
                    this.userId ++;
                }
            }catch(Exception e){
                System.out.println(e);
        }

        try(FileWriter file = new FileWriter("users.json")){
            file.write(userList.toString());
            file.flush();
        }catch (Exception e){
            System.out.println(e);
        }
    }

        // to update user info
        public boolean updateUser(int id,String campo, int value){
            JSONParser jsonParser = new JSONParser();
            JSONArray userList = new JSONArray();

            // getting all the previous user in the json file
            try(FileReader reader = new FileReader("users.json")){
                Object obj = jsonParser.parse(reader);
                JSONArray usersList = (JSONArray) obj;
                JSONObject userObj = new JSONObject();

                for(Object userObjs : usersList){
                    userObj = (JSONObject) userObjs ;
                    if(Integer.parseInt(userObj.get("id").toString())==id) {
                        ((JSONObject) userObjs).put(campo, value);
                    }
                    userList.add(userObj);
                }
            }catch(Exception e){
                System.out.println(e);
            return false;
        }

        try(FileWriter file = new FileWriter("users.json")){
            file.write(userList.toString());
            file.flush();
            System.out.print(ConsoleColors.BLUE_BOLD_BRIGHT+"   [UPDATE] ");
            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT+"User "+this.getUserInfo(id).split(" ")[0]+
                    "; Id: "+id+" has updated his data.");
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
        }

        // method that will return a specifyd value
        public int getUserValue(int id, String value){
            JSONParser jsonParser = new JSONParser();
            JSONArray userList = new JSONArray();

            // getting all the previous user in the json file
            try(FileReader reader = new FileReader("users.json")){
                Object obj = jsonParser.parse(reader);
                JSONArray usersList = (JSONArray) obj;
                JSONObject userObj = new JSONObject();

                for(Object userObjs : usersList){
                    userObj = (JSONObject) userObjs ;
                    if(Integer.parseInt(userObj.get("id").toString())==id) {
                        return Integer.parseInt(userObj.get(value).toString());
                    }
                }

            }catch(Exception e){
                System.out.println(e);
            }
            return -1;
        }

        // Method that provide all the active user
        public String allActiveUser(int id){
            String activeUser= "";
            JSONParser jsonParser = new JSONParser();

            try(FileReader reader = new FileReader("users.json")){
                Object obj = jsonParser.parse(reader);
                JSONArray usersList = (JSONArray) obj;
                JSONObject userObj = new JSONObject();

                for(Object user : usersList){
                    userObj = (JSONObject) user ;
                    if(Integer.parseInt(userObj.get("state").toString())==1 && Integer.parseInt(userObj.get("id").toString())!=id){
                        activeUser=activeUser+userObj.get("id")+" "+userObj.get("nome")+"\n";
                    }
                }
            }catch(Exception e){
                System.out.println(e);
            }
            return activeUser;
        }

    public char[] getBoard(int boardReference){
            char auxiliarBoard[][] = allBoard.get(boardReference - 1).board;
        char m1 = auxiliarBoard[0][0]; char m2 = auxiliarBoard[0][1]; char m3 = auxiliarBoard[0][2];
        char m4 = auxiliarBoard[1][0]; char m5 = auxiliarBoard[1][1]; char m6 = auxiliarBoard[1][2];
        char m7 = auxiliarBoard[2][0]; char m8 = auxiliarBoard[2][1]; char m9 = auxiliarBoard[2][2];
        return new char[]{m1, m2, m3, m4, m5, m6, m7, m8, m9};
    }

    public void newAppStart() {
        System.out.print(ConsoleColors.BLUE_BOLD_BRIGHT+"   [NEW_START] ");
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT+"A new instance off the app was been open.");
    }
    public void logOutNotification(int id){
        System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT+"   [LOG_OUT] ");
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT+"User: "+this.getUserInfo(id).split(" ")[0]+"; Id: "+id);
    }
}
