package kr.co.neteacher.eclass.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.co.neteacher.eclass.entity.enums.EduType;
import kr.co.neteacher.eclass.entity.enums.YearType;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 교과서
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Table(name = "t_book")
public class Book {

    @Id
    @Comment("교과서 아이디")
    private Long id;

    @Column(length = 200, nullable = false)
    @Comment("교과서명")
    private String bookTitle;

    @Column(length = 100)
    @Comment("저자명")
    private String author;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 10, nullable = false)
    @Comment("개정년도")
    private YearType yearType;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 10, nullable = false)
    @Comment("학교 유형")
    private EduType eduType;

    @Comment("이미지경로")
    private String imagePath;

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<NeEval> neEvalList = new ArrayList<>();

}
