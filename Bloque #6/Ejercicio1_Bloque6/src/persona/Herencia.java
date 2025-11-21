package persona;

// Clase padre (superclase)
public class Person {
    private String name;
    private String address;
    
    // Constructor
    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public String getAddress() {
        return address;
    }
    
    // Setters
    public void setAddress(String address) {
        this.address = address;
    }
    
    // Método toString
    @Override
    public String toString() {
        return "Person[name=" + name + ", address=" + address + "]";
    }
}


// Clase hija (subclase) - Student
class Student extends Person {
    private String program;
    private int year;
    private double fee;
    
    // Constructor
    public Student(String name, String address, String program, int year, double fee) {
        super(name, address); // Llamar al constructor de la clase padre
        this.program = program;
        this.year = year;
        this.fee = fee;
    }
    
    // Getters
    public String getProgram() {
        return program;
    }
    
    public int getYear() {
        return year;
    }
    
    public double getFee() {
        return fee;
    }
    
    // Setters
    public void setProgram(String program) {
        this.program = program;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public void setFee(double fee) {
        this.fee = fee;
    }
    
    // Método toString
    @Override
    public String toString() {
        return "Student[" + super.toString() + ", program=" + program + 
               ", year=" + year + ", fee=" + fee + "]";
    }
}


// Clase hija (subclase) - Staff
class Staff extends Person {
    private String school;
    private double pay;
    
    // Constructor
    public Staff(String name, String address, String school, double pay) {
        super(name, address); // Llamar al constructor de la clase padre
        this.school = school;
        this.pay = pay;
    }
    
    // Getters
    public String getSchool() {
        return school;
    }
    
    public double getPay() {
        return pay;
    }
    
    // Setters
    public void setSchool(String school) {
        this.school = school;
    }
    
    public void setPay(double pay) {
        this.pay = pay;
    }
    
    // Método toString
    @Override
    public String toString() {
        return "Staff[" + super.toString() + ", school=" + school + 
               ", pay=" + pay + "]";
    }
}


// Clase principal
public class Herencia {
    public static void main(String[] args) {
        // Crear una persona
        Person person = new Person("Juan Pérez", "Calle 5 #10");
        System.out.println(person);
        System.out.println();
        
        // Crear un estudiante
        Student student = new Student("María López", "Calle 8 #20", 
                                      "Ingeniería de Software", 2, 5000.00);
        System.out.println(student);
        System.out.println("Programa: " + student.getProgram());
        System.out.println("Año: " + student.getYear());
        System.out.println("Fee: " + student.getFee());
        System.out.println();
        
      
        Staff staff = new Staff("Carlos Rodríguez", "Calle 12 #30", 
                               "Escuela de Ingeniería", 3500.00);
        System.out.println(staff);
        System.out.println("Escuela: " + staff.getSchool());
        System.out.println("Salario: " + staff.getPay());
        System.out.println();
        
     
        student.setYear(3);
        staff.setPay(4000.00);
        System.out.println("Estudiante actualizado: " + student.getYear());
        System.out.println("Staff actualizado: " + staff.getPay());
    }
}