package com.stardevllc.clock.property;

import com.stardevllc.clock.Clock;
import com.stardevllc.property.LongProperty;

public class ClockLongProperty extends LongProperty {
    public ClockLongProperty(Clock<?> bean, String propertyName, long value) {
        super(bean, propertyName, value);
    }
}
