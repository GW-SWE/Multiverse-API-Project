package swe.gw.apiproject.repository;

import swe.gw.apiproject.model.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface CarsRepository extends JpaRepository<Cars, Long> {

    Optional<Cars> findByMake(String make);

    Optional<Cars> findByColour(String colour);
}
