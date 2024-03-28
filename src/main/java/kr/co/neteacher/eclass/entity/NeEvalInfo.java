package kr.co.neteacher.eclass.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

/**
 * 능률 수행평가(NE평가) 쓰기평가정보
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Table(name = "t_ne_eval_info")
public class NeEvalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("쓰기평가정보 아이디")
    private Long id;

    @Comment("NE평가 정보")
    @OneToOne
    @JoinColumn(name = "ne_eval_id")
    private NeEval neEval;

    @Comment("평가 주제 영어")
    @Column(columnDefinition = "nvarchar(max)")
    private String evalSubjectEn;

    @Comment("평가 주제 한글")
    @Column(columnDefinition = "nvarchar(max)")
    private String evalSubjectKo;

    @Comment("평가 목적")
    @Column(columnDefinition = "nvarchar(max)")
    private String evalPurposeImagePath;

    @Comment("과제 조건")
    @Column(columnDefinition = "nvarchar(max)")
    private String evalConditionImagePath;

    @Comment("성취 기준")
    @Column(columnDefinition = "nvarchar(max)")
    private String evalAchievementImagePath;

    @Comment("평가 기준")
    @Column(columnDefinition = "nvarchar(max)")
    private String evalStandardImagePath;

    @Comment("학생 평가 화면 이미지 파일 경로")
    @Column(columnDefinition = "TEXT")
    private String studentImagePath;

    @Comment("선생님 채점 화면 이미지 파일 경로")
    @Column(columnDefinition = "TEXT")
    private String teacherImagePath;

    @Comment("평가 자료 경로")
    @Column(columnDefinition = "nvarchar(1000)")
    private String resourcePath;


    /**
     * 신규 데이터 생성
     * TODO: 관리자 페이지에서 입력 받아야 함
     * */
    public NeEvalInfo init(NeEval neEval) {
        return NeEvalInfo.builder()
                .evalSubjectEn("Eval Subject")
                .evalSubjectKo("평가 주제")
                .evalPurposeImagePath("평가 목적 데이터")
                .evalConditionImagePath("과제 조건 데이터")
                .evalAchievementImagePath("성취 기준 데이터")
                .evalStandardImagePath("평가 기준 데이터")
                .studentImagePath("/images/writing_simulation.png")
                .teacherImagePath("/images/writing_grading.png")
                .neEval(neEval)
                .build();
    }
}
