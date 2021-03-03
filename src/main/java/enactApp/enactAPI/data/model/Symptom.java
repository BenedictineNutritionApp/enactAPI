package enactApp.enactAPI.data.model;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity

@Table(name = "symptom")
public class Symptom extends AbstractEntity {

    @Column(name = "abdominal_pain")
    private boolean abdominalPain;

    @Column(name = "bloating")
    private boolean bloating;

    @Column(name = "nausea")
    private boolean nausea;

    @Column(name = "vomiting")
    private boolean vomiting;

    @Column(name = "constipation")
    private boolean constipation;

    @Column(name = "diarrhea")
    private boolean diarrhea;

    @Column(name = "appetite_loss")
    private boolean appetiteLoss;

    @Column(name = "stoma_problems")
    private boolean stomaProblems;

    @Column(name = "other")
    private String other;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

}
