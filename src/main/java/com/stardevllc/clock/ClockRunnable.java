package com.stardevllc.clock;

import java.util.ArrayList;

public final class ClockRunnable implements Runnable {
    
    private ClockManager clockManager;
    
    public ClockRunnable(ClockManager clockManager) {
        this.clockManager = clockManager;
    }
    
    @Override
    public void run() {
        for (Clock<?> clock : new ArrayList<>(clockManager.getClocks())) {
            clock.tick();
            
            if (clock.isCancelled()) {
                clockManager.removeClock(clock);
            }
        }
    }
}