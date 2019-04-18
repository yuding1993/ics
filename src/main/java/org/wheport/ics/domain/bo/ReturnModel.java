package org.wheport.ics.domain.bo;

import lombok.Data;
import org.wheport.ics.domain.EnumConstants;

import java.io.Serializable;

@Data
public class ReturnModel implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final String SUCCESS = "success";
	private static final String FAILED = "failed";

	private String code;
	private String msg;
	private Object obj;

	public ReturnModel() {
		this.msg = SUCCESS;
		this.code = EnumConstants.RETURN_SUCCESS.getValue();
	}

	public static ReturnModel getErrorInstance(){
		ReturnModel returnModel = new ReturnModel();
		returnModel.setCode(EnumConstants.RETURN_FAIL.getValue());
		returnModel.setMsg(FAILED);
		return returnModel;
	}
}
