package com.jww.ump.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 树模型
 *
 * @author Ricky Wang
 * @date 17/12/11 21:50:42
 */
@Data
public class SysTreeModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long parentId;
    private String name;
    private boolean spread;
    private boolean leaf;
    private int level;
    private String href;
    private Integer type;
    private List<SysTreeModel> children;
    /**
     * 是否选中
     */
    private Boolean checked;
    /**
     * 是否不可用
     */
    private Boolean disabled;
    /**
     * 节点图标
     */
    private String icon;
    /**
     * 权限
     */
    private String permission;


    private static SysTreeModel getRootNote(List<SysTreeModel> sysTreeModelList) {
        SysTreeModel rootNode = new SysTreeModel();
        rootNode.setParentId(-999L);
        rootNode.setId(0L);
        rootNode.setName("root");
        rootNode.setLevel(0);
        rootNode.setLeaf(false);
        return rootNode;
    }

    private static SysTreeModel constructTree(SysTreeModel rootNode, List<SysTreeModel> sysTreeModelList, int rootLevel) {
        if (sysTreeModelList == null || sysTreeModelList.size() == 0) {
            return null;
        }
        List<SysTreeModel> childNodeList = new ArrayList<SysTreeModel>();
        for (SysTreeModel sysTreeModel : sysTreeModelList) {
            if (sysTreeModel.getParentId().equals(rootNode.getId())) {
                SysTreeModel childNode = new SysTreeModel();
                childNode.setId(sysTreeModel.getId());
                childNode.setName(sysTreeModel.getName());
                childNode.setParentId(sysTreeModel.getParentId());
                childNode.setLevel(rootLevel + 1);
                childNode.setType(sysTreeModel.getType());
                childNodeList.add(childNode);
            }
        }
        rootNode.setChildren(childNodeList);
        if (childNodeList.size() == 0) {
            rootNode.setLeaf(true);
        } else {
            rootNode.setLeaf(false);
        }
        for (SysTreeModel sysTreeModel : childNodeList) {
            constructTree(sysTreeModel, sysTreeModelList, ++rootLevel);
            --rootLevel;
        }
        return rootNode;
    }

    public static List<SysTreeModel> getTree(List<SysTreeModel> sysTreeModelList) {
        SysTreeModel rootNode = SysTreeModel.getRootNote(sysTreeModelList);
        rootNode = SysTreeModel.constructTree(rootNode, sysTreeModelList, 0);
        return rootNode.getChildren();
    }
}
