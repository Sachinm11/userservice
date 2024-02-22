package com.mircodemo.userservice.userservice.Impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.mircodemo.userservice.userservice.Entities.Hotel;
import com.mircodemo.userservice.userservice.Entities.Rating;
import com.mircodemo.userservice.userservice.external.services.HotelService;
import com.mircodemo.userservice.userservice.external.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mircodemo.userservice.userservice.Entities.User;
import com.mircodemo.userservice.userservice.Exceptions.ResourceNotFoundException;
import com.mircodemo.userservice.userservice.Services.UserService;
import com.mircodemo.userservice.userservice.repositories.UserRepo;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user =  userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id: "+userId+" Not found"));

//        Rating[] ratingsForUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(),Rating[].class);
//
//        List<Rating> ratings = Arrays.stream(ratingsForUser).toList();
//        List<Rating> ratingList = ratings.stream().map(rating -> {
//            Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(),Hotel.class);
//            rating.setHotel(hotel);
//            return rating;
//        }).collect(Collectors.toList());
//
//        user.setRatings(ratingList);

        List<Rating> ratings = ratingService.getRatingsByUserId(user.getUserId());
        List<Rating> ratingList = ratings.stream().map(rating -> {
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;
    }
    
}
