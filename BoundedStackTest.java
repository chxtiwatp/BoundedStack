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
        check("ลานจอดรถต้องไม่เป็นค่า null",stack!=null); //ลานจอดรถต้องไม่เป็นค่า null
        check("รถที่จอดอยู่ต้องมีจำนวนน้อยกว่าความจุในลานจอดรถ",stack.size()<1); //รถที่จอดอยู่ต้องมีจำนวนน้อยกว่าความจุในลานจอดรถ

        try {
            new BoundedStack(0);
            check("ความจุของลานจอดรถต้องมากกว่า 0", false); //ความจุของลานจอดรถเท่ากับ 0 ต้องโยน Exception
        } catch (IllegalArgumentException e) {
            check("ความจุของลานจอดรถต้องมากกว่า 0", true);  
        }
        
        try {
            new BoundedStack(-1);
            check("ความจุลานจอดรถต้องไม่เป็นค่าลบ", false); //ความจุลานจอดรถหากเป็นค่าลบ ต้องโยน exception
        } catch (IllegalArgumentException e) {
            check("ความจุลานจอดรถต้องไม่เป็นค่าลบ", true);  
        }

        BoundedStack original = new BoundedStack(3);
        original.push(1);     
        original.push(2);
        
       BoundedStack copy = new BoundedStack(original);
        check("ต้นฉบับต้องไม่เท่ากับnull", original != null);
        check("ขนาดที่ก็อปมาต้องมีค่าเท่ากับต้นฉบับ", copy.size()==original.size());
        check("รถคันล่าสุดต้องเท่ากับคันล่าสุดของต้นฉบับ", copy.top()==original.top());
        copy.push(3);
        check("จำนวนรถในลานจอดรถของ copy  มี 3คัน", copy.size()==3); //จำนวนรถในลานจอดรถของ copy  มี 3คัน
        check("จำนวนรถในลานจอดรถของต้นฉบับ มี 2 คัน", original.size()==2); //จำนวนรถในลานจอดรถของต้นฉบับ มี 2 คัน

        try {
            new BoundedStack((BoundedStack)null);
            check("ต้นฉบับที่จะcopy ต้องไม่เป็น null", false); //ถ้าคัดลอกลานจอดรถเป็น null ต้องโยน exception
        } catch (IllegalArgumentException e) {
            check("ต้นฉบับที่จะcopy ต้องไม่เป็น null", true);
        }

        try {
            stack.push(-1);
            check("เลขรถต้องเป็นค่าบวกเท่านั้น", false); //เลขของรถที่รับค่ามาต้องเป็นค่าบวกเท่านั้น
        } catch (IllegalArgumentException e) {
            check("เลขรถต้องเป็นค่าบวกเท่านั้น", true);
        }

        BoundedStack fullmai = new BoundedStack(1); 
        fullmai.push(1);
        check("ลานจอดรถเต็ม", fullmai.isFull()); //เช็คว่าลานจอดรถเต็มไหม

        try {
            fullmai.push(2);
            check("ไม่สามารถจอดเพิ่มได้", false); //เช็คว่าจอดเพิ่มได้ไหมถ้าจอดเพิ่มไม่ได้ให้โยน exception
        } catch (IllegalStateException e) {
            check("ไม่สามารถจอดเพิ่มได้", true);
        }

        BoundedStack removed = new BoundedStack(1); 
        removed.push(1);
        removed.remove();
        check("ลานจอดรถว่าง", removed.size()==0); //เช็คว่าหลังเอารถออกจากลานแล้วมีพื้นที่ว่างไหม

        try {
            removed.remove();
            check("ไม่มีรถให้เอาออกแล้ว", false); //หากไม่มีรถจอดอยู่อยู่แล้วให้โยน exception
        } catch (IllegalStateException e) {
            check("ไม่มีรถให้เอาออกแล้ว", true);
        }

        BoundedStack topped = new BoundedStack(2); 
        topped.push(456);
        topped.push(123);
        check("แสดงรถคันล่าสุด", topped.top()==123); //แสดงรถที่มาจอดคันล่าสุด
        BoundedStack stackempty = new BoundedStack(2);
        try {
            stackempty.top();
            check("ดึงรถคันล่าสุดจากลานว่าง", false); //เช็คข้อมูลว่าในลานมีรถคันล่าสุดจอดอยู่ไหมถ้าไม่ให้โยน exception
        } catch (IllegalStateException e) {
            check("ดึงรถคันล่าสุดจากลานว่าง", true);
        }
        
        BoundedStack sized = new BoundedStack(1);
        sized.push(1);
        check("เช็คว่ามีรถมาจอดในลานกี่คัน", sized.size()==1); //เช็คว่าตอนนี้มีรถมาจอดในลานจอดรถกี่คัน

        BoundedStack fulled = new BoundedStack(1);
        fulled.push(1);
        check("เช็คว่าตอนนี้มีรถมาจอดในลานเต็มรึยัง", fulled.isFull()); //เช็คว่าตอนนี้มีรถมาจอดในลานจอดรถเต็มรึยัง

        BoundedStack empted = new BoundedStack(1);
        check("เช็คว่าลานจอดรถว่างไหม", empted.isEmpty()); //เช็คว่าลานจอดรถว่างไหม

        // 1. เทสการ push และ remove สลับกันหลายๆ รอบ
        BoundedStack test = new BoundedStack(3);
        test.push(1);
        test.push(2);
        check("ถอดค่าล่าสุดออก", test.remove()==2);
        test.push(3);
        check("ค่าบนสุดต้องเป็นค่าล่าสุดที่ใส่", test.top()==3);

        // 2. เทส Boundary: ลานจอดความจุ 1 ใส่จนเต็ม ถอดออกจนว่าง แล้วใส่ใหม่ได้
        BoundedStack bdry = new BoundedStack(1);
        bdry.push(5);
        check("สถานะต้องแสดงว่าเต็ม", bdry.isFull());
        bdry.remove();
        check("สถานะต้องแสดงว่าว่าง", bdry.isEmpty());
        bdry.push(67);
        check("ใส่ค่าใหม่ได้และค่าบนสุดถูกต้อง", bdry.top()==67);

        System.out.println("==================================");
        System.out.printf("PASS %d / FAIL %d%n", pass, fail);
        System.out.println("==================================");
    }






}