// Employee.java
public class Employee {
    protected int employeeID;
    protected String name;
    protected int age;
    protected String NIC;
    protected String contact;
    protected String address;
    protected double salary;
    protected String designation;

    public Employee(int employeeID, String name, int age, String NIC, String contact, String address, double salary, String designation) {
        this.employeeID = employeeID;
        this.name = name;
        this.age = age;
        this.NIC = NIC;
        this.contact = contact;
        this.address = address;
        this.salary = salary;
        this.designation = designation;
    }

    public void calculateSalary() {
        System.out.println("Salary calculation logic here.");
    }

    public void role() {
        System.out.println("Role: " + designation);
    }
}



