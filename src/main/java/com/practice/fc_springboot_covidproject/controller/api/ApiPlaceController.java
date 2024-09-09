package com.practice.fc_springboot_covidproject.controller.api;

import com.practice.fc_springboot_covidproject.constant.PlaceType;
import com.practice.fc_springboot_covidproject.domain.Place;
import com.practice.fc_springboot_covidproject.dto.APIDataResponse;
import com.practice.fc_springboot_covidproject.dto.PlaceDTO;
import com.practice.fc_springboot_covidproject.dto.PlaceRequest;
import com.practice.fc_springboot_covidproject.dto.PlaceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ApiPlaceController {

    @GetMapping("/places")
    public APIDataResponse<List<PlaceResponse>> getPlaces() {
        return APIDataResponse.of(List.of(PlaceResponse.of(
                PlaceType.COMMON,
                "랄라배드민턴장",
                "서울시 강남구 강남대로 1234",
                "010-1234-5678",
                30,
                "신장개업"
        )));
    }

//    @GetMapping("/places")
//    public APIDataResponse<List<PlaceDTO>> getPlaces() {
//        return APIDataResponse.of(List.of(PlaceDTO.of(
//                PlaceType.COMMON,
//                "할리배드민턴장",
//                "서울시 강남구 테헤란로 12번 34",
//                "010-1234-5678",
//                30,
//                "신장개업"
//        )));
//
//    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/places")
    public APIDataResponse<Void> createPlace(@RequestBody PlaceRequest placeRequest) {
        return APIDataResponse.empty();
    }

    @GetMapping("/places/{placeId}")
    public APIDataResponse<PlaceResponse> getPlace(@PathVariable Long placeId) {
        if (placeId.equals(2L)) {
            return APIDataResponse.empty();
        }

        return APIDataResponse.of(PlaceResponse.of(
                PlaceType.COMMON,
                "랄라배드민턴장",
                "서울시 강남구 강남대로 1234",
                "010-1234-5678",
                30,
                "신장개업"
        ));
    }

    @PutMapping("/places/{placeId}")
    public APIDataResponse<Void> modifyPlace(
            @PathVariable Long placeId,
            @RequestBody PlaceRequest placeRequest
    ) {
        return APIDataResponse.empty();
    }

    @DeleteMapping("/places/{placeId}")
    public APIDataResponse<Void> removePlace(@PathVariable Long placeId) {
        return APIDataResponse.empty();
    }

}
