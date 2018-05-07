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

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/tour", produces = MediaType.APPLICATION_JSON_VALUE)
public class TourController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TourService tourService;

    @RequestMapping(value = {"/from/location"}, method = RequestMethod.GET)
    public List<CityFrom> getCityList(@RequestParam(name = "country", required = false) String countryFrom) {
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

    @RequestMapping(value = "/currency/all", method = RequestMethod.GET)
    public List<Currency> getCurrencies() {
        return tourService.getCurrencies();
    }

    @RequestMapping(value = "/get/price", method = RequestMethod.GET)
    public List<Tour> getTours(@RequestParam(name = "from", required = false) Integer priceFrom,
                               @RequestParam(name = "to", required = false) Integer priceTo) {
        return tourService.getByPrice(priceFrom, priceTo);
    }

    @RequestMapping(value = "/get/date", method = RequestMethod.GET)
    public List<Tour> getTours(@RequestParam(name = "from", required = false) Long dateFrom,
                               @RequestParam(name = "to", required = false) Long dateTo) {
        return tourService.getByDate(dateFrom != null ? new Date(dateFrom) : null,
                dateTo != null ? new Date(dateTo) : null);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Tour> getTours(@RequestParam(name = "hotel") String hotelName) {
        return tourService.getByHotel(hotelName);
    }
}
