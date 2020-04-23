package com.elcom.model.enums;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public enum DateFormatTag {
	DATEFORMAT_dd_MM_yyyy("dd/MM/yyyy"),
	DATEFORMAT_yyyy_MM_dd("yyyy/MM/dd"),
	DATEFORMAT_dd_MM_yyyy_HH_mm_ss("dd-MM-yyyy HH:mm:ss"), 
	DATEFORMAT_slash_dd_MM_yyyy_HH_mm_ss("dd/MM/yyyy HH:mm:ss"),
	DATEFORMAT_yyyy_MM_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss"),
	DATEFORMAT_dd_MM_yyyy_T_HH_mm_ss("dd-MM-yyyy'T'HH:mm:ss"),	
	DATEFORMAT_dd_MM_yyyy_T_HH_mm_ss_000Z("yyyy-MM-dd'T'HH:mm:ss'.000Z'"),
	DATEFORMAT_ECM("yyyy-MM-dd'T'HH:mm:ss.SSS+0000");
	

	private final String value;

	DateFormatTag(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	public DateFormat formatter() {
		return new SimpleDateFormat(this.value);
	}

}
