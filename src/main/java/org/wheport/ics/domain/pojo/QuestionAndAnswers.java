package org.wheport.ics.domain.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionAndAnswers extends BaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 问答名称
     */
    private String qAName;

    /**
     * 问答排序
     */
    private Integer sort;

    /**
     * 是否启用 y/n
     */
    private String workStatus;

    /**
     * 问题
     */
    private String question;

    /**
     * 答案
     */
    private String answer;
}