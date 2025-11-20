package com.guevara.vehiculos.service;

import com.guevara.vehiculos.model.Vehiculo;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Profile("offline")
public class VehiculoServiceOffline extends VehiculoService {
  private final Map<String, Vehiculo> store = new LinkedHashMap<>();

  public VehiculoServiceOffline() {
    super(null);
  }

  @Override
  public Vehiculo crear(Vehiculo v) {
    String id = UUID.randomUUID().toString();
    v.setId(id);
    store.put(id, v);
    return v;
  }

  @Override
  public List<Vehiculo> listar() {
    return new ArrayList<>(store.values());
  }

  @Override
  public Optional<Vehiculo> leer(String id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public Vehiculo actualizar(String id, Vehiculo v) {
    v.setId(id);
    store.put(id, v);
    return v;
  }

  @Override
  public boolean eliminar(String id) {
    return store.remove(id) != null;
  }
}