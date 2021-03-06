package by.tourist.controller;

import by.tourist.entity.City;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import by.tourist.services.implementation.CityServiceImpl;

import java.util.List;

@Api
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityServiceImpl cityService;

    @PostMapping
    public City create(@RequestBody City city) {
        return cityService.create(city);
    }

    @GetMapping("/find-all")
    public List<City> findAll(@RequestParam int page, @RequestParam int size) {
        return cityService.findAll(page, size);
    }

    @GetMapping("/find-by-name/{name}")
    public City findByName(@PathVariable String name) {
        return cityService.findByName(name);
    }

    @GetMapping("/size")
    public int getSize() {
        return cityService.getSize();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        cityService.deleteById(id);

    }
}
