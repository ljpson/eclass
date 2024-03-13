package kr.co.neteacher.eclass.repository;

import kr.co.neteacher.eclass.entity.Klass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KlassRepository extends JpaRepository<Klass, Long>, KlassRepositoryExtra {


//    Optional<Klass> findByBookId(int id, Yn deleteYn);
//
//    List<Klass> findAllByIdAndDeleteYn(int id, Yn deleteYn);

}
