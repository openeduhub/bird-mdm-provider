package org.edusharing.wlo.bird.mdm.provider.models;

public enum CourseMode {
    SELF_STUDY("Selbstlernkurs"),
    SUPERVISED("Betreuter Kurs");

    private final String value;

    CourseMode(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
