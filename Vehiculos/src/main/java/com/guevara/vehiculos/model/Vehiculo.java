package com.guevara.vehiculos.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("vehiculos")
public class Vehiculo {
  @Id
  private String id;
  private String marca;
  private String modelo;
  private int anio;
  private String color;
  private String placa;

  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getMarca() { return marca; }
  public void setMarca(String marca) { this.marca = marca; }
  public String getModelo() { return modelo; }
  public void setModelo(String modelo) { this.modelo = modelo; }
  public int getAnio() { return anio; }
  public void setAnio(int anio) { this.anio = anio; }
  public String getColor() { return color; }
  public void setColor(String color) { this.color = color; }
  public String getPlaca() { return placa; }
  public void setPlaca(String placa) { this.placa = placa; }
}