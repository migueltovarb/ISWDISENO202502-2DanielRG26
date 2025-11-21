package ejercicio2;

public class MainTest {
    public static void main(String[] args) {
        
        Shape shape1 = new Shape("blue", false);
        System.out.println(shape1.toString());
        
        
        Circle circle1 = new Circle();
        System.out.println(circle1.toString());
        System.out.println("Área del círculo: " + circle1.getArea());
        System.out.println("Perímetro del círculo: " + circle1.getPerimeter());
        
        Circle circle2 = new Circle(5.0, "green", true);
        System.out.println(circle2.toString());
        System.out.println("Área del círculo 2: " + circle2.getArea());
        System.out.println("Perímetro del círculo 2: " + circle2.getPerimeter());
        
        
        Rectangle rect1 = new Rectangle();
        System.out.println(rect1.toString());
        System.out.println("Área del rectángulo: " + rect1.getArea());
        System.out.println("Perímetro del rectángulo: " + rect1.getPerimeter());
        
        Rectangle rect2 = new Rectangle(4.0, 6.0, "yellow", false);
        System.out.println(rect2.toString());
        System.out.println("Área del rectángulo 2: " + rect2.getArea());
        System.out.println("Perímetro del rectángulo 2: " + rect2.getPerimeter());
        
        
        Square square1 = new Square();
        System.out.println(square1.toString());
        System.out.println("Área del cuadrado: " + square1.getArea());
        System.out.println("Perímetro del cuadrado: " + square1.getPerimeter());
        
        Square square2 = new Square(5.0, "purple", true);
        System.out.println(square2.toString());
        System.out.println("Lado del cuadrado: " + square2.getSide());
        System.out.println("Área del cuadrado 2: " + square2.getArea());
        System.out.println("Perímetro del cuadrado 2: " + square2.getPerimeter());
        
        
        square2.setSide(10.0);
        System.out.println("\nDespués de cambiar el lado a 10.0:");
        System.out.println("Width: " + square2.getWidth());
        System.out.println("Length: " + square2.getLength());
        System.out.println("Área: " + square2.getArea());
    }
}