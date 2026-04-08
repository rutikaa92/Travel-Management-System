package com.travel.model;

public class Booking {
    private int id;
    private int userId;
    private int packageId;
    private String bookingDate;
    private String status;
    private double totalPrice;
    // For display joins
    private String userName;
    private String packageName;

    public Booking() {}

    public int getId()             { return id; }
    public int getUserId()         { return userId; }
    public int getPackageId()      { return packageId; }
    public String getBookingDate() { return bookingDate; }
    public String getStatus()      { return status; }
    public double getTotalPrice()  { return totalPrice; }
    public String getUserName()    { return userName; }
    public String getPackageName() { return packageName; }

    public void setId(int id)                   { this.id = id; }
    public void setUserId(int uid)              { this.userId = uid; }
    public void setPackageId(int pid)           { this.packageId = pid; }
    public void setBookingDate(String d)        { this.bookingDate = d; }
    public void setStatus(String s)             { this.status = s; }
    public void setTotalPrice(double p)         { this.totalPrice = p; }
    public void setUserName(String n)           { this.userName = n; }
    public void setPackageName(String n)        { this.packageName = n; }
}