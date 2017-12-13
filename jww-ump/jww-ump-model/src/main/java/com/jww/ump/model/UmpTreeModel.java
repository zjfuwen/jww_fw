package com.jww.ump.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Title:
 * @Description:
 * @Author: Ricky Wang
 * @Date: 17/12/11 21:50:42
 */
@Data
public class UmpTreeModel  implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long parentId;
    private String name;
    private boolean spread;
    private boolean leaf;
    private int level;
    private String href;
    private List<UmpTreeModel> children;


    public static UmpTreeModel constructTree(UmpTreeModel rootNode, List<UmpTreeModel> umpTreeModelList, int rootLevel){
        if(umpTreeModelList==null || umpTreeModelList.size()==0){
            return null;
        }
        rootNode.setParentId(0L);
        rootNode.setLevel(0);
        rootNode.setLeaf(false);
        List<UmpTreeModel> childNodeList = new ArrayList<UmpTreeModel>();
        for(UmpTreeModel umpTreeModel: umpTreeModelList){
            if(umpTreeModel.getParentId().equals(rootNode.getId())){
                UmpTreeModel childNode = new UmpTreeModel();
                childNode.setId(umpTreeModel.getId());
                childNode.setName(umpTreeModel.getName());
                childNode.setParentId(umpTreeModel.getParentId());
                childNode.setLevel(rootLevel+1);
                childNodeList.add(childNode);
            }
        }
        rootNode.setChildren(childNodeList);
        if(childNodeList.size()==0){
            rootNode.setLeaf(true);
        } else {
            rootNode.setLeaf(false);
        }
        for(UmpTreeModel umpTreeModel : childNodeList){
            constructTree(umpTreeModel, umpTreeModelList, ++rootLevel);
            --rootLevel;
        }
        return rootNode;
    }
}
