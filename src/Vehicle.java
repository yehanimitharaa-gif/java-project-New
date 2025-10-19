
// Vehicle.java
public class Vehicle {
    private int vehicleID;
    private String vehicleNumber;
    private String vehicleType;
    private String model;

    public Vehicle(int vehicleID, String vehicleNumber, String vehicleType, String model) {
        this.vehicleID = vehicleID;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.model = model;
    }

    public void addVehicle() { System.out.println("Vehicle added."); }
    public void removeVehicle() { System.out.println("Vehicle removed."); }
    public void updateVehicle() { System.out.println("Vehicle updated."); }
}

