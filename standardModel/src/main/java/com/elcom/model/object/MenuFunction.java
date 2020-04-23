package com.elcom.model.object;

public class MenuFunction {

	@ColumnIndex(index=0)
	private Integer treeLevel;
	@ColumnIndex(index=1)
	private String menuModule;
	@ColumnIndex(index=2)
	private String menuCode;
	@ColumnIndex(index=3)
	private String menuTitle;
	@ColumnIndex(index=4)
	private Integer parentId;
	@ColumnIndex(index=5)
	private String menuType;
	@ColumnIndex(index=6)
	private String objectType;
	@ColumnIndex(index=7)
	private String accessRight;
	@ColumnIndex(index=8)
	private Integer roleId;
	@ColumnIndex(index=9)
	private Integer functionId;
	@ColumnIndex(index=10)
	private String module;
	@ColumnIndex(index=11)
	private String functionCode;
	@ColumnIndex(index=12)
	private String url;
	@ColumnIndex(index=13)
	private Integer menuId;

	private boolean noAccess;
	private boolean create;
	private boolean edit;
	private boolean delete;
	private boolean view;

	public String getMenuModule() {
		return menuModule;
	}
	public void setMenuModule(String menuModule) {
		this.menuModule = menuModule;
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
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public String getAccessRight() {
		return accessRight;
	}
	public void setAccessRight(String accessRight) {
		this.accessRight = accessRight;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getFunctionCode() {
		return functionCode;
	}
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
	public Integer getTreeLevel() {
		return treeLevel;
	}
	public void setTreeLevel(Integer treeLevel) {
		this.treeLevel = treeLevel;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public boolean isNoAccess() {
		noAccess = "Y".equals(this.accessRight.substring(0, 1));
		return noAccess;
	}
	public void setNoAccess(boolean noAccess) {
		this.noAccess = noAccess;
	}
	public boolean isCreate() {
		create = "Y".equals(this.accessRight.substring(1, 2));
		return create;
	}
	public void setCreate(boolean create) {
		this.create = create;
	}
	public boolean isEdit() {
		edit = "Y".equals(this.accessRight.substring(2, 3));
		return edit;
	}
	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	public boolean isDelete() {
		delete = "Y".equals(this.accessRight.substring(3, 4));
		return delete;
	}
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	public boolean isView() {
		view = "Y".equals(this.accessRight.substring(4, 5));
		return view;
	}
	public void setView(boolean view) {
		this.view = view;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getFunctionId() {
		return functionId;
	}
	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}

}
