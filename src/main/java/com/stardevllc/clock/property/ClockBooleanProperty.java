package com.stardevllc.clock.property;

import com.stardevllc.clock.Clock;
import com.stardevllc.property.BooleanProperty;

public class ClockBooleanProperty extends BooleanProperty {
    public ClockBooleanProperty(Clock<?> bean, String propertyName, boolean value) {
        super(bean, propertyName, value);
    }
}
