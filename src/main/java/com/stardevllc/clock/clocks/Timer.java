package com.stardevllc.clock.clocks;

import com.stardevllc.clock.Clock;
import com.stardevllc.clock.callback.CallbackHolder;
import com.stardevllc.clock.property.ClockLongProperty;
import com.stardevllc.clock.snapshot.TimerSnapshot;
import com.stardevllc.property.LongProperty;

public class Timer extends Clock<TimerSnapshot> {
    
    protected final ClockLongProperty lengthProperty;
    
    public Timer(long length, long countAmount) {
        super(length, countAmount);
        this.lengthProperty = new ClockLongProperty(this, "length", length);
        this.lengthProperty.addListener(e -> unpause());
    }

    @Override
    protected boolean shouldCallback(CallbackHolder<TimerSnapshot> holder) {
        long lastRun = holder.getLastRun(); //Variable to easily access when it ran last
        if (!holder.isRepeating()) {
            long run = holder.getPeriod(); //This is for when the non-repeating callback should run based on the length
            return lastRun == -1 && this.getTime() <= run;
        } else {
            if (holder.getLastRun() == -1) {
                return holder.getPeriod() <= this.getTime();
            } else {
                long nextRun = holder.getLastRun() - holder.getPeriod();
                return this.getTime() <= nextRun;
            }
        }
    }

    @Override
    protected long getNextRun(CallbackHolder<TimerSnapshot> holder) {
        if (!holder.isRepeating()) {
            return holder.getPeriod();
        } 
        
        if (holder.getLastRun() == -1) {
            return this.getTime() - holder.getPeriod();
        } else {
            return holder.getLastRun() - holder.getPeriod();
        }
    }

    @Override
    protected void count() {
        this.time.setValue(Math.max(0, getTime() - countAmount));
    }

    public long getLength() {
        return lengthProperty.get();
    }
    
    public void reset() {
        setTime(getLength());
        this.callbacks.values().forEach(callback -> callback.setLastRun(-1));
    }
    
    public void setLength(long length) {
        long elapsed = this.getLength() - this.getTime();
        this.lengthProperty.setValue(length);
        this.time.setValue(Math.max(this.getLength() - elapsed, 0));
        this.callbacks.values().forEach(callback -> {
            if (callback.isRepeating()) {
                callback.setLastRun(-1); //Reset last run if it is a repeating one
            } else {
                if (callback.getLastRun() != -1) {
                    if (this.getLength() - callback.getPeriod() > callback.getLastRun()) {
                        callback.setLastRun(-1); //This reset the non-repeating callbacks if the new length - the interval is greater th an the last run
                    }
                }
            }
        });
    }
    
    public void setLengthAndReset(long length) {
        this.lengthProperty.setValue(length);
        this.reset();
    }
    
    public void addLength(long length) {
        setLength(this.getLength() + length);
    }
    
    public void removeLength(long length) {
        if (length - this.getLength() < 0) {
            length = this.getLength();
        }
        
        setLength(this.getLength() - length);
    }
    
    @Override
    public Timer start() {
        return (Timer) super.start();
    }
    
    @Override
    public TimerSnapshot createSnapshot() {
        return new TimerSnapshot(this.getTime(), isPaused(), this.getLength(), getCountAmount());
    }
    
    public LongProperty lengthProperty() {
        return this.lengthProperty;
    }
}