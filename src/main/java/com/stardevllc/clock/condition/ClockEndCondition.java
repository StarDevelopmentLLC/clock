package com.stardevllc.clock.condition;

import com.stardevllc.clock.snapshot.ClockSnapshot;

@FunctionalInterface
public interface ClockEndCondition<T extends ClockSnapshot> {
    boolean shouldEnd(T snapshot);
}
