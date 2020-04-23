package com.elcom.model.object;


public class ServicePermission {

	@ColumnIndex(index=0)
	private String objectType;
	@ColumnIndex(index=1)
	private String accessRight;
	@ColumnIndex(index=2)
	private Integer roleId;
	@ColumnIndex(index=3)
	private Integer serviceId;
	@ColumnIndex(index=4)
	private String module;
	@ColumnIndex(index=5)
	private String serviceName;
	@ColumnIndex(index=6)
	private String servicePath;
	@ColumnIndex(index=7)
	private String postingGroup;

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
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServicePath() {
		return servicePath;
	}
	public void setServicePath(String servicePath) {
		this.servicePath = servicePath;
	}
	public String getPostingGroup() {
		return postingGroup;
	}
	public void setPostingGroup(String postingGroup) {
		this.postingGroup = postingGroup;
	}

}
