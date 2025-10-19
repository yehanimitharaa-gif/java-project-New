public class Fuel {
    private int fuelID;
    private String location;
    private double costPerLiter;

    public Fuel(int fuelID, String location, double costPerLiter) {
        this.fuelID = fuelID;
        this.location = location;
        this.costPerLiter = costPerLiter;
    }

    public void locationFuelCost() { System.out.println("Fuel cost calculated by location."); }
}


