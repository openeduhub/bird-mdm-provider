package org.edusharing.wlo.bird.mdm.provider.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CourseTimeunit {
    MINUTE("Minuten"),
    HOUR("Stunden"),
    DAY("Tage"),
    WEEK("Wochen"),
    MONTH("Monate");

    private final String value;

    CourseTimeunit(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String toString() {
        return value;
    }
}
