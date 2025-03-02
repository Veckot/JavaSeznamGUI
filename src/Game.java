public class Game {
    private String name;
    private int mark;
    private boolean isBought;

    public Game(String name, int mark, boolean isBought) {
        this.name = name;
        this.mark = mark;
        this.isBought = isBought;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
