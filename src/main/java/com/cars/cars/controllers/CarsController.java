package com.cars.cars.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cars.cars.dto.CarDTO;
import com.cars.cars.model.Car;
import com.cars.cars.repository.CarRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cars")
public class CarsController {

    @Autowired
    private CarRepository repository;

    @PostMapping
    public void registerCar(@RequestBody @Valid CarDTO car) {
        System.out.println("Modelo: " + car.modelo());
        System.out.println("Fabricante: " + car.fabricante());
        System.out.println("Data de Fabricação: " + car.dataFabricacao());
        System.out.println("Valor: " + car.valor());
        System.out.println("Ano do modelo: " + car.anoModelo());

        repository.save(new Car(car));
    }

    @GetMapping
    public List<Car> getCars() {
        return repository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateCar(@PathVariable Long id, @RequestBody @Valid CarDTO req) {
        Optional<Car> car = repository.findById(id);

        if (car.isPresent()) {
            Car carValues = car.get();
            carValues.setModelo(req.modelo());
            carValues.setFabricante(req.fabricante());
            carValues.setDataFabricacao(req.dataFabricacao());
            carValues.setValor(req.valor());
            carValues.setAnoModelo(req.anoModelo());
            repository.save(carValues);
        }

    }
}
