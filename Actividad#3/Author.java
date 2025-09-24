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

    // Método Setter
    public void setEmail(String email) {
        this.email = email;
    }

    // Método toString
    @Override
    public String toString() {
        return "Author[name=" + name + ",email=" + email + ",gender=" + gender + "]";
    }

    // Método principal (main) para probar la clase
    public static void main(String[] args) {
        // Crear un autor
        Author author = new Author("John Doe", "johndoe@example.com", 'm');

        // Mostrar la información del autor
        System.out.println(author.toString());

        // Cambiar el correo electrónico
        author.setEmail("newemail@example.com");
        System.out.println("Updated " + author.toString());
    }
}
