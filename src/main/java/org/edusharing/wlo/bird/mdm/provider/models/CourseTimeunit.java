package org.edusharing.wlo.bird.mdm.provider.models;

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

    @Override
    public String toString() {
        return value;
    }
}
