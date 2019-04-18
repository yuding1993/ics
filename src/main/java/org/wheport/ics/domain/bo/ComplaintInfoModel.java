package org.wheport.ics.domain.bo;

import lombok.Data;
import org.wheport.ics.domain.pojo.ComplaintInfo;

@Data
public class ComplaintInfoModel extends ComplaintInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String captcha;
}
