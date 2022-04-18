package vn.com.mbbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.com.mbbank.dto.MpPositionsDTO;
import vn.com.mbbank.services.MpPositionsService;
import vn.com.mbbank.utils.Constants;

@RestController
@RequestMapping(Constants.REQUEST_MAPPING_PREFIX)
public class MpPositionsController {

    @Autowired
    private MpPositionsService mpPositionsService;

    @RequestMapping(value = "/v1/mp-positions/org/{orgId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMpPositionsByOrg(Authentication authentication, @PathVariable Long orgId, MpPositionsDTO dto) {
        dto.setOrgId(orgId);
        return mpPositionsService.getMpPositionsByOrgId(dto);
    }

}
