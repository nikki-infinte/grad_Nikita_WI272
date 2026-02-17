package com.maintenance.model;

import java.sql.Date;

public class SiteRequest {
    private int requestId;
    private int sid;
    private int uid;
    private int newLength;
    private int newWidth;
    private String newSiteType;
    private String status;
    private Date requestDate;

    public SiteRequest() {}

    public SiteRequest(int requestId, int sid, int uid, int newLength, int newWidth, String newSiteType, String status, Date requestDate) {
        this.requestId = requestId;
        this.sid = sid;
        this.uid = uid;
        this.newLength = newLength;
        this.newWidth = newWidth;
        this.newSiteType = newSiteType;
        this.status = status;
        this.requestDate = requestDate;
    }

    public SiteRequest(int sid, int uid, int newLength, int newWidth, String newSiteType) {
        this.sid = sid;
        this.uid = uid;
        this.newLength = newLength;
        this.newWidth = newWidth;
        this.newSiteType = newSiteType;
    }

    public int getRequestId() { return requestId; }
    public void setRequestId(int requestId) { this.requestId = requestId; }

    public int getSid() { return sid; }
    public void setSid(int sid) { this.sid = sid; }

    public int getUid() { return uid; }
    public void setUid(int uid) { this.uid = uid; }

    public int getNewLength() { return newLength; }
    public void setNewLength(int newLength) { this.newLength = newLength; }

    public int getNewWidth() { return newWidth; }
    public void setNewWidth(int newWidth) { this.newWidth = newWidth; }

    public String getNewSiteType() { return newSiteType; }
    public void setNewSiteType(String newSiteType) { this.newSiteType = newSiteType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getRequestDate() { return requestDate; }
    public void setRequestDate(Date requestDate) { this.requestDate = requestDate; }

    @Override
    public String toString() {
        return "SiteRequest{" +
                "requestId=" + requestId +
                ", sid=" + sid +
                ", uid=" + uid +
                ", newLength=" + newLength +
                ", newWidth=" + newWidth +
                ", newSiteType='" + newSiteType + '\'' +
                ", status='" + status + '\'' +
                ", requestDate=" + requestDate +
                '}';
    }
}
