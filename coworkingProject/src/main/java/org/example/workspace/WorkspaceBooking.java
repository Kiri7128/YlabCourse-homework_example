package org.example.workspace;

import java.util.Date;

public class WorkspaceBooking
{
    //private String Workspaceid;
    private String userId;
    private Date startTime;
    private Date endTime;

    public WorkspaceBooking(String userId, Date startTime, Date endTime){
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
