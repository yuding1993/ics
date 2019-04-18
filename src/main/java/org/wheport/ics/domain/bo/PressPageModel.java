package org.wheport.ics.domain.bo;

import lombok.Data;
import org.wheport.ics.domain.pojo.PressCat;

import java.util.List;

@Data
public class PressPageModel extends PressCat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer nowPage;
	private List<Integer> pageList;
	private List<PressInfoModel> pressInfoList;
}
