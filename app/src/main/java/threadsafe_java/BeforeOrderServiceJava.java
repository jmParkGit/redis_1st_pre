package threadsafe_java;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class BeforeOrderServiceJava {
    private Lock lock = new ReentrantLock();
    // 상품 DB
    private final Map<String, Integer> productDatabase = new HashMap<>();
    
    // 가장 최근 주문 정보를 저장하는 DB
    private final ThreadLocal<OrderInfo> latestOrderDatabase = ThreadLocal.withInitial(() -> null);

    public BeforeOrderServiceJava() {
        // 초기 상품 데이터
        productDatabase.put("apple", 100);
        productDatabase.put("banana", 50);
        productDatabase.put("orange", 75);
    }

    // 주문 처리 메서드
    public void order(String productName, int amount) {
        lock.lock();
        boolean result = false;
        Integer currentStock = productDatabase.getOrDefault(productName, 0);

        try {
            Thread.sleep(1); // 동시성 이슈 유발을 위한 인위적 지연
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        if (currentStock >= amount) {
            productDatabase.put(productName, currentStock - amount);
            latestOrderDatabase.set(new OrderInfo(productName, amount, System.currentTimeMillis()));
            result = true;
        }

        System.out.println("Thread " + Thread.currentThread().getName() + " 주문 정보: " + "\n" +
        productName + ": "  + "1" + " 건 ([주문량: " + amount +"]), result: " + result);
        lock.unlock();
    }

    // 재고 조회
    public int getStock(String productName) {
        return productDatabase.getOrDefault(productName, 0);
    }
}