package threadsafe_java;

public class OrderInfo {
    private String productName;
    private int amount;
    private long timestamp;

    public OrderInfo(String productName, int amount, long timestamp) {
        this.productName = new String(productName);
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
