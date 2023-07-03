package org.edusharing.wlo.bird.mdm.provider.models;

public enum CourseCharge {
    CHARGEABLE("kostenpflichtig"),
    FREE("kostenlos");

    private final String value;

    CourseCharge(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
