public class Employee1 {
    // Atributos
    private int id;
    private String firstName;
    private String lastName;
    private int salary;

    // Constructor
    public Employee1(int id, String firstName, String lastName, int salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    // Métodos Getter
    public int getID() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public int getSalary() {
        return salary;
    }

    // Método Setter
    public void setSalary(int salary) {
        this.salary = salary;
    }

    // Método para obtener el salario anual
    public int getAnnualSalary() {
        return salary * 12;
    }

    // Método para aumentar el salario por un porcentaje
    public int raiseSalary(int percent) {
        this.salary += salary * percent / 100;
        return salary;
    }

    // Método toString
    @Override
    public String toString() {
        return "Employee[id=" + id + ",name=" + firstName + " " + lastName + ",salary=" + salary + "]";
    }

    // Método principal (main) para probar la clase
    public static void main(String[] args) {
        Employee1 emp = new Employee1(1, "John", "Doe", 5000);

        // Mostrar información del empleado
        System.out.println(emp.toString());

        // Aumentar el salario en un 10%
        emp.raiseSalary(10);
        System.out.println("New salary: " + emp.getSalary());

        // Mostrar salario anual
        System.out.println("Annual Salary: " + emp.getAnnualSalary());
    }
}
