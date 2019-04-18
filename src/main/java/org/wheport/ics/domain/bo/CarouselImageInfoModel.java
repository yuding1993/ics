package org.wheport.ics.domain.bo;


import lombok.Data;
import org.wheport.ics.domain.pojo.CarouselImageInfo;

@Data
public class CarouselImageInfoModel extends CarouselImageInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 资讯id
	 */
	private String pressId;
	/**
	 * 资讯一级分类id
	 */
	private Integer pressParentCatId;
	/**
	 * 资讯所属模块id
	 */
	private Integer pressBelongModuleId;
	
}
