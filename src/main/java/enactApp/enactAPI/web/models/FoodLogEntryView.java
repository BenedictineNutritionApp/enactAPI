package enactApp.enactAPI.web.models;

import enactApp.enactAPI.data.model.Food;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class FoodLogEntryView {

    private Long id;

    private String entryTime;

    private Date date;

    private Double portion;

    private FoodView food;



}
