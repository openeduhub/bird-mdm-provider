package org.edusharing.wlo.bird.mdm.provider.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CourseMode {
    SELF_STUDY("Selbstlernkurs"),
    SUPERVISED("Betreuter Kurs");

    private final String value;

    CourseMode(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String toString() {
        return value;
    }
}
