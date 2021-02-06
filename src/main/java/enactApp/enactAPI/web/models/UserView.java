package enactApp.enactAPI.web.models;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserView {

    private String firstName;

    private int avatarId;


}
