package by.neon.tour.service;

import by.neon.tour.model.*;

import java.util.List;

public interface TourService {
    /**
     * Returns the list of city by given country name
     * @param countryFrom the name of country
     * @return List
     */
    List<CityFrom> getCityList(String countryFrom);

    /**
     * Returns the list of countries for tour order
     * @return List
     */
    List<CountryFrom> getCountryFrom();

    /**
     *
     * @return
     */
    List<CountryTo> getCountryTo();

    /**
     *
     * @param locationName
     * @return
     */
    List<Hotel> getHotelsByLocation(String locationName);

    /**
     *
     * @param category
     * @return
     */
    List<Hotel> getHotelsByCategory(String category);

    /**
     *
     * @param category
     * @param location
     * @return
     */
    List<Hotel> getHotelsByCategoryAndLocation(String category, String location);

    /**
     *
     * @return
     */
    List<HotelCategory> getCategories();

    /**
     *
     * @param countryTo
     * @return
     */
    List<Location> getLocations(String countryTo);
}
