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


    private static SysTreeModel getRootNote(List<SysTreeModel> umpTreeModelList) {
        SysTreeModel rootNode = new SysTreeModel();
        rootNode.setParentId(-999L);
        rootNode.setId(0L);
        rootNode.setName("root");
        rootNode.setLevel(0);
        rootNode.setLeaf(false);
        return rootNode;
    }

    private static SysTreeModel constructTree(SysTreeModel rootNode, List<SysTreeModel> umpTreeModelList, int rootLevel) {
        if (umpTreeModelList == null || umpTreeModelList.size() == 0) {
            return null;
        }
        List<SysTreeModel> childNodeList = new ArrayList<SysTreeModel>();
        for (SysTreeModel umpTreeModel : umpTreeModelList) {
            if (umpTreeModel.getParentId().equals(rootNode.getId())) {
                SysTreeModel childNode = new SysTreeModel();
                childNode.setId(umpTreeModel.getId());
                childNode.setName(umpTreeModel.getName());
                childNode.setParentId(umpTreeModel.getParentId());
                childNode.setLevel(rootLevel + 1);
                childNode.setType(umpTreeModel.getType());
                childNodeList.add(childNode);
            }
        }
        rootNode.setChildren(childNodeList);
        if (childNodeList.size() == 0) {
            rootNode.setLeaf(true);
        } else {
            rootNode.setLeaf(false);
        }
        for (SysTreeModel umpTreeModel : childNodeList) {
            constructTree(umpTreeModel, umpTreeModelList, ++rootLevel);
            --rootLevel;
        }
        return rootNode;
    }

    public static List<SysTreeModel> getTree(List<SysTreeModel> umpTreeModelList) {
        SysTreeModel rootNode = SysTreeModel.getRootNote(umpTreeModelList);
        rootNode = SysTreeModel.constructTree(rootNode, umpTreeModelList, 0);
        return rootNode.getChildren();
    }
}
