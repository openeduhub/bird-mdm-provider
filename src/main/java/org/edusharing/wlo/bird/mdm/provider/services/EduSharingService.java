package org.edusharing.wlo.bird.mdm.provider.services;

import ezvcard.Ezvcard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edu_sharing.generated.repository.backend.services.rest.client.ApiException;
import org.edu_sharing.generated.repository.backend.services.rest.client.api.NodeV1Api;
import org.edu_sharing.generated.repository.backend.services.rest.client.model.Node;
import org.edu_sharing.generated.repository.backend.services.rest.client.model.NodeEntries;
import org.edu_sharing.generated.repository.backend.services.rest.client.model.Pagination;
import org.edusharing.wlo.bird.mdm.provider.models.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;


@Slf4j
@Service
@RequiredArgsConstructor
public class EduSharingService {

    private final NodeV1Api nodeV1Api;

    @Value("${edu.sharing.course.id}")
    private String collectionId;

    private static final List<String> properties = Arrays.asList(
            "ccm:price",
            "ccm:oeh_course_coursemode",
            "cclom:general_description",
            "ccm:oeh_course_description_short",
            "cclom:typicallearningtime",
            "cclom:general_language",
            "ccm:oeh_course_lecture_type",
            "ccm:oeh_course_serviceprovider_url_image",
            "cclom:location",
            "ccm:oeh_course_targetgroup",
            "ccm:oeh_lrt"
    );

    private static final String HTML_PATTERN = "<(\"[^\"]*\"|'[^']*'|[^'\">])*>";
    private static final Pattern htmlPattern = Pattern.compile(HTML_PATTERN);

    public List<BirdDTO> getCourses() throws ApiException {
        List<BirdDTO> data = new ArrayList<>();
        Pagination pagination;
        do {
            NodeEntries children = nodeV1Api.getChildren("-home-", collectionId, 500, data.size(), null, null, null, null, properties);
            List<BirdDTO> birdData = children.getNodes().stream()
                    .map(this::map)
                    .filter(Objects::nonNull)
                    .toList();
            data.addAll(birdData);
            pagination = children.getPagination();
        } while (data.size() < pagination.getTotal());
        return data;
    }

