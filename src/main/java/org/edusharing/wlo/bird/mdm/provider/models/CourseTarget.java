package org.edusharing.wlo.bird.mdm.provider.models;

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

    @Override
    public String toString() {
        return value;
    }
}
