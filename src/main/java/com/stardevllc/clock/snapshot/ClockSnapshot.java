package com.stardevllc.clock.snapshot;

public abstract class ClockSnapshot {
    protected final long time;
    protected final boolean paused;
    protected final long countAmount;
    
    public ClockSnapshot(long time, boolean paused, long countAmount) {
        this.time = time;
        this.paused = paused;
        this.countAmount = countAmount;
    }
    
    public long getTime() {
        return time;
    }
    
    public boolean isPaused() {
        return paused;
    }
    
    public long getCountAmount() {
        return countAmount;
    }
}
