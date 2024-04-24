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

    public int getPathNumber() {
        return pathNumber;
    }

    public boolean isEndpoint() {
        return isEndpoint;
    }

    public void setEndpoint(boolean endpoint) {
        isEndpoint = endpoint;
    }

    public void setPathNumber(int pathNumber) {
        this.pathNumber = pathNumber;
    }

    private GridState state;

    public GridState getState() {
        return state;
    }

    public void setState(GridState state) {
        this.state = state;
    }
}
