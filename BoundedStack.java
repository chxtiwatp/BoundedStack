/**
 * BoundedStack — จำลองลานจอดรถแคบ ทางเข้าออกทางเดียว จอดได้สูงสุดตามความจุ,
 * รถที่จอดล่าสุด (อยู่ปากทางออก) ต้องออกก่อนเสมอ
 */
public class BoundedStack {

    /* Abstraction Function(AF):
     *   AF(carArea, count) = ลานจอดรถที่มีรถจอดอยู่ count คัน โดยรถคันแรก (ลึกสุด) อยู่ที่ carArea[0] 
     *   และรถคันล่าสุด (ปากทางออก) อยู่ที่ carArea[count-1]
     * 
     * Representation Invariant(RI):
     *   RI: carArea != null && 0 <= count && count <= carArea.length
     *   และ carArea[0..count-1] > 0 (รหัสรถต้องมีค่าบวกเท่านั้น)
     */

    //Creator: BoundedStack(int capacity)
    //Producer: BoundedStack(BoundedStack other) (สร้าง Object ใหม่จากอันเดิม)
    //Mutator: push(int carId), remove()
    //Observer: top(), size(), isFull(), isEmpty()

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
    /**
     * สร้างลานจอดรถตามความจุที่กำหนด
     * @param capacity ความจุของ ลานจอดรถต้องมากกว่า 0
     * @throws IllegalArgumentException capacity <= 0
     */
    public BoundedStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("ความจุของลานจอดรถต้องมากกว่า 0");
        }
        this.carArea = new int[capacity];
        this.count = 0;
        checkRep();
    }

    /**
     * คัดลอกสร้างลานจอดรถใหม่จากต้นฉบับ
     * @param other BoundedStack ต้นฉบับที่ต้องการคัดลอก 
     * @throws IllegalArgumentException other == null
     */
    public BoundedStack(BoundedStack other) {
    if (other == null) {
        throw new IllegalArgumentException("ต้นฉบับที่จะ copy ต้องไม่เป็น null");
    }
    this.carArea = other.carArea.clone();
    this.count = other.count;
    checkRep();
}

    /**
     * เลขรถต้องเป็นค่าบวก
     * @param carId หมายเลขรถ
     * @throws IllegalArgumentException carId <= 0
     * @throws IllegalStateException ถ้าลานจอดรถเต็ม
     */
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

    
    /**
     * นำรถคันล่าสุด (ปากทางออก) ออกจากลานจอดรถ
     * @return carId หมายเลขรถที่ถูกนำออกไป
     * @throws IllegalStateException ถ้าลานจอดรถว่าง
     */
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

    /**
     * เช็ครถคันที่จอดล่าสุด
     * @return รหัสสหมายเลขรถคันล่าสุดที่จอด
     * @throws IllegalStateException ถ้าลานจอดว่างจะโยนException
     */
    public int top() {
        if (isEmpty()) {
            throw new IllegalStateException("ที่จอดรถว่าง");
        }
        checkRep();
        return carArea[count - 1];
    }


    /**
     * จำนวนรถที่จอดอยู่ในลานจอดรถปัจจุบัน
     * @return จำนวนรถปัจจุบันที่อยู่ในลานจอดรถ
     */
    public int size() {
        checkRep();
        return count;
    }


    /**
     * ตรวจสอบว่าลานจอดรถเต็มหรือไม่
     * @return true ถ้าลานจอดรถเต็ม , false ถ้าลานจอดรถไม่เต็ม
     */
    public boolean isFull() {
        return count == carArea.length;
    }


    /**
     * ตรวจสอบว่าลานจอดรถว่างหรือไม่
     * @return  true ถ้าลานจอดรถว่าง , false ถ้าลานจอดรถไม่ว่าง 
     */
    public boolean isEmpty() {
        return count == 0;
    }
}