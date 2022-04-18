package vn.com.mbbank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import vn.com.mbbank.dto.OrganizationDTO;
import vn.com.mbbank.utils.ErrorApp;

import javax.servlet.http.HttpServletRequest;
import vn.com.mbbank.utils.ResponseUtils;
import vn.com.mbbank.services.MpOrganizationsService;

@RestController
@RequestMapping(value = "/api/v1/org-tree")
public class MpOrganizationsController {

    @Autowired
    private MpOrganizationsService organizationService;

    @RequestMapping(value = "/root-node", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> rootNode(Authentication authentication, HttpServletRequest req) {
        return ResponseUtils.getResponseSucessEntity(organizationService.getRootNode());
    }
    
    @RequestMapping(value = "/init-tree", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> initTree(Authentication authentication, HttpServletRequest req) {
        return ResponseUtils.getResponseSucessEntity(organizationService.initTree());
    }

    @RequestMapping(value = "/lazy-load-node", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> lazyLoadNode(Authentication authentication, HttpServletRequest req,
            @RequestParam(value = "parentId") Long parentId) throws Exception {
        return ResponseUtils.getResponseEntity(ErrorApp.SUCCESS, organizationService.getNodeChildren(parentId));
    }

    @RequestMapping(value = "/search-node", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> searchNode(Authentication authentication, HttpServletRequest req,
         @RequestParam(value = "parentId", required = false, defaultValue = "0") Long parentId,
         @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) throws Exception {
        return ResponseUtils.getResponseSucessEntity(organizationService.searchOrg(parentId, keyword));
    }

    @RequestMapping(value = "/get-by-parent/{parentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getByParent(Authentication authentication, HttpServletRequest req,
                                              @PathVariable(name = "parentId") Long parentId,
                                              OrganizationDTO org) {
        org.setParentId(parentId);
        return ResponseUtils.getResponseSucessEntity(organizationService.getByParent(org));
    }
}
