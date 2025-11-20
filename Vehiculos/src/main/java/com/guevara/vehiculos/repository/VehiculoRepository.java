package com.guevara.vehiculos.repository;

import com.guevara.vehiculos.model.Vehiculo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehiculoRepository extends MongoRepository<Vehiculo, String> {
}