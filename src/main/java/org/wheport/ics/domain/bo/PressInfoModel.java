package org.wheport.ics.domain.bo;

import lombok.Data;
import org.wheport.ics.domain.pojo.DownloadFile;
import org.wheport.ics.domain.pojo.PressInfo;

import java.util.List;


@Data
public class PressInfoModel extends PressInfo {

	private static final long serialVersionUID = 1L;
	
	private String isRelImg;
	private String relImgUrl;
	private Integer isRelDownload;
	private List<DownloadFile> fileList;
}
