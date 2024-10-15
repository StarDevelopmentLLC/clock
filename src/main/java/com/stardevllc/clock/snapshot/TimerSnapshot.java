package com.stardevllc.clock.snapshot;

public class TimerSnapshot extends ClockSnapshot {
    
    protected final long length;
    
    public TimerSnapshot(long time, boolean paused, long length, long countAmount) {
        super(time, paused, countAmount);
        this.length = length;
    }
    
    public long getLength() {
        return length;
    }
}
