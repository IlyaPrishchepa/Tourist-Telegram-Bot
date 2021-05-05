package by.tourist.services.interfaces;

import by.tourist.entity.City;

import java.util.List;

public interface CityService {

    City create(City city);
    List<City> findAll(int page, int size);
    City findByName(String name);
    int getSize();
    void deleteById (int id);

}
