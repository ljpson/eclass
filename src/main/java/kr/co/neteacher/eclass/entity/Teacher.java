package kr.co.neteacher.eclass.entity;

import kr.co.neteacher.eclass.entity.enums.Yn;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 선생님
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Table(name = "t_teacher")
public class Teacher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    @Comment("능률 아이디")
    private String neId;

    @Column(length = 20, nullable = false)
    @Comment("선생님 이름")
    private String teacherName;

    @Comment("초기 비밀번호")
    private String password;

    @Enumerated(EnumType.STRING)
    @Comment("온보딩 설정 여부")
    private Yn onboardingSetting = Yn.N;

//    @JsonIgnore
//    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
//    private List<LikeEval> likeEvals = new ArrayList<>();


}
