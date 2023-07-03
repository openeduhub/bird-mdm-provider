package org.edusharing.wlo.bird.mdm.provider.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CourseLectureType {
    PRESENCE("Präsenz"),
    ONLINE_SELF_STUDY("Online (Selbstlernkurs)"),
    BLENDED_LEARNING("Blended Learning mit festen Präsenz-Gruppenterminen "),
    ONLINE_FIXED_GROUP_APPOINTMENTS("Online mit festen Online-Gruppenterminen"),
    MASSIVE_OPEN_ONLINE_COURSE("Massive Open Online Course (MOOC)");

    private final String value;

    CourseLectureType(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String toString() {
        return value;
    }
}
