package com.elcom.model.object;

import java.io.Serializable;

public class StateResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3708406064896404584L;
	
	private boolean status;
	private Long ts; //timestamp

	public StateResponse(){}
	
	public Long getTs() {
		return ts;
	}

	public void setTs(Long ts) {
		this.ts = ts;
	}

	public StateResponse(boolean state){
		this.status = state;
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
