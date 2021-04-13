package enactApp.enactAPI.data.model;
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
@Table(name = "article")
public class Article extends AbstractEntity  {

    @Column(name = "article_name")
    private String articleName;

//    @Column(name = "article_author")
//    private String articleAuthor;

    @Column(name = "data")
    private byte[] data;

}
