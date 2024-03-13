package kr.co.neteacher.eclass.controller.api;

import kr.co.neteacher.eclass.controller.common.Const;
import kr.co.neteacher.eclass.dto.KlassDTO;
import kr.co.neteacher.eclass.dto.KlassQueryParams;
import kr.co.neteacher.eclass.entity.Klass;
import kr.co.neteacher.eclass.entity.TeacherKlass;
import kr.co.neteacher.eclass.service.KlassService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class KlassRestController extends DefaultRestController  {

    private final KlassService klassService;

    /**
     * 학급 정보 저장/수정
     * @param teacherId (교사아이디)
     * @param klass (학급)
     * @return 성공/실패 메시지
     */
    @PutMapping("/klass/save/{teacherId}")
    public DeferredResult<ResponseEntity<?>> saveKlass(@PathVariable Long teacherId,
                                                       @RequestBody Klass klass) {
        try {
            klassService.save(klass, teacherId);
            return successResponse(Const.MSG_018);
        } catch (Exception e) {
            return failResponse(Const.MSG_019 + e.getMessage());
        }
    }

    /**
     * 학급 목록 조회
     * @param queryParams (teacherId, klassName)
     * @return 검색조건에 맞는 학급 목록
     */
    @GetMapping("/klass/{teacherId}")
    public DeferredResult<ResponseEntity<?>> getKlassList(@PathVariable Long teacherId,
                                                          KlassQueryParams queryParams) {
        try {
            // 개설한 학급이 있는지 조회
            List<TeacherKlass> teacherKlassList = klassService.getTeacherKlassList(teacherId);
            if (teacherKlassList.isEmpty()) {
                //개설한 학급이 없으면 학급개설 알림 메시지
                return failResponse(Const.MSG_010);
            } else {
                // 개설학급이 있으면 목록 조회
                List<KlassDTO> list = klassService.getKlassList(queryParams);
                return successResponse(list);
            }
        } catch (Exception e) {
            return failResponse(e);
        }
    }

    /**
     * 학급 목록 조회 (학급아이디만 조회 - 학급정보 없음)
     * @param teacherId (교사아이디)
     * @return 교사가 담당하는 학급 목록
     */
    @GetMapping("/teacher/klass/{teacherId}")
    public DeferredResult<ResponseEntity<?>> getTeacherKlassList(@PathVariable Long teacherId) {
        try {
            List<TeacherKlass> list = klassService.getTeacherKlassList(teacherId);
            return successResponse(list);
        } catch (Exception e) {
            return failResponse(e);
        }
    }

    /**
     * 교사 목록 조회 (교사아이디만 조회 - 교사정보 없음)
     * @param klassId (학급아이디)
     * @return 학급을 담당하는 교사 목록
     */
    @GetMapping("/teacher/{klassId}")
    public DeferredResult<ResponseEntity<?>> getKlassTeacherList(@PathVariable Long klassId) {
        try {
            List<TeacherKlass> list = klassService.getKlassTeacherList(klassId);
            return successResponse(list);
        } catch (Exception e) {
            return failResponse(e);
        }
    }

    /**
     * 학급 정보 삭제(업데이트)
     * @param klass (학급)
     * @return 성공/실패 메시지
     */
    @PutMapping("/klass/delete")
    public DeferredResult<ResponseEntity<?>> updateDeleteYn(@RequestBody Klass klass) {
        try {
            klassService.updateDeleteYn(klass.getId());
            return successResponse(Const.MSG_013);
        } catch (Exception e) {
            return failResponse(Const.MSG_014 + e.getMessage());
        }
    }
}
