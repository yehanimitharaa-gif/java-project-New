public class Driver extends Employee {
    private String licenseNumber;
    private int experience;

    public Driver(int employeeID, String name, int age, String NIC, String contact, String address,
                  String email, String accountNumber, double salary, String designation,
                  int leavesTaken, double bonus, String licenseNumber, int experience) {
        super(employeeID, name, age, NIC, contact, address, email, accountNumber, salary, designation, leavesTaken, bonus);
        this.licenseNumber = licenseNumber;
        this.experience = experience;
    }

    public void assignVehicle() {
        System.out.println("Vehicle assigned to driver.");
    }

    @Override
    public String toString() {
        return super.toString() + "\nLicense No: " + licenseNumber + "\nExperience: " + experience + " years";
    }
}
