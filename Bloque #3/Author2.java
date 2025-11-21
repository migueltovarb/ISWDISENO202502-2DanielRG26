public class Author {
    // Atributos
    private String name;
    private String email;
    private char gender;

    // Constructor
    public Author(String name, String email, char gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    // Métodos Getter
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public char getGender() {
        return gender;
    }

    // Método toString
    @Override
    public String toString() {
        return "Author[name=" + name + ",email=" + email + ",gender=" + gender + "]";
    }
}

public class Book {
    // Atributos
    private String name;
    private Author author;
    private double price;
    private int qty;

    // Constructor
    public Book(String name, Author author, double price) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.qty = 0;  // Valor inicial de cantidad
    }

    public Book(String name, Author author, double price, int qty) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.qty = qty;
    }

    // Métodos Getter
    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    // Métodos Setter
    public void setPrice(double price) {
        this.price = price;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    // Método toString
    @Override
    public String toString() {
        return "Book[name=" + name + ",Author[" + author.toString() + "],price=" + price + ",qty=" + qty + "]";
    }

    // Método principal (main) para probar la clase
    public static void main(String[] args) {
        // Crear un autor
        Author author = new Author("John Doe", "johndoe@example.com", 'm');

        // Crear un libro
        Book book = new Book("Java Programming", author, 29.99, 10);

        // Mostrar información del libro
        System.out.println(book.toString());
    }
}
