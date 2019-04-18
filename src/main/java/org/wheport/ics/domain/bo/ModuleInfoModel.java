package org.wheport.ics.domain.bo;

import lombok.Data;
import org.wheport.ics.domain.pojo.ModuleInfo;

@Data
public class ModuleInfoModel extends ModuleInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * pc页面路径
	 */
	private String pageUrl;
	/**
	 * 手机页面路径
	 */
	private String mobileUrl;
	/**
	 * app页面路径
	 */
	private String appUrl;
}
