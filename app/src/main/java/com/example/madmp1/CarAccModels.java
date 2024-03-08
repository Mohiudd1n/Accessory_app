package com.example.madmp1;

public class CarAccModels{
    String car_desc;
    String car_rate;
    String car_price;
    String car_oprice;
    int car_image;

    public CarAccModels(String car_desc, String car_rate, String car_price, String car_oprice, int car_image) {
        this.car_desc = car_desc;
        this.car_rate = car_rate;
        this.car_price = car_price;
        this.car_oprice = car_oprice;
        this.car_image = car_image;
    }

    public String getCar_desc() {
        return car_desc;
    }

    public String getCar_rate() {
        return car_rate;
    }

    public String getCar_price() {
        return car_price;
    }

    public String getCar_oprice() {
        return car_oprice;
    }

    public int getCar_image() {
        return car_image;
    }
}
