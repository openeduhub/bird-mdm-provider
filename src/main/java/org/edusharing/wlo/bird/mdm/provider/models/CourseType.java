package org.edusharing.wlo.bird.mdm.provider.models;

public enum CourseType {
    LANGUAGE_COURSE("Sprachkurs"),
    SPECIALIST_COURSE("Fachkurs"),
    PROPAEDEUTICS("Propädeutika"),
    SOFT_SKILLS("Soft Skills"),
    BUSINESS_SKILLS("Business Skills"),
    DIGITAL_SKILLS("Digital Skills"),
    ACADEMIC_SKILLS("Academic Skills"),
    CAREER_SKILLS("Career Skills");

    private final String value;

    CourseType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
