package kr.co.neteacher.eclass.service;

import kr.co.neteacher.eclass.dto.KlassDTO;
import kr.co.neteacher.eclass.dto.KlassQueryParams;
import kr.co.neteacher.eclass.entity.Klass;
import kr.co.neteacher.eclass.entity.TeacherKlass;
import kr.co.neteacher.eclass.entity.enums.Yn;
import kr.co.neteacher.eclass.repository.KlassRepository;
import kr.co.neteacher.eclass.repository.TeacherKlassRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class KlassService {

    private final KlassRepository klassRepository;
    private final TeacherKlassRepository teacherKlassRepository;


    /**
     * 학급 정보 저장 / 수정
     * @param klass (학급아이디)
     * @param teacherId (교사아이디)
     */
    public void save(Klass klass, Long teacherId) {

        if (klass.getId() == null){
            // 신규 저장
            klassRepository.save(klass);
            // 교사-학급 관계저장
            TeacherKlass teacherKlass = new TeacherKlass()
                    .setKlassId(klass.getId())
                    .setTeacherId(teacherId)
                    .setDeleteYn(Yn.N);
            teacherKlassRepository.save(teacherKlass);
        } else {
            // 수정
            klassRepository.save(klass);
        }
    }

    /**
     * 학급 목록 - 검색포함
     * @param queryParams 검색 조건 (teacherId, KlassName)
     * @return List<KlassDTO> - 조인하여 교사정보까지 함께 받아옴
     */
    public List<KlassDTO> getKlassList(KlassQueryParams queryParams) {
        // 조인하여 KlassDTO로 값을 받아옴
        return klassRepository.findKlassListByKlassQueryParams(queryParams);
    }

    /**
     * 교사(teacherId)가 담당하는 학급 목록 - 학급정보없음(학급아이디만 조회)
     * @param teacherId (교사아이디)
     * @return 교사학급 목록
     */
    public List<TeacherKlass> getTeacherKlassList(Long teacherId) {
        // 교사(teacherId)가 담당하는 학급 조회
        List<TeacherKlass> list = teacherKlassRepository.findAllByTeacherIdAndDeleteYn(teacherId, Yn.N);
        return list;
    }

    /**
     * 학급(klassId)에 속한 교사 목록 - 교사정보없음(교사아이디만 조회)
     * @param klassId (학급아이디)
     * @return 교사 목록
     */
    public List<TeacherKlass> getKlassTeacherList(Long klassId) {
        // 학급(klassId)에 속한 교사조회
        List<TeacherKlass> list = teacherKlassRepository.findAllByKlassIdAndDeleteYn(klassId, Yn.N);
        return list;
    }

    /**
     * 학급 삭제(deleteYn 수정)
     * @param klassId (학급아이디)
     */
    public void updateDeleteYn(Long klassId) {
        // 학급 정보조회
        Optional<Klass> klass = klassRepository.findById(klassId);
        // 교사-학급 관계조회
        Optional<TeacherKlass> teacherklass = teacherKlassRepository.findById(klassId);
        // 학급정보에 deleteYn 수정
        klass.ifPresent(k -> {
            k.setDeleteYn(Yn.Y);
            klassRepository.save(k);
        });
        // 교사-학급정보에 deleteYn 수정
        teacherklass.ifPresent(tk -> {
            tk.setDeleteYn(Yn.Y);
            teacherKlassRepository.save(tk);
        });
    }

}
