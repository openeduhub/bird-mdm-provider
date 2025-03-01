package org.edusharing.wlo.bird.mdm.provider.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record BirdDTO(
        @JsonProperty("course_availability_from") I18N<String> courseAvailabilityFrom,
        @JsonProperty("course_charge") I18N<CourseCharge> courseCharge,
        @JsonProperty("course_coursemode") I18N<CourseMode> courseMode,
        @JsonProperty("course_description_long") I18N<String> courseLongDescription,
        @JsonProperty("course_description_short") I18N<String> courseShortDescription,
        @JsonProperty("course_duration") Long courseDuration,
        @JsonProperty("course_duration_timeunit") I18N<CourseTimeunit> courseDurationTimeunit,
        @JsonProperty("course_language") I18N<List<CourseLanguage>> courseLanguage,
        @JsonProperty("course_learningoutcome") I18N<String> courseLearningOutcome,
        @JsonProperty("course_lecture_type") I18N<List<CourseLectureType>> courseLectureType,
        @JsonProperty("course_schedule") I18N<String> courseSchedule,
        @JsonProperty("course_serviceprovider_id") String serviceProviderId,
        @JsonProperty("course_serviceprovider_name") I18N<String> serviceProviderName,
        @JsonProperty("course_serviceprovider_url_image") I18N<String> serviceProviderUrlImage,
        @JsonProperty("course_serviceprovider_url_service") I18N<String> serviceProviderUrlService,
        @JsonProperty("course_targetgroup") I18N<List<CourseTarget>> courseTargetGroup,
        @JsonProperty("course_title") I18N<String> courseTitle,
        @JsonProperty("course_type") I18N<List<CourseType>> courseType,
        @JsonProperty("course_url_image") I18N<String> courseUrlImage,
        @JsonProperty("course_url_video") I18N<String> courseUrlVideo,
        @JsonProperty("course_workload") I18N<String> courseWorkload
) {
}

