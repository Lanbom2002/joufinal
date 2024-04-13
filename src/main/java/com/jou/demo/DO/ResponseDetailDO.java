package com.jou.demo.DO;

public class ResponseDetailDO {
    private String responseDetailID;
    private String responseID;

    public String getResponseDetailID() {
        return responseDetailID;
    }

    public void setResponseDetailID(String responseDetailID) {
        this.responseDetailID = responseDetailID;
    }

    public String getResponseID() {
        return responseID;
    }

    public void setResponseID(String responseID) {
        this.responseID = responseID;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getOptionID() {
        return optionID;
    }

    public void setOptionID(String optionID) {
        this.optionID = optionID;
    }

    public String getTextAnswer() {
        return textAnswer;
    }

    public void setTextAnswer(String textAnswer) {
        this.textAnswer = textAnswer;
    }

    private String questionID;
    private String optionID;
    private String textAnswer;

}
