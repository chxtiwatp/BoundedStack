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
     *   และ carArea[0..size-1] > 0 (รหัสรถต้องเป็นจำนวนเต็มบวก)
     */

    private final int[] carArea;
    private int size;
    //carArea คือ array ที่เก็บรหัสรถที่จอดอยู่ในลานจอดรถ
    //size คือจำนวนรถที่จอดอยู่ในลานจอดรถ

    private void checkRep() {
        assert carArea != null;
        assert size >= 0 && size <= carArea.length;
        for (int i = 0; i < size; i++) {
            assert carArea[i] > 0 : "เลขรถต้องเป็นค่าบวกเท่านั้น";
        }
    }

    public BoundedStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("ความจุของลานจอดรถต้องมากกว่า 0");
        }
        this.carArea = new int[capacity];
        this.size = 0;
        checkRep();
    }

    public void parkCar(int carId) {
        if (carId <= 0) {
            throw new IllegalArgumentException("เลขรถต้องเป็นค่าบวกเท่านั้น");
        }
        if (isFull()) {
            throw new IllegalStateException("ลานจอดรถเต็ม");
        }
        carArea[size] = carId;
        size++;
        checkRep();
    }

    public int driveOutCar() {
        if (isEmpty()) {
            throw new IllegalStateException("ลานจอดรถว่าง");
        }
        size--;
        int carId = carArea[size];
        carArea[size] = 0; // เคลียร์ช่องจอด
        checkRep();
        return carId;
    }

    public int peekFrontCar() {
        if (isEmpty()) {
            throw new IllegalStateException("Parking lot is empty");
        }
        checkRep();
        return carArea[size - 1];
    }

    public int carCount() {
        checkRep();
        return size;
    }

    public boolean isFull() {
        return size == carArea.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}