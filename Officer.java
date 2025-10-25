public class Officer extends Employee {
    private String department;

    public Officer(int employeeID, String name, int age, String NIC, String contact, String address,
                   String email, String accountNumber, double salary, String designation,
                   int leavesTaken, double bonus, String department) {
        super(employeeID, name, age, NIC, contact, address, email, accountNumber, salary, designation, leavesTaken, bonus);
        this.department = department;
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

    @Override
    public String toString() {
        return super.toString() + "\nDepartment: " + department;
    }
}

