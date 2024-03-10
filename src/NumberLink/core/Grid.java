package NumberLink.core;

public  class Grid {

    public Grid parent;
    public int rank;
    public int pathNumber;
    public boolean isEndpoint;

    public Grid(){
        this.parent = this;
        this.rank = 0;
        this.pathNumber = 0;
        this.isEndpoint = false;
    }

    private GridState state = GridState.OPEN;

    public GridState getState() {
        return state;
    }

    public void setState(GridState state) {
        this.state = state;
    }
}
