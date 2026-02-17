package com.maintenance.model;

import java.sql.Date;

public class Maintenance {
    private int mid; // Generally implicit in logic, but good for completeness if table has PK
    private int sid;
    private int maintenanceYear;
    private double amount;
    private String status;
    private Date paymentDate;

    public Maintenance() {}

    public Maintenance(int sid, int maintenanceYear, double amount, String status, Date paymentDate) {
        this.sid = sid;
        this.maintenanceYear = maintenanceYear;
        this.amount = amount;
        this.status = status;
        this.paymentDate = paymentDate;
    }

    public int getSid() { return sid; }
    public void setSid(int sid) { this.sid = sid; }

    public int getMaintenanceYear() { return maintenanceYear; }
    public void setMaintenanceYear(int maintenanceYear) { this.maintenanceYear = maintenanceYear; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }

    @Override
    public String toString() {
        return "Maintenance{" +
                "sid=" + sid +
                ", maintenanceYear=" + maintenanceYear +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
