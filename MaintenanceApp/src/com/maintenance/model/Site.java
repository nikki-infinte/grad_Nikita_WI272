package com.maintenance.model;

public class Site {
    private int sid;
    private int uid;
    private int length;
    private int width;
    private String siteType;
    private boolean isOccupied;

    public Site() {}

    public Site(int sid, int uid, int length, int width, String siteType, boolean isOccupied) {
        this.sid = sid;
        this.uid = uid;
        this.length = length;
        this.width = width;
        this.siteType = siteType;
        this.isOccupied = isOccupied;
    }

    public Site(int uid, int length, int width, String siteType, boolean isOccupied) {
        this.uid = uid;
        this.length = length;
        this.width = width;
        this.siteType = siteType;
        this.isOccupied = isOccupied;
    }

    public int getSid() { return sid; }
    public void setSid(int sid) { this.sid = sid; }

    public int getUid() { return uid; }
    public void setUid(int uid) { this.uid = uid; }

    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }

    public String getSiteType() { return siteType; }
    public void setSiteType(String siteType) { this.siteType = siteType; }

    public boolean isOccupied() { return isOccupied; }
    public void setOccupied(boolean occupied) { isOccupied = occupied; }

    @Override
    public String toString() {
        return "Site{" +
                "sid=" + sid +
                ", uid=" + uid +
                ", length=" + length +
                ", width=" + width +
                ", siteType='" + siteType + '\'' +
                ", isOccupied=" + isOccupied +
                '}';
    }
}
