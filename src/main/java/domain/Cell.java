package domain;

public class Cell {

    private int value;
    private PitStop pitStop;

    public Cell(int value, PitStop pitStop) {
        this.value = value;
        this.pitStop = pitStop;
    }

    public PitStop getPitStop() {
        return pitStop;
    }

    public void setPitStop(PitStop pitStop) {
        this.pitStop = pitStop;
    }

    public int getValue() {
        return value;
    }
}
