public class BoundedStackTest {
    // ตัวแปรสำหรับสรุปผลทั้งหมด
    static int pass = 0, fail = 0;

    // Helper รับข้อความ+true/false
    static void check(String testName, boolean ok) {
        if (ok) {
            System.out.println("PASS: " + testName);
            pass++;
        } 
        else 
        {
            System.out.println("FAIL: " + testName);
            fail++;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("============================");
        System.out.println("=     BoudedStack Test     =");
        System.out.println("============================");

        //เทสค่าปกติ
        BoundedStack stack = new BoundedStack(1);
        check("The parking lot must not be null",stack!=null); //ลานจอดรถต้องไม่เป็นค่า null
        check("The number of parked vehicles must be less than the parking lot capacity",stack.size()<1); //รถที่จอดอยู่ต้องมีจำนวนน้อยกว่าความจุในลานจอดรถ

        try {
            new BoundedStack(0);
            check("The capacity of the parking lot must be greater than 0", false); //ความจุของลานจอดรถเท่ากับ 0 ต้องโยน Exception
        } catch (IllegalArgumentException e) {
            check("The capacity of the parking lot must be greater than 0", true);  
        }
        
        try {
            new BoundedStack(-1);
            check("The parking lot capacity must not be a negative value", false); //ความจุลานจอดรถหากเป็นค่าลบ ต้องโยน exception
        } catch (IllegalArgumentException e) {
            check("The parking lot capacity must not be a negative value", true);  
        }

        BoundedStack original = new BoundedStack(3);
        original.push(1);     
        original.push(2);
        
       BoundedStack copy = new BoundedStack(original);
        check("The source must not be null", original != null);
        check("The size of the copied item must match the original", copy.size()==original.size());
        check("The latest vehicle must match the latest vehicle of the original", copy.top()==original.top());
        copy.push(3);
        check("There are three cars in the parking lot", copy.size()==3); //จำนวนรถในลานจอดรถของ copy  มี 3คัน
        check("There are two vehicles in the original parking lot", original.size()==2); //จำนวนรถในลานจอดรถของต้นฉบับ มี 2 คัน

        try {
            new BoundedStack((BoundedStack)null);
            check("The source to be copied must not be null", false); //ถ้าคัดลอกลานจอดรถเป็น null ต้องโยน exception
        } catch (IllegalArgumentException e) {
            check("The source to be copied must not be null", true);
        }

        try {
            stack.push(-1);
            check("The source to be copied must not be null", false); //เลขของรถที่รับค่ามาต้องเป็นค่าบวกเท่านั้น
        } catch (IllegalArgumentException e) {
            check("The source to be copied must not be null", true);
        }

        BoundedStack fullmai = new BoundedStack(1); 
        fullmai.push(1);
        check("The parking lot is full", fullmai.isFull()); //เช็คว่าลานจอดรถเต็มไหม

        try {
            fullmai.push(2);
            check("The parking lot is full", false); //เช็คว่าจอดเพิ่มได้ไหมถ้าจอดเพิ่มไม่ได้ให้โยน exception
        } catch (IllegalStateException e) {
            check("The parking lot is full", true);
        }

        BoundedStack removed = new BoundedStack(1); 
        removed.push(1);
        removed.remove();
        check("The parking lot is empty", removed.size()==0); //เช็คว่าหลังเอารถออกจากลานแล้วมีพื้นที่ว่างไหม

        try {
            removed.remove();
            check("There are no more cars left to take out", false); //หากไม่มีรถจอดอยู่อยู่แล้วให้โยน exception
        } catch (IllegalStateException e) {
            check("There are no more cars left to take out", true);
        }

        BoundedStack topped = new BoundedStack(2); 
        topped.push(456);
        topped.push(123);
        check("Show the latest car", topped.top()==123); //แสดงรถที่มาจอดคันล่าสุด
        BoundedStack stackempty = new BoundedStack(2);
        try {
            stackempty.top();
            check("Pull the latest car from the open lot", false); //เช็คข้อมูลว่าในลานมีรถคันล่าสุดจอดอยู่ไหมถ้าไม่ให้โยน exception
        } catch (IllegalStateException e) {
            check("Pull the latest car from the open lot", true);
        }
        
        BoundedStack sized = new BoundedStack(1);
        sized.push(1);
        check("Check how many cars are parked in the lot", sized.size()==1); //เช็คว่าตอนนี้มีรถมาจอดในลานจอดรถกี่คัน

        BoundedStack fulled = new BoundedStack(1);
        fulled.push(1);
        check("Check if the parking lot is full", fulled.isFull()); //เช็คว่าตอนนี้มีรถมาจอดในลานจอดรถเต็มรึยัง

        BoundedStack empted = new BoundedStack(1);
        check("Check if the parking lot is free", empted.isEmpty()); //เช็คว่าลานจอดรถว่างไหม

        // 1. เทสการ push และ remove สลับกันหลายๆ รอบ
        BoundedStack test = new BoundedStack(3);
        test.push(1);
        test.push(2);
        check("Remove the latest value", test.remove()==2); //ถอดค่าล่าสุดออก
        test.push(3);
        check("The top value must be the most recently entered value", test.top()==3); //ค่าบนสุดต้องเป็นค่าล่าสุดที่ใส่

        // 2. เทส Boundary: ลานจอดความจุ 1 ใส่จนเต็ม ถอดออกจนว่าง แล้วใส่ใหม่ได้ 
        BoundedStack bdry = new BoundedStack(1);
        bdry.push(5);
        check("The status must show that it is full", bdry.isFull()); //สถานะต้องแสดงว่าเต็ม
        bdry.remove();
        check("The status must show as available", bdry.isEmpty()); //สถานะต้องแสดงว่าว่าง
        bdry.push(67);
        check("New values can be entered and the top value is correct", bdry.top()==67); //ใส่ค่าใหม่ได้และค่าบนสุดถูกต้อง

        System.out.println("==================================");
        System.out.printf("PASS %d / FAIL %d%n", pass, fail);
        System.out.println("==================================");
    }






}