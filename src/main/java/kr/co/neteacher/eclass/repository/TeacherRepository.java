package kr.co.neteacher.eclass.repository;

import kr.co.neteacher.eclass.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByNeId(String neId);
}
