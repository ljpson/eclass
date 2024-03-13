package kr.co.neteacher.eclass.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 교사 정보
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Table(name = "t_teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String neId;
    private String teacherName;

//    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
//    private List<TeacherKlass> teacherKlasses;

}