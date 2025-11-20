package com.guevara.vehiculos.service;

import com.guevara.vehiculos.model.Vehiculo;
import com.guevara.vehiculos.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@org.springframework.context.annotation.Profile("!offline")
public class VehiculoService {
  private final VehiculoRepository repository;

  public VehiculoService(VehiculoRepository repository) {
    this.repository = repository;
  }

  public Vehiculo crear(Vehiculo v) {
    return repository.save(v);
  }

  public List<Vehiculo> listar() {
    return repository.findAll();
  }

  public Optional<Vehiculo> leer(String id) {
    return repository.findById(id);
  }

  public Vehiculo actualizar(String id, Vehiculo v) {
    v.setId(id);
    return repository.save(v);
  }

  public boolean eliminar(String id) {
    if (repository.existsById(id)) {
      repository.deleteById(id);
      return true;
    }
    return false;
  }
}