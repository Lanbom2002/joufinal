package com.jou.demo.DO;

import java.time.LocalDateTime;

public class SurveyDO {
    private String surveysID;
    private String title_0;
    private String description;
    private LocalDateTime dateTime;
    public String getTitle_0() {
        return title_0;
    }

    public void setTitle_0(String title_0) {
        this.title_0 = title_0;
    }

    public String getSurveysID() {
        return surveysID;
    }

    public void setSurveysID(String surveysID) {
        this.surveysID = surveysID;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
