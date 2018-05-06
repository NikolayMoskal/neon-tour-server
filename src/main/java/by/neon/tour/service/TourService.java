package by.neon.tour.service;

import by.neon.tour.model.*;

import java.sql.Date;
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

    /**
     * @return
     */
    List<Currency> getCurrencies();

    /**
     * @param start
     * @param end
     * @return
     */
    List<Tour> getByDate(Date start, Date end);

    /**
     * @param from
     * @param to
     * @return
     */
    List<Tour> getByPrice(Integer from, Integer to);

    /**
     * @param name
     * @return
     */
    Tour getByName(String name);

    /**
     * @param hotelName
     * @return
     */
    List<Tour> getByHotel(String hotelName);
}
