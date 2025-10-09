package reservas;

import java.util.*;
import java.text.SimpleDateFormat;


public class Reservas {
    

    static class Estudiante {
        private String nombre;
        private String codigoInstitucional;
        private String programaAcademico;

        public Estudiante(String nombre, String codigoInstitucional, String programaAcademico) {
            this.nombre = nombre;
            this.codigoInstitucional = codigoInstitucional;
            this.programaAcademico = programaAcademico;
        }

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public String getCodigoInstitucional() { return codigoInstitucional; }
        public void setCodigoInstitucional(String codigoInstitucional) { this.codigoInstitucional = codigoInstitucional; }

        public String getProgramaAcademico() { return programaAcademico; }
        public void setProgramaAcademico(String programaAcademico) { this.programaAcademico = programaAcademico; }

        @Override
        public String toString() {
            return "Estudiante: " + nombre + " | Código Institucional: " + codigoInstitucional + " | Programa: " + programaAcademico;
        }
    }


    static class Reserva {
        private Estudiante estudiante;
        private Date fechaReserva;
        private String sala;

        public Reserva(Estudiante estudiante, Date fechaReserva, String sala) {
            this.estudiante = estudiante;
            this.fechaReserva = fechaReserva;
            this.sala = sala;
        }

        public Estudiante getEstudiante() { return estudiante; }
        public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }

        public Date getFechaReserva() { return fechaReserva; }
        public void setFechaReserva(Date fechaReserva) { this.fechaReserva = fechaReserva; }

        public String getSala() { return sala; }
        public void setSala(String sala) { this.sala = sala; }

        @Override
        public String toString() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return "Reserva - Estudiante: " + estudiante.getNombre() + " | Fecha: " + sdf.format(fechaReserva) + " | Sala: " + sala;
        }
    }


    static class SistemaReservas {
        private List<Reserva> reservas;

        public SistemaReservas() {
            reservas = new ArrayList<>();
        }

        public void agregarReserva(Reserva reserva) {
            reservas.add(reserva);
        }

        public void mostrarReservas() {
            if (reservas.isEmpty()) {
                System.out.println("No hay reservas registradas.");
            } else {
                for (Reserva reserva : reservas) {
                    System.out.println(reserva);
                }
            }
        }
    }

    public static void main(String[] args) {

        Estudiante estudiante1 = new Estudiante("Carlos Pérez", "12345", "Ingeniería de Sistemas");
        Estudiante estudiante2 = new Estudiante("María Gómez", "67890", "Arquitectura");


        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.OCTOBER, 13, 10, 30);
        Date fechaReserva1 = calendar.getTime();

        calendar.set(2025, Calendar.OCTOBER, 14, 14, 00);
        Date fechaReserva2 = calendar.getTime();


        String sala1 = "Sala A";
        String sala2 = "Sala B";


        SistemaReservas sistema = new SistemaReservas();


        Reserva reserva1 = new Reserva(estudiante1, fechaReserva1, sala1);
        Reserva reserva2 = new Reserva(estudiante2, fechaReserva2, sala2);


        sistema.agregarReserva(reserva1);
        sistema.agregarReserva(reserva2);


        sistema.mostrarReservas();
    }
}
