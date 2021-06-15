// Class related to an match
public class Match {
    private int idClient, idOpponent, idBoardReference, idMatch;
    private boolean accepted;
    private int play=-1;
    private boolean matchIsFinished;
    private char[] cards = {'d', 'd'};

    public Match(int idClient, int idOpponent, int idBoardReference, int idMatch){
        this.idBoardReference = idBoardReference;
        this.idOpponent = idOpponent;
        this.idClient = idClient;
        this.idMatch = idMatch;
        this.accepted=false;
        this.matchIsFinished = false;
    }

    public boolean isMatchIsFinished() {
        return matchIsFinished;
    }

    public void setMatchIsFinished(boolean matchIsFinished) {
        this.matchIsFinished = matchIsFinished;
    }

    public int getPlay() {
        return play;
    }

    public void setPlayDefault(){ this.play=-1; }

    public void setPlay(int play) {
        this.play = play;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
    public char[] getCards() {
        return cards;
    }

    public void setCards(char[] cards) {
        this.cards = cards;
    }

    public int getIdBoardReference() {
        return idBoardReference;
    }

    public void setIdBoardReference(int idBoardReference) {
        this.idBoardReference = idBoardReference;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdOpponent() {
        return idOpponent;
    }

    public void setIdOpponent(int idOpponent) {
        this.idOpponent = idOpponent;
    }
    public int getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }
}