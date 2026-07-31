/**
 * BoundedStack — จำลองลานจอดรถแคบ ทางเข้าออกทางเดียว จอดได้สูงสุดตามความจุ,
 * รถที่จอดล่าสุด (อยู่ปากทางออก) ต้องออกก่อนเสมอ
 */
public class BoundedStack {

    /* Abstraction Function(AF):
     *   AF(carArea, size) = ลานจอดรถที่มีรถจอดอยู่ size คัน โดยรถคันแรก (ลึกสุด) อยู่ที่ carArea[0] 
     *   และรถคันล่าสุด (ปากทางออก) อยู่ที่ carArea[size-1]
     * 
     * Representation Invariant(RI):
     *   RI: carArea != null && 0 <= size && size <= carArea.length
     *   และ carArea[0..size-1] > 0 (รหัสรถต้องมีค่าบวกเท่านั้น)
     */

    private final int[] carArea;
    private int count;
    //carArea คือ array ที่เก็บรหัสรถที่จอดอยู่ในลานจอดรถ
    //size คือจำนวนรถที่จอดอยู่ในลานจอดรถ

    private void checkRep() {
        assert carArea != null;
        assert count >= 0 && count <= carArea.length;
        for (int i = 0; i < count; i++) {
            assert carArea[i] > 0 : "เลขรถต้องเป็นค่าบวกเท่านั้น";
        }
    }

    public BoundedStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("ความจุของลานจอดรถต้องมากกว่า 0");
        }
        this.carArea = new int[capacity];
        this.count = 0;
        checkRep();
    }

    public BoundedStack(BoundedStack other) {
    if (other == null) {
        throw new IllegalArgumentException("ต้นฉบับที่จะopy ต้องไม่เป็น null");
    }
    this.carArea = other.carArea.clone();
    this.count = other.count;
    checkRep();
}

    public void push(int carId) {
        if (carId <= 0) {
            throw new IllegalArgumentException("เลขรถต้องเป็นค่าบวกเท่านั้น");
        }
        if (isFull()) {
            throw new IllegalStateException("ลานจอดรถเต็ม");
        }
        carArea[count] = carId;
        count++;
        checkRep();
    }

    

    public int remove() {
        if (isEmpty()) {
            throw new IllegalStateException("ลานจอดรถว่าง");
        }
        count--;
        int carId = carArea[count];
        carArea[count] = 0; // เคลียร์ช่องจอด
        checkRep();
        return carId;
    }

    public int top() {
        if (isEmpty()) {
            throw new IllegalStateException("ที่จอดรถว่าง");
        }
        checkRep();
        return carArea[count - 1];
    }

    public int size() {
        checkRep();
        return count;
    }

    public boolean isFull() {
        return count == carArea.length;
    }

    public boolean isEmpty() {
        return count == 0;
    }
}