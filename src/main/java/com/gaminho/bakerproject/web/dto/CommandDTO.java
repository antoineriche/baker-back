package com.gaminho.bakerproject.web.dto;

import java.time.LocalDate;

public class CommandDTO {
    private long id;
    private String date;
    private long count;
    private long restaurantId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "CommandDTO{" +
                "id=" + id +
                ", date=" + date +
                ", count=" + count +
                ", restaurantId=" + restaurantId +
                '}';
    }
}
