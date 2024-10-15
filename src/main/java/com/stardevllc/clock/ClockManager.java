package com.stardevllc.clock;

import com.stardevllc.clock.callback.ClockCallback;
import com.stardevllc.clock.clocks.Stopwatch;
import com.stardevllc.clock.clocks.Timer;
import com.stardevllc.clock.snapshot.ClockSnapshot;
import com.stardevllc.clock.snapshot.StopwatchSnapshot;
import com.stardevllc.clock.snapshot.TimerSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class ClockManager {
    protected final List<Clock<? extends ClockSnapshot>> clocks = Collections.synchronizedList(new ArrayList<>());
    protected long countAmount;
    protected ClockRunnable runnable;
    protected Logger logger;
    
    public ClockManager(Logger logger, long countAmount) {
        this.runnable = new ClockRunnable(this);
        this.countAmount = countAmount;
        this.logger = logger;
    }
    
    public Logger getLogger() {
        return logger;
    }
    
    public void addClock(Clock<? extends ClockSnapshot> clock) {
        this.clocks.add(clock);
    }
    
    public void removeClock(Clock<? extends ClockSnapshot> clock) {
        this.clocks.remove(clock);
    }
    
    @SafeVarargs
    public final Timer createTimer(long length, ClockCallback<? extends TimerSnapshot>... callbacks) {
        Timer timer = new Timer(length, countAmount);
        addClock(timer);
        
        if (callbacks != null) {
            for (ClockCallback<? extends TimerSnapshot> callback : callbacks) {
                timer.addCallback((ClockCallback<TimerSnapshot>) callback);
            }
        }
        return timer;
    }
    
    @SafeVarargs
    public final Stopwatch createStopwatch(ClockCallback<? extends StopwatchSnapshot>... callbacks) {
        return createStopwatch(0L, 0L, callbacks);
    }
    
    @SafeVarargs
    public final Stopwatch createStopwatch(long endTime, ClockCallback<? extends StopwatchSnapshot>... callbacks) {
        return createStopwatch(0L, endTime, callbacks);
    }

    @SafeVarargs
    public final Stopwatch createStopwatch(long startTime, long endTime, ClockCallback<? extends StopwatchSnapshot>... callbacks) {
        Stopwatch stopwatch = new Stopwatch(startTime, endTime, countAmount);
        addClock(stopwatch);

        if (callbacks != null) {
            for (ClockCallback<? extends StopwatchSnapshot> callback : callbacks) {
                stopwatch.addCallback((ClockCallback<StopwatchSnapshot>) callback);
            }
        }

        return stopwatch;
    }
    
    public ClockRunnable getRunnable() {
        return runnable;
    }
    
    public List<Clock<? extends ClockSnapshot>> getClocks() {
        return clocks;
    }
    
    public long getCountAmount() {
        return countAmount;
    }
    
    public void setCountAmount(long countAmount) {
        this.countAmount = countAmount;
    }
}