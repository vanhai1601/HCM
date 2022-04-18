package vn.com.mbbank.services;

import vn.com.mbbank.dto.OrganizationDTO;
import vn.com.mbbank.dto.TreeDTO;

import java.util.List;

public interface MpOrganizationsService {

    List<TreeDTO> getRootNode();
    List<TreeDTO> initTree();
    List<TreeDTO> getNodeChildren(Long parentId);
    List<TreeDTO> searchOrg(Long parentId, String keyword);

    Object getByParent(OrganizationDTO dto);
}
