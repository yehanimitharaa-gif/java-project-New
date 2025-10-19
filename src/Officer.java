// Officer.java
public class Officer extends Employee {
    public Officer(int employeeID, String name, int age, String NIC, String contact, String address,
                   double salary, String designation) {
        super(employeeID, name, age, NIC, contact, address, salary, designation);
    }

    public void scheduleMeeting() {
        System.out.println("Meeting scheduled.");
    }

    public void generateReport() {
        System.out.println("Report generated.");
    }

    public void generatePayment() {
        System.out.println("Payment generated.");
    }
}
