package swe.gw.apiproject.service;

import swe.gw.apiproject.model.Cars;
import swe.gw.apiproject.repository.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarsService {

    @Autowired
    CarsRepository carsRepository;

    public Cars createCars(Cars info) {return carsRepository.save(info);}

    public List<Cars> getCars() { return carsRepository.findAll();}

    public Cars updateCars(Long id, Cars input) {
        Cars data = carsRepository.findById(id).get();
        if(!Objects.isNull(input.getMake())) {data.setMake(input.getMake());}
        if(!Objects.isNull(input.getColour())) {data.setColour(input.getColour());}
        if(!Objects.isNull(input.getVersion())) {data.setVersion(input.getVersion());}


        return carsRepository.save(data);
    }

    public Optional<Cars> getCarsById(Long id) { return carsRepository.findById(id);}

    public Optional<Cars> getCarsByMake(String make) { return carsRepository.findByMake(make);}

    public Optional<Cars> getCarsByColour(String colour) { return carsRepository.findByColour(colour);}


}
