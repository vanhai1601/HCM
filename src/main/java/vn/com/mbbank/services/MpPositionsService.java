package vn.com.mbbank.services;

import org.springframework.http.ResponseEntity;
import vn.com.mbbank.dto.MpPositionsDTO;

public interface MpPositionsService {

    ResponseEntity<Object> getMpPositionsByOrgId(MpPositionsDTO dto);
}
