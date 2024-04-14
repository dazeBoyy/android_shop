package com.example.homefoodapp.Domain;

import java.io.Serializable;

public class Foods implements Serializable {

    private String Category;
    private String Description;
    private boolean BestFood;
    private int Id;
    private String Title;
    private String ImagePath;
    private int LocationId;
    private double Price;
    private int PriceId;
    private double Star;
    private int TimeId;
    private int TimeValue;
    private int numberInCart;

    private String Person;

    public Foods() {
    }

    public String getPerson() {
        return Person;
    }

    public void setPerson(String person) {
        Person = person;
    }

    public Foods(String category, String person, String description, boolean bestFood, int id, String title, String imagePath, int locationId, double price, int priceId, double star, int timeId, int timeValue, int numberInCart) {
        Category = category;
        Description = description;
        BestFood = bestFood;
        Person = person;
        Id = id;
        Title = title;
        ImagePath = imagePath;
        LocationId = locationId;
        Price = price;
        PriceId = priceId;
        Star = star;
        TimeId = timeId;
        TimeValue = timeValue;
        this.numberInCart = numberInCart;
    }

    @Override
    public String toString() {
        return Title;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String categoryId) {
        Category = categoryId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public boolean isBestFood() {
        return BestFood;
    }

    public void setBestFood(boolean bestFood) {
        BestFood = bestFood;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public int getLocationId() {
        return LocationId;
    }

    public void setLocationId(int locationId) {
        LocationId = locationId;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getPriceId() {
        return PriceId;
    }

    public void setPriceId(int priceId) {
        PriceId = priceId;
    }

    public double getStar() {
        return Star;
    }


    public void setStar(double star) {
        Star = star;
    }

    public int getTimeId() {
        return TimeId;
    }

    public void setTimeId(int timeId) {
        TimeId = timeId;
    }


    public int getTimeValue() {
        return TimeValue;
    }

    public void setTimeValue(int timeValue) {
        TimeValue = timeValue;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
