package ejercicio3;

import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            System.out.println("\n========================================");
            System.out.println("       SISTEMA DE ANIMALES");
            System.out.println("========================================");
            System.out.println("1. Crear un Animal");
            System.out.println("2. Crear un Mamífero");
            System.out.println("3. Crear un Gato");
            System.out.println("4. Crear un Perro");
            System.out.println("5. Hacer que un Gato salude");
            System.out.println("6. Hacer que un Perro salude");
            System.out.println("7. Hacer que dos Perros se saluden");
            System.out.println("0. Salir");
            System.out.println("========================================");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch (opcion) {
                case 1:
                    crearAnimal(scanner);
                    break;
                case 2:
                    crearMamifero(scanner);
                    break;
                case 3:
                    crearGato(scanner);
                    break;
                case 4:
                    crearPerro(scanner);
                    break;
                case 5:
                    gatoSaluda(scanner);
                    break;
                case 6:
                    perroSaluda(scanner);
                    break;
                case 7:
                    perrosSaludan(scanner);
                    break;
                case 0:
                    System.out.println("\n¡Hasta pronto!");
                    break;
                default:
                    System.out.println("\n❌ Opción inválida. Intente nuevamente.");
            }
            
        } while (opcion != 0);
        
        scanner.close();
    }
    
    // Método para crear un Animal
    private static void crearAnimal(Scanner scanner) {
        System.out.print("\nIngrese el nombre del animal: ");
        String nombre = scanner.nextLine();
        
        Animal animal = new Animal(nombre);
        System.out.println("\n✓ Animal creado:");
        System.out.println(animal.toString());
    }
    
    // Método para crear un Mamífero
    private static void crearMamifero(Scanner scanner) {
        System.out.print("\nIngrese el nombre del mamífero: ");
        String nombre = scanner.nextLine();
        
        Mammal mamifero = new Mammal(nombre);
        System.out.println("\n✓ Mamífero creado:");
        System.out.println(mamifero.toString());
    }
    
    // Método para crear un Gato
    private static void crearGato(Scanner scanner) {
        System.out.print("\nIngrese el nombre del gato: ");
        String nombre = scanner.nextLine();
        
        Cat gato = new Cat(nombre);
        System.out.println("\n✓ Gato creado:");
        System.out.println(gato.toString());
    }
    
    // Método para crear un Perro
    private static void crearPerro(Scanner scanner) {
        System.out.print("\nIngrese el nombre del perro: ");
        String nombre = scanner.nextLine();
        
        Dog perro = new Dog(nombre);
        System.out.println("\n✓ Perro creado:");
        System.out.println(perro.toString());
    }
    
    // Método para que un Gato salude
    private static void gatoSaluda(Scanner scanner) {
        System.out.print("\nIngrese el nombre del gato: ");
        String nombre = scanner.nextLine();
        
        Cat gato = new Cat(nombre);
        System.out.println("\n" + gato.toString());
        System.out.print(nombre + " dice: ");
        gato.greets();
    }
    
    // Método para que un Perro salude
    private static void perroSaluda(Scanner scanner) {
        System.out.print("\nIngrese el nombre del perro: ");
        String nombre = scanner.nextLine();
        
        Dog perro = new Dog(nombre);
        System.out.println("\n" + perro.toString());
        System.out.print(nombre + " dice: ");
        perro.greets();
    }
    
    // Método para que dos Perros se saluden
    private static void perrosSaludan(Scanner scanner) {
        System.out.print("\nIngrese el nombre del primer perro: ");
        String nombre1 = scanner.nextLine();
        
        System.out.print("Ingrese el nombre del segundo perro: ");
        String nombre2 = scanner.nextLine();
        
        Dog perro1 = new Dog(nombre1);
        Dog perro2 = new Dog(nombre2);
        
        System.out.println("\n" + perro1.toString());
        System.out.println(perro2.toString());
        
        System.out.print("\n" + nombre1 + " saluda a " + nombre2 + ": ");
        perro1.greets(perro2);
        
        System.out.print(nombre2 + " saluda a " + nombre1 + ": ");
        perro2.greets(perro1);
    }
}