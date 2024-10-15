package com.stardevllc.clock.snapshot;

public class StopwatchSnapshot extends ClockSnapshot {
    
    private final long endTime;
    
    public StopwatchSnapshot(long time, long endTime, boolean paused, long countAmount) {
        super(time, paused, countAmount);
        this.endTime = endTime;
    }
    
    public long getEndTime() {
        return endTime;
    }
}
