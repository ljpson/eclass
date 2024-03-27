package kr.co.neteacher.eclass.service;

import kr.co.neteacher.eclass.entity.Teacher;
import kr.co.neteacher.eclass.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeacherService {

    private final TeacherRepository teacherRepository;


    /**
     * 선생님 정보 가져오기
     *
     * @param teacherId 선생님 ID
     * @return 선생님 정보
     */
    public Optional<Teacher> get(long teacherId) {
        return teacherRepository.findById(teacherId);
    }


    /**
     * 전체 선생님 정보 가져오기
     *
     * @return 선생님 정보
     */
    public List<Teacher> getAllTeacherList() {
        return teacherRepository.findAll();
    }

    /**
     * 선생님 정보 가져오기
     *
     * @param neId 능률아이디
     * @return 선생님 정보
     */
    @Transactional
    public Optional<Teacher> getTeacher(String neId, String teacherName) {

        // 교사정보 조회
        Optional<Teacher> storedTeacher = teacherRepository.findByNeId(neId);

        // 가입 정보가 없으면 가입 후 생성된 아이디 포함한 교사정보 다시 조회
        if (storedTeacher.isEmpty()) {
            // 신규 교사 등록
            Teacher newTeacher = teacherRepository.save(Teacher.builder()
                    .neId(neId)
                    .teacherName(teacherName)
                    .build());

            // 리턴 용 교사정보 (저장할 때 생성된 아이디 포함)
            storedTeacher = Optional.of(newTeacher);
        }

        return storedTeacher;
    }

    /**
     * 선생님 검색
     *
     * @param neId 능률아이디
     * @return 선생님 정보
     */
    public Optional<Teacher> searchTeacher(String neId) {
        // 교사정보 조회
        Optional<Teacher> searchTeacher = teacherRepository.findByNeId(neId);

        return searchTeacher;
    }

}