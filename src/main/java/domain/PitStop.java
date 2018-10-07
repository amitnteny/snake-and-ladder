package domain;

public class PitStop {

    private PitStopType pitStopType;
    private Integer pitFallPosition;

    public PitStop(PitStopType pitStopType, Integer pitFallPosition) {
        this.pitStopType = pitStopType;
        this.pitFallPosition = pitFallPosition;
    }

    public PitStopType getPitStopType() {
        return pitStopType;
    }

    public Integer getPitFallPosition() {
        return pitFallPosition;
    }

}
