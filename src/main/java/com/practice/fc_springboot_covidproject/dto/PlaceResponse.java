package com.practice.fc_springboot_covidproject.dto;

import com.practice.fc_springboot_covidproject.constant.PlaceType;

public record PlaceResponse(
        PlaceType placeType,
        String placeName,
        String address,
        String phoneNumber,
        Integer capacity,
        String memo
) {
    public static PlaceResponse of(
            PlaceType placeType,
            String placeName,
            String address,
            String phoneNumber,
            Integer capacity,
            String memo
    ) {
        return new PlaceResponse(
                placeType, placeName, address, phoneNumber, capacity, memo
        );
    }
}
