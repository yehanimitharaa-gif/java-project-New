public class Employee {
    // Fields should be private for encapsulation
    private int employeeID, age, leavesTaken;
    private String name, NIC, contact, address, email, accountNumber, designation;
    private double salary, bonus;

    // Full constructor (12 parameters)
    public Employee(int employeeID, String name, int age, String NIC, String contact, String address,
                    String email, String accountNumber, double salary, String designation,
                    int leavesTaken, double bonus) {
        this.employeeID = employeeID;
        this.name = name;
        this.age = age;
        this.NIC = NIC;
        this.contact = contact;
        this.address = address;
        this.email = email;
        this.accountNumber = accountNumber;
        this.salary = salary;
        this.designation = designation;
        this.leavesTaken = leavesTaken;
        this.bonus = bonus;
    }

    // Getters for all fields
    public int getEmployeeID() { return employeeID; }
    public int getAge() { return age; }
    public int getLeavesTaken() { return leavesTaken; }
    public String getName() { return name; }
    public String getNIC() { return NIC; }
    public String getContact() { return contact; }
    public String getAddress() { return address; }
    public String getEmail() { return email; }
    public String getAccountNumber() { return accountNumber; }
    public String getDesignation() { return designation; }
    public double getSalary() { return salary; }
    public double getBonus() { return bonus; }

    // Optional setters (if you need to update fields)
    public void setSalary(double salary) { this.salary = salary; }
    public void setBonus(double bonus) { this.bonus = bonus; }
    public void setLeavesTaken(int leavesTaken) { this.leavesTaken = leavesTaken; }

    // Calculate final salary considering bonus and leaves deduction
    public double calculateSalary() {
        return salary + bonus - (leavesTaken * 1000); // 1000 deduction per leave
    }

    // toString for display
    @Override
    public String toString() {
        return "ID: " + employeeID +
                "\nName: " + name +
                "\nEmail: " + email +
                "\nAccount No: " + accountNumber +
                "\nDesignation: " + designation +
                "\nSalary: " + salary;
    }
}

