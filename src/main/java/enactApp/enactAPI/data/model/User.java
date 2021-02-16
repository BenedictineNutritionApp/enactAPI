package enactApp.enactAPI.data.model;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "user")
public class User extends AbstractEntity {
//public class User extends AbstractEntity implements UserDetails, CredentialsContainer {


    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "avatar_id")
    private int avatarId;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @Column(name = "height")
    private int height;

    @Column(name = "weight")
    private int weight;

    @Column(name = "activity_level_id")
    private long activityLevelId;

    @Column(name = "cancer_treatment_id")
    private long cancerTreatmentId;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "race")
    private String race;

    @Column(name = "ethnicity")
    private String ethnicity;

    @Column(name = "gender")
    private String gender;


}

