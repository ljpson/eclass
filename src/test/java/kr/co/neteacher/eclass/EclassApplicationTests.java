package kr.co.neteacher.eclass;

import kr.co.neteacher.eclass.entity.Teacher;
import kr.co.neteacher.eclass.entity.TeacherKlass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
@Commit
class EclassApplicationTests {
	/* 아래 테스트는 Entity에 관계 형성했을 때 가능한 테스트입니다 - 현재 사용불가
	 * 추후 연구목적으로 삭제하지 않음 */
	@Autowired
	EntityManager em;

	/**
	 * 담당 선생님에게 학급 추가
	 */

	@Test
	void ClassInsert() {
//		Long teacherId = 1L;
//		// 선생님 객체를 조회합니다.
//		Teacher teacher = em.find(Teacher.class, teacherId);
//
//		// 학급 엔티티를 생성합니다.
//		Klass class$ = new Klass()
//				.setClassName("1학년 4반")
//				.setCreateBy(1L)
//				.setCreateDate(new Date());
//
//		// 중개 테이블에 데이터를 저장합니다.
//		TeacherKlass classTeacher = new TeacherKlass();
//		classTeacher.setTeacher(teacher);
//		classTeacher.setClass$(class$);
//
//		// 저장합니다.
//		em.persist(teacher);
//		em.persist(class$);
//		em.persist(classTeacher);
	}

	/**
	 * 학급에 교사 추가 (보조선생님 추가)
	 */
	@Test
	void TeacherInsert() {
//		Long classId = 1L;
//		// 학급 객체를 조회합니다.
//		Klass class$ = em.find(Klass.class, classId);
//
//		// 선생님 엔티티를 생성합니다.
//		Teacher teacher = new Teacher()
//				.setTeacherName("곽선생")
//				.setNeId("NE0002");
//
//		// 중개 테이블에 데이터를 저장합니다.
//		TeacherKlass classTeacher = new TeacherKlass();
//		classTeacher.setTeacher(teacher);
//		classTeacher.setClass$(class$);
//
//		// 저장합니다.
//		em.persist(teacher);
//		em.persist(class$);
//		em.persist(classTeacher);
	}
	@Test
	void TeacherClassInsert() {
//		// 선생님 엔티티를 생성합니다.
//		Teacher teacher = new Teacher();
//		teacher.setTeacherName("손선생");
//
//		// 학급 엔티티를 생성합니다.
//		Klass class$ = new Klass();
//		class$.setClassName("3학년 1반");
//
//		// 중개 테이블에 데이터를 저장합니다.
//		TeacherKlass classTeacher = new TeacherKlass();
//		classTeacher.setTeacher(teacher);
//		classTeacher.setClass$(class$);
//
//		// 저장합니다.
//		em.persist(teacher);
//		em.persist(class$);
//		em.persist(classTeacher);
	}
	// 교사-학급 관계테이블
	@Test
	void TeacherClassFind() {
		Long teacherClassId = 1L;
		// 학급 객체를 조회합니다.
		TeacherKlass teacherKlass = em.find(TeacherKlass.class, teacherClassId);
		System.out.println("teacherKlass:"+ teacherKlass);
	}
	@Test
	void TeacherFind() {
		Long teacherId = 1L;
		// 학급 객체를 조회합니다.
		Teacher teacher = em.find(Teacher.class, teacherId);
		System.out.println("teacher:"+teacher);
	}
}
