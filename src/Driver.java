// Driver.java
public class Driver extends Employee {
    private String licenseNumber;
    private int experience;

    public Driver(int employeeID, String name, int age, String NIC, String contact, String address,
                  double salary, String designation, String licenseNumber, int experience) {
        super(employeeID, name, age, NIC, contact, address, salary, designation);
        this.licenseNumber = licenseNumber;
        this.experience = experience;
    }

    public void assignVehicle() {
        System.out.println("Vehicle assigned to driver.");
    }
}
