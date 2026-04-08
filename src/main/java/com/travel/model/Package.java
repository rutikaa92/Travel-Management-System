package com.travel.model;

public class Package {
    private int id;
    private String name;
    private String description;
    private String destination;
    private int durationDays;
    private double price;
    private int availableSlots;

    public Package() {}

    public int getId()               { return id; }
    public String getName()          { return name; }
    public String getDescription()   { return description; }
    public String getDestination()   { return destination; }
    public int getDurationDays()     { return durationDays; }
    public double getPrice()         { return price; }
    public int getAvailableSlots()   { return availableSlots; }

    public void setId(int id)                        { this.id = id; }
    public void setName(String name)                 { this.name = name; }
    public void setDescription(String desc)          { this.description = desc; }
    public void setDestination(String dest)          { this.destination = dest; }
    public void setDurationDays(int days)            { this.durationDays = days; }
    public void setPrice(double price)               { this.price = price; }
    public void setAvailableSlots(int slots)         { this.availableSlots = slots; }
}