package by.neon.tour.controller;

import by.neon.tour.model.*;
import by.neon.tour.service.TourService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/tour", produces = MediaType.APPLICATION_JSON_VALUE)
public class TourController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TourService tourService;

    @RequestMapping(value = {"/from/location"}, method = RequestMethod.GET)
    public List<CityFrom> getCityList(@RequestParam("country") String countryFrom) {
        return tourService.getCityList(countryFrom);
    }

    @RequestMapping(value = {"/from/country"}, method = RequestMethod.GET)
    public List<CountryFrom> getCountryFromList() {
        return tourService.getCountryFrom();
    }

    @RequestMapping(value = {"/to/country"}, method = RequestMethod.GET)
    public List<CountryTo> getCountryToList() {
        return tourService.getCountryTo();
    }

    @RequestMapping(value = {"/to/location"}, method = RequestMethod.GET)
    public List<Location> getLocations(@RequestParam("country") String countryTo) {
        return tourService.getLocations(countryTo);
    }

    @RequestMapping(value = {"/hotel/category"}, method = RequestMethod.GET)
    public List<HotelCategory> getHotelCategories() {
        return tourService.getCategories();
    }

    @RequestMapping(value = {"/hotel/get"}, method = RequestMethod.GET)
    public List<Hotel> getHotels(@RequestParam(name = "location", required = false) String location,
                                 @RequestParam(name = "category", required = false) String category) {
        if (location != null && category != null) {
            logger.debug("Getting hotels by both location and hotel category");
            return tourService.getHotelsByCategoryAndLocation(category, location);
        } else {
            if (location != null) {
                logger.debug("Getting hotel list by location");
                return tourService.getHotelsByLocation(location);
            } else if (category != null) {
                logger.debug("Getting hotel list by category");
                return tourService.getHotelsByCategory(category);
            } else {
                logger.info("No hotel found!");
                return null;
            }
        }
    }
}
