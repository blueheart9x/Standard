package com.elcom.model.object;

import java.util.List;

public class ListObjectResult {
	List<Object> errorUpdate;
	List<Object> sucsessUpdate;

	public ListObjectResult() {
	}
	
	public ListObjectResult(List<Object> errorUpdate, List<Object> sucsessUpdate) {
		super();
		this.errorUpdate = errorUpdate;
		this.sucsessUpdate = sucsessUpdate;
	}
	
	public List<Object> getErrorUpdate() {
		return errorUpdate;
	}

	public void setErrorUpdate(List<Object> errorUpdate) {
		this.errorUpdate = errorUpdate;
	}

	public List<Object> getSucsessUpdate() {
		return sucsessUpdate;
	}

	public void setSucsessUpdate(List<Object> sucsessUpdate) {
		this.sucsessUpdate = sucsessUpdate;
	}

}
