package by.tourist.services.implementation;

import by.tourist.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import by.tourist.repository.CityRepo;
import by.tourist.services.interfaces.CityService;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepo cityRepo;

    @Override
    public City create(City city) {
        return cityRepo.save(city);
    }

    @Override
    public List<City> findAll(int page, int size) {
        return cityRepo.findAll(PageRequest.of(page, size)).getContent();
    }

    @Override
    public List<City> findByName(String name, int page, int size) {
        return cityRepo.findCitiesByName(name, PageRequest.of(page, size));
    }

    @Override
    public int getSize() {
        return (int) cityRepo.count();
    }

    @Override
    public void deleteById(int id) {
        cityRepo.deleteById(id);

    }
}
