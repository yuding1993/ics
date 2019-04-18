package org.wheport.ics.domain.bo;

import lombok.Data;
import org.wheport.ics.domain.pojo.QuestionAndAnswers;

@Data
public class QuestionAndAnswersModel extends QuestionAndAnswers {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean showOrHide;

}
