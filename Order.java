public class Order {
    private int orderID;
    private String orderStatus;

    public Order(int orderID, String orderStatus) {
        this.orderID = orderID;
        this.orderStatus = orderStatus;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void maintainOrderState() {
        // Placeholder for future logic
        System.out.println("Order " + orderID + " state maintained.");
    }
}
