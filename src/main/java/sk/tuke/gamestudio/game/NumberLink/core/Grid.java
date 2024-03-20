package sk.tuke.gamestudio.game.NumberLink.core;

public  class Grid {

    public int pathNumber;
    public boolean isEndpoint;

    public Grid parent;
    public int rank;

    public Grid(){
        this.parent = this;
        this.rank = 0;
        this.pathNumber = 0;
        this.isEndpoint = false;

    }

    private GridState state;

    public GridState getState() {
        return state;
    }

    public void setState(GridState state) {
        this.state = state;
    }
}
