package enactApp.enactAPI.data.model;
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
@Table(name = "metric")
public class Metric extends AbstractEntity{
    @Column(name = "weight")
    private int weight;

    @Column(name = "date_time")
    private LocalDateTime dateTime;
}
