package com.elcom.model.object;

public class State {

	private int status;

	public State() {
	}
	
	public State(int status) {
		setStatus(status);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}