    private BirdDTO map(Node node) {
        Optional<Map<String, List<String>>> properties = Optional.ofNullable(node.getProperties());
        try {
            return new BirdDTO(
                    properties.map(x -> x.get("ccm:price")).flatMap(x -> x.stream().findFirst())
                            .filter(x -> x.equals("http://w3id.org/openeduhub/vocabs/price/no"))
                            .map(x -> CourseCharge.FREE)
                            .map(I18N::new)
                            .orElse(null),

                    properties.map(x -> x.get("ccm:oeh_course_coursemode"))
                            .flatMap(x -> x.stream().findFirst())
                            .map(x -> switch (x) {
                                        case "https://vocabs.openeduhub.de/w3id.org/openeduhub/vocabs/learningMode/selfPaced.html" ->
                                                CourseMode.SELF_STUDY;
                                        case "https://vocabs.openeduhub.de/w3id.org/openeduhub/vocabs/learningMode/guided.html " ->
                                                CourseMode.SUPERVISED;
                                        default -> CourseMode.SELF_STUDY;
                                    }
                            )
                            .map(I18N::new)
                            .orElse(null),

                    properties.map(x -> x.get("cclom:general_description"))
                            .flatMap(x -> x.stream().findFirst())
                            .map(I18N::new)
                            .orElseThrow(),

                    properties.map(x -> x.get("cclom:general_description"))
                            .flatMap(x -> x.stream().findFirst())
                            .map(x -> {
                                try {
                                    if (!htmlPattern.matcher(x).find()) {
                                        return x.split("\n")[0];
                                    }
                                } catch (Exception ignore) {
                                }
                                return x;
                            })
                            .map(I18N::new)
                            .orElse(new I18N<>("tbd.")),

                    0L,

                    new I18N<>(CourseTimeunit.MINUTE),

                    properties.map(x -> x.get("cclom:general_language"))
                            .map(x -> x.stream()
                                    .map(String::toLowerCase)
                                    .map(CourseLanguage::fromString)
                                    .filter(Objects::nonNull)
                                    .toList())
                            .map(x -> x.isEmpty() ? null : x)
                            .map(I18N::new)
                            .orElseThrow(),

// Bad database for on OERSI so we use CourseLectureType.ONLINE_SELF_STUDY only
//                properties.map(x -> x.get("ccm:oeh_course_lecture_type"))
//                        .map(x -> x.stream()
//                                .map(y -> switch (y) {
//                                    case "https://vocabs.openeduhub.de/w3id.org/openeduhub/vocabs/learningFormat/presential.html" ->
//                                            CourseLectureType.PRESENCE;
//                                    case "https://vocabs.openeduhub.de/w3id.org/openeduhub/vocabs/learningFormat/online.html" ->
//                                            CourseLectureType.ONLINE_SELF_STUDY;
//                                    case "https://vocabs.openeduhub.de/w3id.org/openeduhub/vocabs/learningFormat/blended.html" ->
//                                            CourseLectureType.BLENDED_LEARNING;
//                                    case "https://vocabs.openeduhub.de/w3id.org/openeduhub/vocabs/learningFormat/online_appointments.html " ->
//                                            CourseLectureType.ONLINE_FIXED_GROUP_APPOINTMENTS;
//                                    case "https://vocabs.openeduhub.de/w3id.org/openeduhub/vocabs/learningFormat/mooc.html" ->
//                                            CourseLectureType.MASSIVE_OPEN_ONLINE_COURSE;
//                                    default -> null;
//                                })
//                                .filter(Objects::nonNull)
//                                .toList())
//                        .map(x -> x.isEmpty() ? null : x)
//                        .map(I18N::new)
//                        .orElse(new I18N<>(Collections.singletonList(CourseLectureType.ONLINE_SELF_STUDY))),
                    new I18N<>(Collections.singletonList(CourseLectureType.ONLINE_SELF_STUDY)),
                    node.getRef().getId(),

//                new I18N<>("WLO"),
                    properties.map(x -> x.get("ccm:lifecyclecontributer_publisher"))
                            .flatMap(x -> x.stream().findFirst())
                            .map(x -> Ezvcard.parse(x).first())
                            .flatMap(x -> x.getOrganization().getValues().stream().findFirst())
                            .map(I18N::new)
                            .orElseThrow(),


// Bad database of OERSI so we use null only
//                properties.map(x -> x.get("ccm:oeh_course_serviceprovider_url_image"))
//                        .flatMap(x -> x.stream().findFirst())
//                        .map(I18N::new)
//                        .orElse(new I18N<>("tbd.")),
                    null,

                    properties.map(x -> x.get("cclom:location"))
                            .flatMap(x -> x.stream().findFirst())
                            .map(I18N::new)
                            .orElseThrow(),

// Bad database of OERSI so we use CourseTarget.STUDENTS only
//                properties.map(x -> x.get("ccm:oeh_course_targetgroup"))
//                        .map(x -> x.stream()
//                                .map(y -> switch (y) {
//                                    case "https://vocabs.openeduhub.de/w3id.org/openeduhub/vocabs/intendedEndUserRole_BIRD/pupil.html" ->
//                                            CourseTarget.PUPILS;
//                                    case "https://vocabs.openeduhub.de/w3id.org/openeduhub/vocabs/intendedEndUserRole_BIRD/prospective_student.html" ->
//                                            CourseTarget.PROSPECTIVE_STUDY;
//                                    case "https://vocabs.openeduhub.de/w3id.org/openeduhub/vocabs/intendedEndUserRole_BIRD/student.html " ->
//                                            CourseTarget.STUDENTS;
//                                    case "https://vocabs.openeduhub.de/w3id.org/openeduhub/vocabs/intendedEndUserRole_BIRD/prospective_doctoral.html  " ->
//                                            CourseTarget.PROSPECTIVE_DOCTORAL;
//                                    case "https://vocabs.openeduhub.de/w3id.org/openeduhub/vocabs/intendedEndUserRole_BIRD/pasch.html" ->
//                                            CourseTarget.PASCH;
//                                    case "https://vocabs.openeduhub.de/w3id.org/openeduhub/vocabs/intendedEndUserRole_BIRD/teacher.html" ->
//                                            CourseTarget.TEACHER;
//                                    case "https://vocabs.openeduhub.de/w3id.org/openeduhub/vocabs/intendedEndUserRole_BIRD/parent.html" ->
//                                            CourseTarget.PARENTS;
//                                    default -> null;
//                                })
//                                .filter(Objects::nonNull)
//                                .toList())
//                        .map(x->x.isEmpty() ? null : x)
//                        .map(I18N::new)
//                        .orElse(new I18N<>(Collections.singletonList(CourseTarget.STUDENTS))),
                    new I18N<>(Collections.singletonList(CourseTarget.STUDENTS)),

                    new I18N<>(Optional.of(node).map(Node::getTitle).orElseThrow()),

// Bad database of OERSI so we use CourseType.SPECIALIST_COURSE only
//                properties.map(x -> x.get("ccm:oeh_lrt"))
//                        .map(x -> x.stream()
//                                .map(y -> switch (y) {
//                                    case "https://vocabs.openeduhub.de/w3id.org/openeduhub/vocabs/new_lrt/8d5195dd-2e48-44d4-a9c1-6bccbf85ec96.html" ->
//                                            CourseType.LANGUAGE_COURSE;
//                                    case "https://vocabs.openeduhub.de/w3id.org/openeduhub/vocabs/new_lrt/8e157383-9ca3-4e20-849d-0881b648fd99.html" ->
//                                            CourseType.SPECIALIST_COURSE;
//                                    case "https://vocabs.openeduhub.de/w3id.org/openeduhub/vocabs/new_lrt/ff20ae9f-5d83-4f29-ba4f-993cbd743e5c.html" ->
//                                            CourseType.PROPAEDEUTICS;
//                                    case "https://vocabs.openeduhub.de/w3id.org/openeduhub/vocabs/new_lrt/89abe72e-d4c6-4797-ac36-175cfce25107.html" ->
//                                            CourseType.SOFT_SKILLS;
//                                    case "https://vocabs.openeduhub.de/w3id.org/openeduhub/vocabs/new_lrt/b0774ec7-49c0-49e0-8093-dce3ee6d02a0.html" ->
//                                            CourseType.BUSINESS_SKILLS;
//                                    case "https://vocabs.openeduhub.de/w3id.org/openeduhub/vocabs/new_lrt/4d64241b-3d8c-4d67-b9fe-9970f240d991.html" ->
//                                            CourseType.DIGITAL_SKILLS;
//                                    case "https://vocabs.openeduhub.de/w3id.org/openeduhub/vocabs/new_lrt/6f030e55-6193-4587-a374-d002cd43d787.html" ->
//                                            CourseType.ACADEMIC_SKILLS;
//                                    case "https://vocabs.openeduhub.de/w3id.org/openeduhub/vocabs/new_lrt/9ac858a9-fc06-41a1-a18e-faf7a1525198.html" ->
//                                            CourseType.CAREER_SKILLS;
//                                    default -> null;
//                                })
//                                .filter(Objects::nonNull)
//                                .toList())
//                        .map(x->x.isEmpty() ? null : x)
//                        .map(I18N::new)
//                        .orElse(new I18N<>(Collections.singletonList(CourseType.SPECIALIST_COURSE))),
                    new I18N<>(Collections.singletonList(CourseType.SPECIALIST_COURSE)),

// Bad database of OERSI so we use null only
                    //new I18N<>(Optional.of(node).map(Node::getPreview).map(Preview::getUrl).orElse("tbd."))
                    null
            );
        }catch (NoSuchElementException ex){
            log.warn("Node {}", node.getRef().getId(), ex);
            return null;
        }
    }
}
