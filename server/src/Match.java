public class Match {
    private int idClient, idOpponent, idBoardReference, idMatch;
    private String[] cards = {"", ""};


    public Match(int idClient, int idOpponent, int idBoardReference, int idMatch){
        this.idBoardReference = idBoardReference;
        this.idOpponent = idOpponent;
        this.idClient = idClient;
        this.idMatch = idMatch;
    }
    public String[] getCards() {
        return cards;
    }

    public void setCards(String[] cards) {
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