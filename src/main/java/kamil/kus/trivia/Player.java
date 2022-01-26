package kamil.kus.trivia;

class Player {
    private final String name;
    private int place;
    private int purses;

    Player(String name) {
        this.name = name;
        this.place = 0;
        this.purses = 0;
    }

    public String name() {
        return name;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int place() {
        return place;
    }

    public void addCoin() {
        this.purses++;
    }

    public int purses() {
        return purses;
    }
}
