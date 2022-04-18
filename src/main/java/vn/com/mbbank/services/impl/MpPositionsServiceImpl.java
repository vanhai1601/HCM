/*
 * Copyright (C) 2022 HRPLUS. All rights reserved.
 * EcoIT. Use is subject to license terms.
 */
package vn.com.mbbank.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.com.mbbank.dto.MpPositionsDTO;
import vn.com.mbbank.repositories.impl.MpPositionsRepositoryImpl;
import vn.com.mbbank.services.MpPositionsService;
import vn.com.mbbank.utils.ResponseUtils;

/**
 * Lop impl service
 * @author author
 * @since 1.0
 * @version 1.0
 */
@Service
public class MpPositionsServiceImpl implements MpPositionsService {
    
    @Autowired
    private MpPositionsRepositoryImpl mpPositionRepositoryImpl;

    @Override
    public ResponseEntity<Object> getMpPositionsByOrgId(MpPositionsDTO dto) {
        List<MpPositionsDTO> reslut = mpPositionRepositoryImpl.getMpPositionsByOrgId(dto);
        return ResponseUtils.getResponseSucessEntity(reslut);
    }

    
}
