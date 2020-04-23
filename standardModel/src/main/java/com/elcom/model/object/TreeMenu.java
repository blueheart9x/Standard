package com.elcom.model.object;

import java.util.List;

public class TreeMenu {

	@ColumnIndex(index=0)
	private Integer parentId;
	@ColumnIndex(index=1)
	private String parentCode;
	@ColumnIndex(index=2)
	private String parentTitle;
	@ColumnIndex(index=3)
	private String parentModule;
	@ColumnIndex(index=4)
	private String parentType;
	@ColumnIndex(index=5)
	private Integer menuId;
	@ColumnIndex(index=6)
	private String menuCode;
	@ColumnIndex(index=7)
	private String menuTitle;
	@ColumnIndex(index=8)
	private String menuModule;
	@ColumnIndex(index=9)
	private String menuType;
	@ColumnIndex(index=10)
	private Integer treeLevel;
	private List<TreeMenu> lstChild;

	public TreeMenu() {
	}
	
	public TreeMenu(Integer parentId, String parentCode, String parentTitle, String parentModule, String parentType, Integer menuId,
			String menuCode, String menuTitle, String menuModule, String menuType, Integer treeLevel, List<TreeMenu> lstChild) {
		this.parentId = parentId;
		this.parentCode = parentCode;
		this.parentTitle = parentTitle;
		this.parentModule = parentModule;
		this.parentType = parentType;
		this.menuId = menuId;
		this.menuCode = menuCode;
		this.menuTitle = menuTitle;
		this.menuModule = menuModule;
		this.menuType = menuType;
		this.treeLevel = treeLevel;
		this.lstChild = lstChild;
	}
	
	public List<TreeMenu> getLstChild() {
		return lstChild;
	}

	public void setLstChild(List<TreeMenu> lstChild) {
		this.lstChild = lstChild;
	}

	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getParentTitle() {
		return parentTitle;
	}
	public void setParentTitle(String parentTitle) {
		this.parentTitle = parentTitle;
	}
	public String getParentModule() {
		return parentModule;
	}
	public void setParentModule(String parentModule) {
		this.parentModule = parentModule;
	}
	public String getParentType() {
		return parentType;
	}
	public void setParentType(String parentType) {
		this.parentType = parentType;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getMenuTitle() {
		return menuTitle;
	}
	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}
	public String getMenuModule() {
		return menuModule;
	}
	public void setMenuModule(String menuModule) {
		this.menuModule = menuModule;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public Integer getTreeLevel() {
		return treeLevel;
	}
	public void setTreeLevel(Integer treeLevel) {
		this.treeLevel = treeLevel;
	}

}
