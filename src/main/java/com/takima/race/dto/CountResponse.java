package com.takima.race.dto;

public class CountResponse {

    private long count;

    public CountResponse(long count) {
        this.count = count;
    }

    public long getCount() {
        return count;
    }
}