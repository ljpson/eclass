package kr.co.neteacher.eclass.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

/**
 * NE평가_카드
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Table(name = "t_card")
public class Card {

    @Id
    @Comment("카드 아이디")
    @JsonProperty(value = "cardId")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ne_eval_id")
    @Comment("NE평가 정보")
    @JsonIgnore
    private NeEval neEval;

    @Comment("앞면")
    @Column(nullable = false, columnDefinition = "nvarchar(1000)")
    @JsonProperty(value = "word")
    private String front;

    @Comment("뒷면")
    @Column(nullable = false, columnDefinition = "nvarchar(1000)")
    @JsonProperty(value = "meaning")
    private String back;

    @Comment("음원 경로")
    private String audioPath;

    @Comment("예제 문장")
    @Column(length = 100)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty(value = "example")
    private String exampleSentence;

}
