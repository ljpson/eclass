package kr.co.neteacher.eclass.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.co.neteacher.eclass.entity.enums.EvalType;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 능률 수행평가(NE평가)
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Table(name = "t_ne_eval")
public class NeEval {

    @Id
    @Comment("NE평가 아이디")
    private Long id;

    @Comment("교과서 정보")
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Comment("단원명")
    @Column(length = 100, nullable = false)
    private String unitName;

    @Comment("평가 유형")
    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private EvalType evalType;

    @Comment("평가명")
    @Column(length = 100, nullable = false)
    private String evalName;

    @Comment("카드개수")
    private Long cardCnt;

    @JsonIgnore
    @OneToMany(mappedBy = "neEval", cascade = CascadeType.ALL)
    private List<Card> cardList = new ArrayList<>();

}
