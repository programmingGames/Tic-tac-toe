import java.util.ArrayList;
import java.util.Arrays;

public class MoveChooser {
    private char[] board = {'1', '2','3', '4','5', '6','7', '8', '9'};
    private char myCard;
    private char opponentCard;
    private ArrayList<Integer> winMoves = new ArrayList<Integer>(5);
    private ArrayList<Integer> blockMoves = new ArrayList<Integer>(5);

    public MoveChooser(char myCard){
        this.myCard = myCard;
        this.opponentCard = 'X';
        if (myCard == 'X')
            opponentCard = 'O';
    }

    private void createBoard(char[] possibleMoves){
        board = possibleMoves;
    }

    public int randomRange(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }

    public int randomMove(char[] possibleMoves){
        boolean go = true;
        int option = 0;
        while(go){
            option = randomRange(0, 9);
            go = !(isAvailable(option));
        }
        return option+1;
    }

    private boolean isAvailable(int value){
        return (board[value] != 'X') && (board[value] != 'O');
    }

    private ArrayList<Integer> getMatchingMoves(int code, char symbol){ //code 0: line; code 1: column; code 2: diagonal;
        ArrayList<Integer> moves = new ArrayList<Integer>(5);
        int[][] pairsline =   {{1, 2}, {0, 2}, {0, 1},
                               {4, 5}, {5, 6}, {4, 6},
                               {7, 8}, {6, 8}, {6, 7}};
        int[][] pairscolumn = {{3, 6}, {4, 7}, {5, 8},
                               {0, 6}, {1, 7}, {2, 8},
                               {0, 3}, {1, 4}, {2, 5}};
        int[][] pairsdiagonal = {{4, 8}, {4, 6}, {0, 8}, {0, 4}, {2, 4}};

        int[][][] pairsToUse = {pairsline, pairscolumn, pairsdiagonal};
        int[][] pairs = pairsToUse[code];

        if (code < 2){
            for (int i=0; i<9; i++){
                int[] pair = pairs[i];
                if (board[pair[0]] == board[pair[1]] && board[pair[0]] == symbol && isAvailable(i)) {
                    moves.add(i + 1);
                    break; // if one move is found there is no need to continue searching
                }
            }
        }
        else{
            int j = 0;
            int[] range = {0, 2, 4, 8, 6};
            for (int i: range) {
                int[] pair = pairs[j];
                if (board[pair[0]] == board[pair[1]] && board[pair[0]] == symbol && isAvailable(i)) {
                    moves.add(range[j]+1);
                    break; // if a move is found there is no need for searching further
                }
                j++;
            }
            if (board[2] == board[6] && board[2] == symbol && isAvailable(6))
                moves.add(7);
        }
        return moves;
    }

    private void getWinMoves(){
        winMoves = new ArrayList<Integer>(5);
        ArrayList<Integer> in_line_moves = getMatchingMoves(0, myCard);
        ArrayList<Integer> in_column_moves = getMatchingMoves(1, myCard);
        ArrayList<Integer> in_diagonal_moves = getMatchingMoves(1, myCard);
        if (in_line_moves.size() != 0)
            winMoves = in_line_moves;
        else if (in_column_moves.size() != 0)
            winMoves = in_column_moves;
        else if (in_diagonal_moves.size() != 0)
            winMoves = in_diagonal_moves;
    }

    private void getBlockMoves(){
        blockMoves = new ArrayList<Integer>(5);
        ArrayList<Integer> in_line_moves = getMatchingMoves(0, opponentCard);
        ArrayList<Integer> in_column_moves = getMatchingMoves(1, opponentCard);
        ArrayList<Integer> in_diagonal_moves = getMatchingMoves(2, opponentCard);
        if (in_line_moves.size() != 0)
            blockMoves = in_line_moves;
        else if (in_column_moves.size() != 0)
            blockMoves = in_column_moves;
        else if (in_diagonal_moves.size() != 0)
            blockMoves = in_diagonal_moves;
    }

    public int getBestMove(char[] possibleMoves){
        createBoard(possibleMoves);
        getWinMoves();
        getBlockMoves();
        if (winMoves.size() > 0) {
            return winMoves.get(0);
        }
        else if (blockMoves.size() > 0) {
            return blockMoves.get(0);
        }
        return randomMove(possibleMoves);
    }
}



