public class BoudedStackTest {
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
        check("ลานจอดรถต้องไม่เป็นค่า null",stack!=null); 
        check("รถที่จอดอยู่ต้องมีจำนวนน้อยกว่าความจุในลานจอดรถ",stack.size()<1);

        try {
            new BoundedStack(0);
            check("ความจุของลานจอดรถต้องมากกว่า 0", false);
        } catch (IllegalArgumentException e) {
            check("ความจุของลานจอดรถต้องมากกว่า 0", true);  
        }
        
        try {
            new BoundedStack(-1);
            check("ความจุลานจอดรถต้องไม่เป็นค่าลบ", false);
        } catch (IllegalArgumentException e) {
            check("ความจุลานจอดรถต้องไม่เป็นค่าลบ", true);  
        }

        BoundedStack original = new BoundedStack(3);
        original.push(001);     
        original.push(002);
        
        BoundedStack copy = new BoundedStack(original);
        check("ต้นฉบับต้องไม่เท่ากับnull", original != null);
        check("ขนาดที่ก็อปมาต้องมีค่าเท่ากับต้นฉบับ", copy.size()==original.size());
        check("รถคันล่าสุดต้องเท่ากับคันล่าสุดของต้นฉบับ", copy.top()==original.top());

        try {
            stack.push(-1);
            check("เลขรถต้องเป็นค่าบวกเท่านั้น", false);
        } catch (IllegalArgumentException e) {
            check("เลขรถต้องเป็นค่าบวกเท่านั้น", true);
        }

        BoundedStack fullmai = new BoundedStack(1);
        fullmai.push(001);
        check("ลานจอดรถเต็ม", fullmai.isFull());

        try {
            fullmai.push(002);
            check("ไม่สามารถจอดเพิ่มได้", false);
        } catch (IllegalStateException e) {
            check("ไม่สามารถจอดเพิ่มได้", true);
        }

        BoundedStack removed = new BoundedStack(1);
        removed.push(001);
        removed.remove();
        check("ลานจอดรถว่าง", removed.size()==0);

        try {
            removed.remove();
            check("ไม่มีรถให้เอาออกแล้ว", false);
        } catch (IllegalStateException e) {
            check("ไม่มีรถให้เอาออกแล้ว", true);
        }

        BoundedStack topped = new BoundedStack(2);
        topped.push(456);
        topped.push(123);
        check("แสดงรถคันล่าสุด", topped.top()==123);
        
        BoundedStack sized = new BoundedStack(1);
        sized.push(1);
        check("", sized.size()==1);

        BoundedStack fulled = new BoundedStack(1);
        fulled.push(1);
        check("", fulled.isFull());

        BoundedStack empted = new BoundedStack(1);
        check("", empted.isEmpty());

        System.out.println("==================================");
        System.out.printf("PASS %d / FAIL %d%n", pass, fail);
        System.out.println("==================================");
    }






}