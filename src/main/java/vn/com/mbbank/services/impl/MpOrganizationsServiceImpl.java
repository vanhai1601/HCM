package vn.com.mbbank.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.mbbank.dto.OrganizationDTO;
import vn.com.mbbank.dto.TreeDTO;
import vn.com.mbbank.repositories.impl.MpOrganizationsRepositoryImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vn.com.mbbank.services.MpOrganizationsService;

@Service
public class MpOrganizationsServiceImpl implements MpOrganizationsService {
    @Autowired
    private MpOrganizationsRepositoryImpl organizationRepositoryImpl;

    @Override
    public List<TreeDTO> getRootNode() {
        return organizationRepositoryImpl.getRootNode();
    }

    @Override
    public List<TreeDTO> initTree() {
        List<TreeDTO> listAll = organizationRepositoryImpl.getListTreeNodes();
        Map<Long, List<TreeDTO>> mapChildrens = new HashMap();
        List<TreeDTO> rootNodes = new ArrayList();
        for (TreeDTO bean : listAll) {
            if (bean.getParentId() == null) {
                rootNodes.add(bean);
            } else {
                if (mapChildrens.get(bean.getParentId()) == null) {
                    mapChildrens.put(bean.getParentId(), new ArrayList());
                }
                mapChildrens.get(bean.getParentId()).add(bean);
            }
        }
        for (TreeDTO rootNode : rootNodes) {
            addChildrens(rootNode, mapChildrens);
        }

        return rootNodes;
    }

    @Override
    public List<TreeDTO> getNodeChildren(Long parentId) {
        return organizationRepositoryImpl.getNodeChildren(parentId);
    }

    @Override
    public List<TreeDTO> searchOrg(Long parentId, String keyword) {
        List<TreeDTO> resultSearch = organizationRepositoryImpl.searchOrg(parentId, keyword);
        // xu ly lay path
        List<String> listPath = new ArrayList<>();
        if (resultSearch != null && !resultSearch.isEmpty()) {
            for (TreeDTO bean : resultSearch) {
                listPath.add(bean.getPath());
            }
        }
        // lay danh sach don vi the0 path
        List<TreeDTO> listOrgByPath = organizationRepositoryImpl.searchOrgByPath(listPath);
        if (listOrgByPath == null || listOrgByPath.isEmpty()) {
            return new ArrayList<>();
        }
        Map<Long, List<TreeDTO>> mapChildrens = new HashMap();
        List<TreeDTO> rootNodes = new ArrayList();
        for (TreeDTO bean : listOrgByPath) {
            if (bean.getParentId() == null) {
                rootNodes.add(bean);
            } else {
                if (mapChildrens.get(bean.getParentId()) == null) {
                    mapChildrens.put(bean.getParentId(), new ArrayList());
                }
                mapChildrens.get(bean.getParentId()).add(bean);
            }
        }
        for (TreeDTO rootNode : rootNodes) {
            addChildrens(rootNode, mapChildrens);
        }

        return rootNodes;
    }

    public Object getByParent(OrganizationDTO dto) {
        return organizationRepositoryImpl.getByParent(dto);
    }

    private void addChildrens(TreeDTO rootNode, Map<Long, List<TreeDTO>> mapChildrens) {
        List<TreeDTO> childrens = mapChildrens.get(rootNode.getNodeId());
        if (childrens == null || childrens.isEmpty()) {
            return;
        } else {
            rootNode.setChildrens(childrens);
            for (TreeDTO child : childrens) {
                addChildrens(child, mapChildrens);
            }
        }
    }
}
