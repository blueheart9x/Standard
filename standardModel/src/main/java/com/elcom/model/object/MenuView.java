package com.elcom.model.object;

import java.util.List;

public class MenuView {

	private Long id;
	private Long parentId;
	private String menuName;
	private List<MenuView> childMenuList;

	public MenuView(Long id, String menuName, Long parentId) {
		this.id = id;
		this.parentId = parentId;
		this.menuName = menuName;
	}
	
	public MenuView(Long id, String menuName, Long parentId, List<MenuView> childMenuList) {
		this.id = id;
		this.parentId = parentId;
		this.menuName = menuName;
		this.childMenuList = childMenuList;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public List<MenuView> getChildMenuList() {
		return childMenuList;
	}

	public void setChildMenuList(List<MenuView> childMenuList) {
		this.childMenuList = childMenuList;
	}
}
