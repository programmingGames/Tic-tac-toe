import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.RemoteServer;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.LinkedList;

public class TTT extends UnicastRemoteObject implements TTTinterface, TTTService{
    private LinkedList<TTT.play> Plays = new LinkedList<TTT.play>();
    private int idBoard=1, idMatch=1;
    // list of all the board
    private LinkedList<TTT.Board> allBoard =new LinkedList<TTT.Board>();
    private LinkedList<Match> matches = new LinkedList<Match>();

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
            String s = "\n\n\t\t " +
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
            return s;
        }
        public boolean play(int row, int column, int player, int boardReference) {

            if (!(row >=0 && row <3 && column >= 0 && column < 3)) {
                System.out.println("1");
                return false;
            }
            if (allBoard.get(boardReference - 1).board[row][column] > '9') {
                System.out.println("2");
                return false;
            }
            if (player != allBoard.get(boardReference - 1).nextPlayer) {
                System.out.println("3");
                return false;
            }
            if (allBoard.get(boardReference - 1).numPlays == 9) {
                System.out.println("4");
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
           System.out.println("[BOARD_RESET] User: "+getUserInfo(id).split(" ")[0]+" - "+id+"; Board Reference: "+boardReference);
           allBoard.get(boardReference - 1).board = boardReset;
           //System.out.println("Board reseted.");
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

        public void makeMyPlay(int idMatch, int play){
            matches.get(idMatch-1).setPlay(play);
        }

        // Method that will wait for the opponent to play
        public int waitingPlayerToPlay(int idMatch, int idPlayer) {
            int play = matches.get(idMatch-1).getPlay();
            matches.get(idMatch-1).setPlay(-1);
            return play;
        }

        // return the result of the request for game
        public int createRequest(int idClient, int idOpponent, char myCard) {
            boolean exist=false;
            char cards[];

            if (Character.compare(myCard,'X')==0)
                cards = new char[]{'X', 'O'};
            else
                cards = new char[]{'O', 'X'};

            int requestId = -1;
            for (Match match: matches){
                if(match.getIdOpponent()==idOpponent && match.getIdClient() == idClient){
                    exist = true;
                }
            }
            if (!exist){
                Match match = new Match(idClient, idOpponent, getUserValue(idClient, "boardReference"), idMatch);
                match.setCards(cards);
                matches.add(match);
                requestId = idMatch;
                idMatch++;
            }
            System.out.println(matches.get(requestId-1).getCards());
            return requestId;
        }

        // Client waiting to play
        public boolean waitingOpponent(int idMatch){
            return matches.get(idMatch-1).isAccepted();
        }

        // this method wait for the opponent to return his choice
        public char acceptRequest( boolean response,int idMatch){

            char card='n';
            if(response){
                matches.get(idMatch-1).setAccepted(true);
                card = matches.get(idMatch-1).getCards()[1];
            }
            return card;
        }

        // return all the request for the user
        public String getRequests(int idClient){
            String allRequests="";
            int i =1;
            for (Match match: matches){
                if(match.getIdOpponent()==idClient){
                    allRequests=allRequests+getUserInfo(match.getIdClient()).split(" ")[0]+" "+match.getIdBoardReference()+" "+i+"\n";
                }
                i++;
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

                    if(userName.equals(userObj.get("nome"))&&userPasswd.equals(userObj.get("passwd"))){
                        System.out.println("[LOGIN] User: "+userObj.get("nome")+" - "+Integer.parseInt(userObj.get("id").toString()));
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
        public int getRandomNumber(int min, int max) throws RemoteException{
            return (int) ((Math.random() * (max - min)) + min);
        }
        // Add new user to the json file
        public int addUser(String user, String passwd) throws RemoteException {
            int id = 1;
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
                    id ++;
                }
            }catch(Exception e){
                System.out.println(e);
            }
            // adding the new user
            JSONObject newUser = new JSONObject();
            newUser.put("id", (int)(id+getRandomNumber(id, 2000)*getRandomNumber(500, 2000)/getRandomNumber(1,3)));
            newUser.put("nome", user);
            newUser.put("passwd", passwd);
            newUser.put("nrVitorias", 0);
            newUser.put("nrDerrotas", 0);
            newUser.put("nrEmpates", 0);
            newUser.put("state", 1);
            newUser.put("myCard", "");
            newUser.put("boardReference", idBoard);

            addingBoard();// to add the respective board for this player

            userList.add(newUser);
            try(FileWriter file = new FileWriter("users.json")){
                file.write(userList.toString());
                System.out.println("[SIGN_UP] User: "+user+" - "+id+1);
                file.flush();
                return id+1;
            }catch (Exception e){
                System.out.println(e);
                return -1;
            }
        }

        // method to add a new board on the list of bord
        public void addingBoard(){
            Board newBoard = new Board();// creating a new board to this user
            allBoard.add(newBoard);// adding the new board to the list of all the boards
            idBoard ++;// to increment the id to the next board
        }

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
                ((JSONObject) userObjs).put("boardReference", idBoard);
                addingBoard();
                userList.add(userObj);
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
        System.out.println("[REFRESH] All user data set up to date.");
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
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
        }

        // method to determinate the if the user is abel to chose the card
        public boolean ableToChose(int id,int idOpennet){
            int userNrWins=0, opennetNrWins=0;
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
                        userNrWins = Integer.parseInt(userObj.get("nrVitorias").toString());;
                    }
                    else if(Integer.parseInt(userObj.get("id").toString())==idOpennet){
                        opennetNrWins = Integer.parseInt(userObj.get("nrVitorias").toString());
                    }
                }

            }catch(Exception e){
                System.out.println(e);
                return false;
            }
            // verify if the user that is asking to play has more oe equals nr victory, and if
            // so he has to chose the card that he will use to play, if not the opennent that is th
            // one how chose
            if(userNrWins>=opennetNrWins){
                return true;
            }
            else if (userNrWins<opennetNrWins){
                return false;
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

        public String allActiveUser(){
            String activeUser= "";
            JSONParser jsonParser = new JSONParser();

            try(FileReader reader = new FileReader("users.json")){
                Object obj = jsonParser.parse(reader);
                JSONArray usersList = (JSONArray) obj;
                JSONObject userObj = new JSONObject();

                for(Object user : usersList){
                    userObj = (JSONObject) user ;
                    if(Integer.parseInt(userObj.get("state").toString())==1){
                        activeUser=activeUser+userObj.get("id")+" "+userObj.get("nome")+"\n";
                    }
                }
            }catch(Exception e){
                System.out.println(e);
            }
            return activeUser;
        }

    public char[] getPossibleMoves(int boardReference){
            char auxiliarBoard[][] = allBoard.get(boardReference - 1).board;
        char m1 = auxiliarBoard[0][0]; char m2 = auxiliarBoard[0][1]; char m3 = auxiliarBoard[0][2];
        char m4 = auxiliarBoard[1][0]; char m5 = auxiliarBoard[1][1]; char m6 = auxiliarBoard[1][2];
        char m7 = auxiliarBoard[2][0]; char m8 = auxiliarBoard[2][1]; char m9 = auxiliarBoard[2][2];
        return new char[]{m1, m2, m3, m4, m5, m6, m7, m8, m9};
    }
}
