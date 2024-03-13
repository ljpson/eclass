package kr.co.neteacher.eclass.repository;

import kr.co.neteacher.eclass.entity.TeacherKlass;
import kr.co.neteacher.eclass.entity.enums.Yn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherKlassRepository extends JpaRepository<TeacherKlass, Long> {

    /** 학급(klassId)에 속한 교사 조회 */
    List<TeacherKlass> findAllByKlassIdAndDeleteYn(Long KlassId, Yn deleteYn);

    /** 교사(teacherId)가 담당하는 학급 조회 */
    List<TeacherKlass> findAllByTeacherIdAndDeleteYn(Long TeacherId, Yn deleteYn);

}
