package kr.co.neteacher.eclass.repository;

import kr.co.neteacher.eclass.dto.KlassQueryParams;
import kr.co.neteacher.eclass.dto.KlassDTO;

import java.util.List;

public interface KlassRepositoryExtra {

    List<KlassDTO> findKlassListByKlassQueryParams(KlassQueryParams queryParams);

}
