package enactApp.enactAPI.data.model;


import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "fitness_activity")
public class FitnessActivity extends AbstractEntity{

    @Column(name = "type")
    private String type;

    @Column(name = "minutes")
    private String minutes;

    @Column(name = "intensity")
    private String intensity;

}
