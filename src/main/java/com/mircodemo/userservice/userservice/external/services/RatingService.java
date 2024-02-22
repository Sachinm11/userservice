package com.mircodemo.userservice.userservice.external.services;

import com.mircodemo.userservice.userservice.Entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("ratings/users/{userId}")
    List<Rating> getRatingsByUserId(@PathVariable String userId);

    @GetMapping("ratings/hotels/{hotelId}")
    List<Rating> getRatingsByHotelId(@PathVariable String hotelId);

    @PostMapping("ratings")
    Rating createRating(@RequestBody Rating rating);
}
