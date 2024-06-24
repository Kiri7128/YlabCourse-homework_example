package org.example.conferenceroom;

import java.util.Date;

public class ConferenceBooking {
    private String userId;
    private Date startTime;
    private Date endTime;

    public ConferenceBooking(String userId, Date startTime, Date endTime){
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }
    public Date getendTime() {
        return endTime;
    }
    public String getUserId() {
        return userId;
    }
}
