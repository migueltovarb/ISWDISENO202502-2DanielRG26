import java.util.Scanner;

public class InventarioSupermercado {
    private static final int MAX_PRODUCTOS = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] productos = new String[MAX_PRODUCTOS];
    int[] cantidades = new int[MAX_PRODUCTOS];

        System.out.println("Ingreso de productos al inventario");
        for (int i = 0; i < MAX_PRODUCTOS; i++) {
            System.out.print("Ingrese el nombre del producto " + (i + 1) + ": ");
            productos[i] = scanner.nextLine();

            int cantidad;
            do {
                System.out.print("Ingrese la cantidad disponible de " + productos[i] + ": ");
                cantidad = scanner.nextInt();
                if (cantidad < 0) {
                    System.out.println("La cantidad no puede ser negativa. Intente nuevamente.");
                }
            } while (cantidad < 0);

            cantidades[i] = cantidad;
            scanner.nextLine(); 
        }

        int opcion;
        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Mostrar todos los productos y sus existencias");
            System.out.println("2. Buscar un producto por nombre y ver su cantidad");
            System.out.println("3. Actualizar inventario");
            System.out.println("4. Generar alerta de productos con cantidad menor a 10");
            System.out.println("5. Salir");
            System.out.println("6. Eliminar producto y reemplazarlo");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  

            switch (opcion) {
                case 1:
                    mostrarProductos(productos, cantidades);
                    break;
                case 2:
                    buscarProducto(productos, cantidades, scanner);
                    break;
                case 3:
                    actualizarInventario(cantidades, scanner);
                    break;
                case 4:
                    generarAlerta(cantidades);
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                case 6:
                    eliminarYReemplazarProducto(productos, cantidades, scanner);
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
            }
        } while (opcion != 5);

        scanner.close();
    }
    
    

    private static void eliminarYReemplazarProducto(String[] productos, int[] cantidades, Scanner scanner) {
        System.out.print("Ingrese el número del producto a eliminar y reemplazar (1 a " + MAX_PRODUCTOS + "): ");
        int numeroProducto = scanner.nextInt() - 1;
        scanner.nextLine();
        if (numeroProducto < 0 || numeroProducto >= MAX_PRODUCTOS) {
            System.out.println("Número de producto no válido.");
            return;
        }
        System.out.println("El producto '" + productos[numeroProducto] + "' será eliminado y reemplazado.");

        

        System.out.print("Ingrese el nombre del nuevo producto: ");
        String nuevoNombre = scanner.nextLine();
        int nuevaCantidad;
        do {
            System.out.print("Ingrese la cantidad disponible de " + nuevoNombre + ": ");
            nuevaCantidad = scanner.nextInt();
            if (nuevaCantidad < 0) {
                System.out.println("La cantidad no puede ser negativa. Intente nuevamente.");
            }
        } while (nuevaCantidad < 0);
        scanner.nextLine();
        productos[numeroProducto] = nuevoNombre;
        cantidades[numeroProducto] = nuevaCantidad;
        System.out.println("Producto reemplazado exitosamente.");
    }

    private static void mostrarProductos(String[] productos, int[] cantidades) {
        System.out.println("\n--- Productos y sus existencias ---");
        int total = 0;
        for (int i = 0; i < MAX_PRODUCTOS; i++) {
            System.out.println(productos[i] + ": " + cantidades[i] + " unidades");
            total += cantidades[i];
        }
        System.out.println("Total unidades en inventario: " + total);
    }

    private static void buscarProducto(String[] productos, int[] cantidades, Scanner scanner) {
        System.out.print("Ingrese el nombre del producto a buscar: ");
        String nombreProducto = scanner.nextLine();
        boolean encontrado = false;

        for (int i = 0; i < MAX_PRODUCTOS; i++) {
            if (productos[i].equalsIgnoreCase(nombreProducto)) {
                System.out.println("El producto " + productos[i] + " tiene " + cantidades[i] + " unidades disponibles.");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void actualizarInventario(int[] cantidades, Scanner scanner) {
        System.out.print("Ingrese el número del producto a actualizar (1 a 5): ");
        int numeroProducto = scanner.nextInt() - 1;

        if (numeroProducto < 0 || numeroProducto >= MAX_PRODUCTOS) {
            System.out.println("Número de producto no válido.");
            return;
        }

        System.out.print("Ingrese la cantidad a agregar o restar (puede ser negativo): ");
        int cambioCantidad = scanner.nextInt();

        if (cantidades[numeroProducto] + cambioCantidad < 0) {
            System.out.println("No puede haber cantidades negativas de un producto.");
        } else {
            cantidades[numeroProducto] += cambioCantidad;
            System.out.println("La cantidad del producto " + (numeroProducto + 1) + " ha sido actualizada.");
        }
    }

    private static void generarAlerta(int[] cantidades) {
        System.out.println("\n--- Alerta de productos con cantidad menor a 10 ---");
        for (int i = 0; i < MAX_PRODUCTOS; i++) {
            if (cantidades[i] < 10) {
                System.out.println("El producto " + (i + 1) + " tiene " + cantidades[i] + " unidades. ¡ALERTA!");
            }
        }
    }
}
