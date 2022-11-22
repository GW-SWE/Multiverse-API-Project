package swe.gw.apiproject.controller;

import org.apache.logging.log4j.util.Chars;
import swe.gw.apiproject.model.Cars;
import swe.gw.apiproject.service.CarsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.print.DocFlavor;
import java.util.*;

@RestController
@RequestMapping("/cars")
public class CarsController {

    @Autowired
    CarsService carsService;

    @PostMapping("/create")
    public Cars createCars(@RequestBody Cars data) { return carsService.createCars(data);}

    @GetMapping("/all")
    public List<Cars> readCars() { return carsService.getCars();}

    @PutMapping("/update/{id}")
    public Cars readCars(@PathVariable(value = "id") Long id, @RequestBody Cars input)
    {return carsService.updateCars(id, input);}

    @GetMapping("/findcar/{id}")
    public Optional<Cars> readCars(@PathVariable(value = "id") Long id) {return carsService.getCarsById(id);}

    @GetMapping("/findmake/{make}")
    public Optional<Cars> readCars(@PathVariable(value = "make") String make) {return carsService.getCarsByMake(make);}

    @GetMapping("/findbycolour/{colour}")
    public Optional<Cars> readCarsForColour(@PathVariable(value = "colour") String colour) {return carsService.getCarsByColour(colour);}
}
