package org.example.reciepes;

public class Order {
    private String id;
    private String customerName;
    private String status;

    public Order(String id, String customerName, String status) {
        this.id = id;
        this.customerName = customerName;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", customerName=" + customerName + ", status=" + status + "]";
    }
}