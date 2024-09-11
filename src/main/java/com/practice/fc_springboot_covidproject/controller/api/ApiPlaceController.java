package com.practice.fc_springboot_covidproject.controller.api;

import com.practice.fc_springboot_covidproject.constant.PlaceType;
import com.practice.fc_springboot_covidproject.dto.APIDataResponse;
import com.practice.fc_springboot_covidproject.dto.PlaceRequest;
import com.practice.fc_springboot_covidproject.dto.PlaceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RequestMapping("/api")
//@RestController
/**
 * Spring Data REST 로 API 를 만들어서 당장 필요가 없어진 컨트롤러.
 * 우선 deprecated 하고, 향후 사용 방안을 고민해 본다.
 * 필요에 따라서는 다시 살릴 수도 있음
 *
 * @deprecated 0.1.2
 */
@Deprecated
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
