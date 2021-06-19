import javax.lang.model.element.NestingKind;
import java.rmi.*;
public interface TTTinterface extends Remote {
    String currentBoard(int boardReference) throws RemoteException;
    boolean play(int row, int column, int player, int boardReference) throws RemoteException;
    int checkWinner(int boardReference) throws RemoteException;
    void restart(int boardReference, int id ) throws RemoteException;
    void removeLastPlay(int boardReference) throws RemoteException;
    String getUserInfo(int id) throws  RemoteException;
    int getUserValue(int id,String value) throws  RemoteException;
    int validateUser(String user, String passwd) throws RemoteException;
    int addUser(String user, String passwd) throws RemoteException;
    boolean updateUser(int id, String campo, int value) throws RemoteException;
    String allActiveUser(int id) throws RemoteException;
    void initializingApp() throws  RemoteException;
    char[] getPossibleMoves(int boardReference) throws  RemoteException;
    int createRequest(int idClient, int idOpponent, char myCard) throws RemoteException;
    int getMatchBoard(int matchId) throws  RemoteException;
    void deleteMatch(int idMatch,  int userId) throws  RemoteException;
    void endMatch(int idMatch, int id) throws  RemoteException;
    boolean isMatchEnded(int idMatch, int id) throws RemoteException;
    char acceptRequest(  int idMatch) throws RemoteException;
    String getRequests(int idClient) throws RemoteException;
    boolean waitingOpponent(int idMatch) throws RemoteException;
    int waitingPlayerToPlay(int idMatch, int idPlayer) throws RemoteException;
    void makeMyPlay(int idMatch, int play) throws RemoteException;
    void setPlayToDefault(int idMatch) throws  RemoteException;
    int myCard(int idMatch, boolean opponnent) throws  RemoteException;
}