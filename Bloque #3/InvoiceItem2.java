public class InvoiceItem2 {
    // Atributos
    private String id;
    private String desc;
    private int qty;
    private double unitPrice;

    // Constructor
    public InvoiceItem2(String id, String desc, int qty, double unitPrice) {
        this.id = id;
        this.desc = desc;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    // Métodos Getter
    public String getID() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public int getQty() {
        return qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    // Métodos Setter
    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    // Método para calcular el total (unitPrice * qty)
    public double getTotal() {
        return unitPrice * qty;
    }

    // Método toString
    @Override
    public String toString() {
        return "InvoiceItem[id=" + id + ",desc=" + desc + ",qty=" + qty + ",unitPrice=" + unitPrice + "]";
    }

    // Método principal (main) para probar la clase
    public static void main(String[] args) {
        InvoiceItem2 item = new InvoiceItem2("101", "Laptop", 2, 1500.50);

        // Mostrar información del artículo de factura
        System.out.println(item.toString());

        // Mostrar el total
        System.out.println("Total: " + item.getTotal());
    }
}
