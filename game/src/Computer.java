import java.rmi.RemoteException;

public class Computer {

    public int randomRange(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }

    public int getBestMoves(char[] possibleMoves) throws RemoteException{
        return 0;
    }

    public int RandomMove(char[] possibleMoves){
        boolean go = true;
        int option = 100;
        while(go){
            option = randomRange(1, 9);
            Character c1 = possibleMoves[option-1];
            Character c2 = 'O'; Character c3 = 'X';
            go = c1.equals(c2) || c1.equals(c3);
        }
        return option;
    }

    public int makePlay(int level, char[] possibleMoves) throws RemoteException{
        //if (level==1) {
        return randomRange(1,9);
        //}
        //return getBestMoves(possibleMoves);
    }
}