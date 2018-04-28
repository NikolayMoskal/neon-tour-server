package by.neon.tour.service.impl;

import by.neon.tour.model.*;
import by.neon.tour.repository.*;
import by.neon.tour.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tourService")
public class TourServiceImpl implements TourService {
    @Autowired private CountryFromRepository countryFromRepository;
    @Autowired private CityFromRepository cityFromRepository;
    @Autowired private CountryToRepository countryToRepository;
    @Autowired private HotelRepository hotelRepository;
    @Autowired private LocationRepository locationRepository;
    @Autowired private CategoryRepository categoryRepository;

    @Override
    public List<CityFrom> getCityList(String countryFrom) {
        return cityFromRepository.findByCountry(countryFrom);
    }

    @Override
    public List<CountryFrom> getCountryFrom() {
        return countryFromRepository.findAll();
    }

    @Override
    public List<CountryTo> getCountryTo() {
        return countryToRepository.findAll();
    }

    @Override
    public List<Hotel> getHotelsByLocation(String locationName) {
        return hotelRepository.getHotelsByLocation(locationName);
    }

    @Override
    public List<Hotel> getHotelsByCategory(String category) {
        return hotelRepository.getHotelsByCategory(category);
    }

    /**
     * @param category
     * @param location
     * @return
     */
    @Override
    public List<Hotel> getHotelsByCategoryAndLocation(String category, String location) {
        return hotelRepository.getHotelsByCategoryAndLocation(category, location);
    }

    @Override
    public List<HotelCategory> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Location> getLocations(String countryTo) {
        return locationRepository.getLocationByCountry(countryTo);
    }
}
