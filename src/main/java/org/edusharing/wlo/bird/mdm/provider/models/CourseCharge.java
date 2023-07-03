package org.edusharing.wlo.bird.mdm.provider.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CourseCharge {
    CHARGEABLE("kostenpflichtig"),
    FREE("kostenlos");

    private final String value;

    CourseCharge(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String toString() {
        return value;
    }
}
