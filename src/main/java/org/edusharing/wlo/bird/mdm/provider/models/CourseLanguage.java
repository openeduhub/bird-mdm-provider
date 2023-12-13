package org.edusharing.wlo.bird.mdm.provider.models;

public enum CourseLanguage {
    DE,
    EN;

    public static CourseLanguage fromString(String string) {
        try {
            String languageString = string.toUpperCase().split("[_-]")[0];
            return CourseLanguage.valueOf(languageString);
        }catch (Exception ignore){
            return null;
        }
    }
}
