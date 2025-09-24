public class Account {
    // Atributos
    private String id;
    private String name;
    private int balance;

    // Constructor
    public Account(String id, String name) {
        this.id = id;
        this.name = name;
        this.balance = 0;  // Saldo inicial es 0
    }

    public Account(String id, String name, int balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    // Métodos Getter
    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    // Método para agregar saldo (crédito)
    public int credit(int amount) {
        balance += amount;
        return balance;
    }

    // Método para debitar saldo
    public int debit(int amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Amount exceeded balance");
        }
        return balance;
    }

    // Método para transferir saldo a otra cuenta
    public int transferTo(Account another, int amount) {
        if (amount <= balance) {
            balance -= amount;
            another.credit(amount);
        } else {
            System.out.println("Amount exceeded balance");
        }
        return balance;
    }

    // Método toString
    @Override
    public String toString() {
        return "Account[id=" + id + ",name=" + name + ",balance=" + balance + "]";
    }

    // Método principal (main) para probar la clase
    public static void main(String[] args) {
        Account acc1 = new Account("123", "Alice", 500);
        Account acc2 = new Account("456", "Bob", 300);

        // Mostrar información de las cuentas
        System.out.println(acc1.toString());
        System.out.println(acc2.toString());

        // Realizar operaciones
        acc1.debit(100);  // Debitar 100 de la cuenta de Alice
        System.out.println("Balance after debit: " + acc1.getBalance());

        acc1.transferTo(acc2, 200);  // Transferir 200 de Alice a Bob
        System.out.println(acc1.toString());
        System.out.println(acc2.toString());
    }
}
