package com.stardevllc.clock.condition.defaults;

import com.stardevllc.clock.condition.ClockEndCondition;
import com.stardevllc.clock.snapshot.TimerSnapshot;

public class TimerEndCondition implements ClockEndCondition<TimerSnapshot> {
    @Override
    public boolean shouldEnd(TimerSnapshot snapshot) {
        return snapshot.getTime() <= 0;
    }
}
