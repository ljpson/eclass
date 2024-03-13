package kr.co.neteacher.eclass.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.neteacher.eclass.dto.KlassDTO;
import kr.co.neteacher.eclass.dto.KlassQueryParams;
import kr.co.neteacher.eclass.entity.Klass;
import kr.co.neteacher.eclass.entity.enums.Yn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import java.util.List;

import static kr.co.neteacher.eclass.entity.QKlass.klass;
import static kr.co.neteacher.eclass.entity.QTeacher.teacher;
import static kr.co.neteacher.eclass.entity.QTeacherKlass.teacherKlass;
import static org.springframework.util.StringUtils.hasText;

@Slf4j
public class KlassRepositoryImpl extends QuerydslRepositorySupport implements KlassRepositoryExtra {

    final EntityManager em;

    public KlassRepositoryImpl(EntityManager em) {
        super(Klass.class);
        this.em = em;
    }
    /**
     * 학급 목록
     *
     * @param queryParams 검색 조건 (teacherId, KlassName)
     * @return 학급 목록 (학급 생성 역순)
     */
    @Override

    public List<KlassDTO> findKlassListByKlassQueryParams(KlassQueryParams queryParams) {

        log.debug("queryParams ::{}", queryParams);

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory
                .select(Projections.fields(KlassDTO.class,
                        klass.id.as("klassId"),
                        klass.bookId,
                        klass.gradeType,
                        klass.klassName,
                        klass.password,
                        klass.studentLimitNum,
                        klass.deleteYn,
                        klass.createBy,
                        klass.createDate,
                        teacherKlass.teacherId,
                        teacherKlass.teacherType,
                        teacherKlass.submitYn,
                        teacher.neId,
                        teacher.teacherName))
                .from(klass)
                .join(teacherKlass)
                .on(teacherKlass.klassId.eq(klass.id))
                .join(teacher)
                .on(teacher.id.eq(teacherKlass.teacherId))
                .where(klass.deleteYn.eq(Yn.N))
                .where(teacherKlass.deleteYn.eq(Yn.N))
                .where(teacherIdEq(queryParams.getTeacherId()))
                .where(klassNameEq(queryParams.getKlassName()))
                .orderBy(klass.createDate.desc())
                .fetch();

    }
    /**
     * 검색조건
     */
    // 교사 아이디
    private BooleanExpression teacherIdEq(Long teacherId) {
        return hasText(String.valueOf(teacherId)) ? teacherKlass.teacherId.eq(teacherId) : null;
    }
    // 학급명
    private BooleanExpression klassNameEq(String klassName) {
        return hasText(klassName) ? klass.klassName.contains(klassName) : null;
    }

}
