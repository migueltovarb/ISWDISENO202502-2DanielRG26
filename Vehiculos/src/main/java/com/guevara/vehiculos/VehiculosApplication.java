package com.guevara.vehiculos;

import com.guevara.vehiculos.model.Vehiculo;
import com.guevara.vehiculos.service.VehiculoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class VehiculosApplication {
  public static void main(String[] args) {
    SpringApplication.run(VehiculosApplication.class, args);
  }

  @Bean
  @Profile("!test")
  CommandLineRunner runner(VehiculoService service) {
    return args -> {
      try {
        int count = service.listar().size();
        System.out.println("MongoDB OK, registros actuales: " + count);
      } catch (Exception e) {
        System.out.println("Error conectando a MongoDB: " + e.getMessage());
      }
      Scanner scanner = new Scanner(System.in);
      boolean running = true;
      while (running) {
        System.out.println("\n==== CRUD Vehiculos ====");
        System.out.println("1) Agregar");
        System.out.println("2) Listar");
        System.out.println("3) Leer por ID");
        System.out.println("4) Actualizar");
        System.out.println("5) Eliminar");
        System.out.println("6) Salir");
        System.out.print("Seleccione opcion: ");
        String opcion = scanner.nextLine();
        switch (opcion) {
          case "1":
            Vehiculo nuevo = new Vehiculo();
            System.out.print("Marca: ");
            nuevo.setMarca(scanner.nextLine());
            System.out.print("Modelo: ");
            nuevo.setModelo(scanner.nextLine());
            System.out.print("Anio: ");
            try { nuevo.setAnio(Integer.parseInt(scanner.nextLine())); } catch (Exception e) { nuevo.setAnio(0); }
            System.out.print("Color: ");
            nuevo.setColor(scanner.nextLine());
            System.out.print("Placa: ");
            nuevo.setPlaca(scanner.nextLine());
            Vehiculo guardado = service.crear(nuevo);
            System.out.println("Creado con ID: " + guardado.getId());
            break;
          case "2":
            List<Vehiculo> lista = service.listar();
            if (lista.isEmpty()) {
              System.out.println("Sin registros");
            } else {
              lista.forEach(v -> System.out.println(v.getId() + " | " + v.getMarca() + " " + v.getModelo() + " " + v.getAnio() + " | " + v.getColor() + " | " + v.getPlaca()));
            }
            break;
          case "3":
            System.out.print("ID: ");
            String idLeer = scanner.nextLine();
            Optional<Vehiculo> encontrado = service.leer(idLeer);
            if (encontrado.isPresent()) {
              Vehiculo v = encontrado.get();
              System.out.println(v.getId() + " | " + v.getMarca() + " " + v.getModelo() + " " + v.getAnio() + " | " + v.getColor() + " | " + v.getPlaca());
            } else {
              System.out.println("No encontrado");
            }
            break;
          case "4":
            System.out.print("ID a actualizar: ");
            String idAct = scanner.nextLine();
            Optional<Vehiculo> origOpt = service.leer(idAct);
            if (origOpt.isEmpty()) {
              System.out.println("No encontrado");
              break;
            }
            Vehiculo orig = origOpt.get();
            System.out.print("Marca (" + orig.getMarca() + "): ");
            String marca = scanner.nextLine();
            if (!marca.isBlank()) orig.setMarca(marca);
            System.out.print("Modelo (" + orig.getModelo() + "): ");
            String modelo = scanner.nextLine();
            if (!modelo.isBlank()) orig.setModelo(modelo);
            System.out.print("Anio (" + orig.getAnio() + "): ");
            String anioStr = scanner.nextLine();
            if (!anioStr.isBlank()) {
              try { orig.setAnio(Integer.parseInt(anioStr)); } catch (Exception ignored) {}
            }
            System.out.print("Color (" + orig.getColor() + "): ");
            String color = scanner.nextLine();
            if (!color.isBlank()) orig.setColor(color);
            System.out.print("Placa (" + orig.getPlaca() + "): ");
            String placa = scanner.nextLine();
            if (!placa.isBlank()) orig.setPlaca(placa);
            Vehiculo actualizado = service.actualizar(idAct, orig);
            System.out.println("Actualizado: " + actualizado.getId());
            break;
          case "5":
            System.out.print("ID a eliminar: ");
            String idDel = scanner.nextLine();
            boolean eliminado = service.eliminar(idDel);
            System.out.println(eliminado ? "Eliminado" : "No encontrado");
            break;
          case "6":
            running = false;
            break;
          default:
            System.out.println("Opcion invalida");
        }
      }
    };
  }
}