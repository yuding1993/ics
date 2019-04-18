package org.wheport.ics.domain.bo;

import lombok.Data;

@Data
public class PageModel {
	private Integer page;
	private Integer pageSize;
	private Integer startRecord;
	private Object requestObj;
	
	/**
	 * 传入页码和每页查询数，可直接使用此bean
	 * @param page
	 * @param pageSize
	 */
	public PageModel(Integer page, Integer pageSize){
		this.pageSize = pageSize;
		this.startRecord = page * pageSize - pageSize;
	}
}
