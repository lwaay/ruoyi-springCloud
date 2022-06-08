package com.sinonc.agriculture.dto;

import com.sinonc.agriculture.domain.AgriQuestion;
import com.sinonc.agriculture.domain.GrowTech;
import com.sinonc.agriculture.domain.OwnDynamic;
import com.sinonc.agriculture.domain.QuestionAnswer;

import java.util.List;

public class OwnDynamicDto extends OwnDynamic {

    /**
     * 回答数，即评论数
     */
    private Integer answerCount;

    /**
     * 是否关注
     */
    private boolean isConcern;

    /**
     * 关注会员昵称
     */
    private List<String> concernMemberList;

    /**
     * 作物名称
     */
    private String cropName;

    /**
     * 第一个回答
     */
    private QuestionAnswer firstQuestionAnswer;

    /**
     * 问题
     */
    private AgriQuestion agriQuestion;

    /**
     * 种养殖技术
     */
    private GrowTech growTech;


    public GrowTech getGrowTech() {
        return growTech;
    }

    public void setGrowTech(GrowTech growTech) {
        this.growTech = growTech;
    }

    public AgriQuestion getAgriQuestion() {
        return agriQuestion;
    }

    public void setAgriQuestion(AgriQuestion agriQuestion) {
        this.agriQuestion = agriQuestion;
    }

    public QuestionAnswer getFirstQuestionAnswer() {
        return firstQuestionAnswer;
    }

    public void setFirstQuestionAnswer(QuestionAnswer firstQuestionAnswer) {
        this.firstQuestionAnswer = firstQuestionAnswer;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public List<String> getConcernMemberList() {
        return concernMemberList;
    }

    public void setConcernMemberList(List<String> concernMemberList) {
        this.concernMemberList = concernMemberList;
    }

    public boolean isConcern() {
        return isConcern;
    }

    public void setConcern(boolean concern) {
        isConcern = concern;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

}
