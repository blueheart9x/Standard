package com.elcom.sharedbiz.dto;

import java.io.Serializable;
import java.util.List;

import com.elcom.data.user.entity.User;
import com.elcom.model.dto.MenuFunctionDTO;

public class UserDTO extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1560387296903450952L;
	private List<MenuFunctionDTO> funcLst;
	
	public List<MenuFunctionDTO> getFuncLst() {
		return funcLst;
	}

	public void setFuncLst(List<MenuFunctionDTO> funcLst) {
		this.funcLst = funcLst;
	}
}
