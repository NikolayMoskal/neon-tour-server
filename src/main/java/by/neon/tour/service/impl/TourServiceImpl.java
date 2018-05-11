package by.neon.tour.service.impl;

import by.neon.tour.model.*;
import by.neon.tour.repository.*;
import by.neon.tour.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service("tourService")
public class TourServiceImpl implements TourService {
    @Autowired
    private CountryFromRepository countryFromRepository;
    @Autowired
    private CityFromRepository cityFromRepository;
    @Autowired
    private CountryToRepository countryToRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private TourRepository tourRepository;

    @Override
    public List<CityFrom> getCityList(String countryFrom) {
        if (countryFrom != null)
            return cityFromRepository.findByCountry(countryFrom);
        else
            return cityFromRepository.findAll();
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

    @Override
    public List<Currency> getCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    public List<Tour> getByDate(Date start, Date end) {
        if (start != null && end != null)
            return tourRepository.getBetweenDates(start, end);
        else {
            if (start != null)
                return tourRepository.getFromDate(start);
            else if (end != null)
                return tourRepository.getToDate(end);
        }
        return new ArrayList<>(0);
    }

    @Override
    public List<Tour> getByPrice(Integer from, Integer to) {
        if (from != null && to != null)
            return tourRepository.getBetweenPrices(from, to);
        else {
            if (from != null)
                return tourRepository.getFromPrice(from);
            else if (to != null)
                return tourRepository.getToPrice(to);
        }
        return new ArrayList<>(0);
    }

    @Override
    public Tour getByName(String name) {
        return tourRepository.findByName(name);
    }

    @Override
    public List<Tour> getByHotel(String hotelName) {
        return tourRepository.getByHotel(hotelName);
    }

    @Override
    public List<Tour> getAll() {
        return tourRepository.findAll();
    }
}
