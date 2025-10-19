public class Order {
    private int orderID;
    private String orderStatus;

    public Order(int orderID, String orderStatus) {
        this.orderID = orderID;
        this.orderStatus = orderStatus;
    }

    public void maintainOrderState() { System.out.println("Order state maintained."); }
}
