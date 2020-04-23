package com.elcom.model.object;

public class DebugInfor {

	private long timeStamp;
	private String currentDateTime;
	
	public DebugInfor(long ts, String dt) {
		this.timeStamp = ts;
		this.currentDateTime = dt;
	}

	public DebugInfor() {
		this.timeStamp = 0;
		this.currentDateTime = "";
	}

	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getCurrentDateTime() {
		return currentDateTime;
	}
	public void setCurrentDateTime(String currentDateTime) {
		this.currentDateTime = currentDateTime;
	}
}
