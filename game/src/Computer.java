import java.rmi.RemoteException;

public class Computer {
    private MoveChooser movechooser;
    private char mycard;

    public Computer(int mycard){
        char[] cards = {'X', 'O'}; //inverted order because my card is the opposite card of the player.
        this.mycard = cards[mycard];
        this.movechooser = new MoveChooser(this.mycard);
        }

    public int makePlay(int level, char[] possibleMoves) throws RemoteException{
        if (level==1) {
            return movechooser.randomMove(possibleMoves);
        }
        else if (level==2){
            if (movechooser.randomRange(1,10) > 5){
                return movechooser.randomMove(possibleMoves);
            }
        }
        return movechooser.getBestMove(possibleMoves);
    }
}