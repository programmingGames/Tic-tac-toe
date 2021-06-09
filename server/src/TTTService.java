import java.rmi.*;
public interface TTTService extends Remote {
    String currentBoard(int boardReference) throws RemoteException;
    boolean play(int row, int column, int player, int boardReference) throws RemoteException;
    int checkWinner(int boardReference) throws RemoteException;
    void restart(int boardReference, int id ) throws RemoteException;
    void removeLastPlay(int boardReference) throws RemoteException;
    String getUserInfo(int id) throws  RemoteException;
    int getUserValue(int id, String value) throws  RemoteException;
    int validateUser(String user, String passwd) throws RemoteException;
    int addUser(String user, String passwd) throws RemoteException;
    boolean updateUser(int id,String campo, int value) throws RemoteException;
    boolean ableToChose(int id,int idOpennet) throws RemoteException;
    String allActiveUser() throws RemoteException;
    void initializingApp() throws  RemoteException;
    char[] getPossibleMoves(int boardReference) throws  RemoteException;
}