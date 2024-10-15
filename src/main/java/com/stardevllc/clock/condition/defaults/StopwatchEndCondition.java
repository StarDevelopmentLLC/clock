package com.stardevllc.clock.condition.defaults;

import com.stardevllc.clock.condition.ClockEndCondition;
import com.stardevllc.clock.snapshot.StopwatchSnapshot;

public class StopwatchEndCondition implements ClockEndCondition<StopwatchSnapshot> {
    @Override
    public boolean shouldEnd(StopwatchSnapshot snapshot) {
        return snapshot.getTime() >= snapshot.getEndTime();
    }
}
