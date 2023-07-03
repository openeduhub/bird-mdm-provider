package org.edusharing.wlo.bird.mdm.provider.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CourseTarget {
    PUPILS("Schüler*innen"),
    PROSPECTIVE_STUDY("Studieninteressierte"),
    STUDENTS("Studierende"),
    PROSPECTIVE_DOCTORAL("Promotionsinteresse"),
    PASCH("PASCH-Schüler*innen"),
    TEACHER("Lehrende"),
    PARENTS("Eltern");

    private final String value;

    CourseTarget(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String toString() {
        return value;
    }
}
