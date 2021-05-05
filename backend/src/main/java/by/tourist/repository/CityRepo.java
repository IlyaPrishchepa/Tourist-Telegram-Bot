package by.tourist.repository;

import by.tourist.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepo extends JpaRepository<City,Integer> {

    City findCityByName(String name);

}
