/**
 * Testrunner
 */
public class Testrunner {
    private static int pass = 0 ;
    private static int fail = 0 ;

    /**พิมพ์ PASS / FAIL และนับผลให้เอง*/
    private static void check(String name, boolean condition){
        if (condition) {
            pass++;
            System.out.println("[PASS]"+ name);
        }else{
            fail++;
            System.out.println("[FAIL]" + name);
        }
    }

    public static void main(String[] args){
        boolean asserts0n = false ;
        assert asserts0n =true;
        if (!asserts0n){
            System.out.println("WARNING: assertions disabled"
                + " - re -run with: java -ea BoudedStackTest\n"
            );
        }

        System.out.println("============================");
        System.out.println("=  BoudedStack Test Suite  =");
        System.out.println("============================");

        testCreators();
        testPush();
        testRemove();
        System.out.println("\n=== Summary ===");
        System.out.println("Pass: " + pass);
        System.out.println("Fail: " + fail);
        System.out.println("Total : " + (pass + fail));
        System.out.println(fail == 0 ? "ALL TESTS PASS" : "SOME TESTS FAILED");

        
    }
    // --Patition : ว่าง / ที่จอดรถเต็ม / input ที่ผิดเงื่อนไข ไว้มาแก้ เขียนไว้ก่อน --
    private static void testCreators(){ 
        System.out.println("-- Creators --");
        // กรณีที่ปกติ
        BoundedStack stack = new BoundedStack(10);
        check(" สร้างช่องจอดรถ 10 ช่อง -> จำนวนรถเริ่มต้น 0 ", stack.count()==0);
        check(" สร้างช่องจอดรถ 10 ช่อง -> ช่องจอดรถต้องว่าง ", stack.isEmpty());
        check(" สร้างช่องจอดรถ 10 ช่อง -> ช่องจอดรถต้องไม่เต็ม ", !stack.isFull());

        //กรณีขอบ (Boundary Case)
        stack = new BoundedStack(1);
        check(" สร้างช่องจอดรถ 1 ช่อง -> จำนวนรถเริ่มต้น 0 ", stack.count()==0);
        check(" สร้างช่องจอดรถ 1 ช่อง -> ช่องจอดรถต้องว่าง ", stack.isEmpty());
        check(" สร้างช่องจอดรถ 1 ช่อง -> ช่องจอดรถต้องไม่เต็ม ", !stack.isFull());

        //กรณีที่Inputผิดเงื่อนไข ต้องโยน exception !!
        boolean threwZero = false ;
        try{
            new BoundedStack(0);
        } catch (IllegalArgumentException e){
            threwZero = true ;
            //System.out.println("สร้างไม่ได้");   
        }
        check("สร้างช่องจอดรถ 0 ช่อง = ยังไม่ได้สร้างช่องจอดรถ", threwZero);

        boolean threwNegative = false ;
        try{
            new BoundedStack(-10);
        } catch (IllegalArgumentException e){
            threwNegative = true ;
        } 
        check("สร้างช่องจอดรถ ติดลบ ช่องซึ่งเป็นไปไม่ได้ ", threwNegative);

    }
    private static void testPush(){
        System.out.println("-- Push --");
        //กรณีปกติ
        BoundedStack stack = new BoundedStack(5); //ลานจอดรถขนาด5ช่อง ( ลานกว้าง )
        //  จอดคันแรก
        stack.push(001);
        check("จอด 001 -> จำนวนรถที่จอดในลานจอดรถคือ 1",stack.count()==1);
        check("จอด 001 -> รถคันล่าสุดต้องเป็น 001",stack.top() == 001);
        check("จอด 001 -> ลานจอดรถยังไม่เต็ม",!stack.isEmpty());

        stack.push(002);
        check("จอด 002 -> จำนวนรถที่จอดในลานจอดรถคือ 2",stack.count()==2);
        check("จอด 002 -> รถคันล่าสุดต้องเป็น 002",stack.top() == 002);
        check("จอด 002 -> ลานจอดรถยังไม่เต็ม",!stack.isEmpty());

        stack.push(003);
        check("จอด 003 -> จำนวนรถที่จอดในลานจอดรถคือ 3",stack.count()==3);
        check("จอด 003 -> รถคันล่าสุดต้องเป็น 003",stack.top() == 003);
        check("จอด 003 -> ลานจอดรถยังไม่เต็ม",!stack.isEmpty());

        stack.push(004);
        check("จอด 004 -> จำนวนรถที่จอดในลานจอดรถคือ 4",stack.count()==4);
        check("จอด 004 -> รถคันล่าสุดต้องเป็น 004",stack.top() == 004);
        check("จอด 004 -> ลานจอดรถยังไม่เต็ม",!stack.isEmpty());

        stack.push(005);
        check("จอด 005 -> จำนวนรถที่จอดในลานจอดรถคือ 5",stack.count()==5);
        check("จอด 005 -> รถคันล่าสุดต้องเป็น 005",stack.top() == 005);
        check("จอด 005 -> ลานจอดรถเต็ม",stack.isFull());

        //กรณีที่Inputผิดเงื่อนไข ต้องโยน exception !!
        boolean threwZeroId = false ; 
        try{
            stack.push(0);
        } catch(IllegalArgumentException e){
            threwZeroId = true ;   
        }
        check("ยังไม่ได้จอดอะไรเข้าไป", threwZeroId);

        boolean threwNegativeId = false ;
        try{
            stack.push(-2);
        } catch(IllegalArgumentException e){
            threwNegativeId = true ;
        }
        check("จอดรถจำนวนติดลบไม่ได้", threwNegativeId);

        //กรณีลานจอรถเต็มแล้วแต่ฝืนจอดเพิ่ม
        boolean threwFull = false ; 
        try{
            stack.push(006); //ลานจอดมี 5 แต่พยายามจอด 6
        } catch(IllegalStateException e){
            threwFull = true ;
        }
        check("จอดเพิ่มไม่ได้เต็มแล้ว", threwFull);
        check("จอดตอนลานจอดรถไม่เต็มจำนวนในลานจอดรถยังคงเป็นเท่าเดิม", stack.count()==5);

        }
        private static void testRemove(){
            System.out.println("-- Remove --");
            //กรณีปกติ
            BoundedStack stack = new BoundedStack(3); //
            stack.push(001);
            stack.push(002);
            stack.push(003);
            
            //รถคันล่าสุดต้องออกก่อน
            check("003 -> ต้องออกจากลานจอดรถก่อน", stack.remove()==003);
            check("จำนวนรถในลานจอดรถต้องเหลือ -> 2 คัน", stack.count()==2);
            check("คันล่าสุดต้องเป็น -> 002", stack.top()==002);

            check("คันถัดไป", stack.remove()==002);
            check("จำนวนรถในลานจอดรถต้องเหลือ -> 1 คัน", stack.count()==1);
            check("คันล่าสุดต้องเป็น -> 001", stack.top()==001);

            check("คันถัดไป", stack.remove()==001);
            //กรณีขอบ (Boundary Case)
            check("จำนวนรถในลานจอดรถต้องเหลือ -> 0 คัน", stack.count()==0);
            check("ลานจอดรถว่าง", stack.isEmpty());

            //กรณีเอาออกตอนลานจอดรถว่าง
            boolean threwEmpty = false;
            try{
                stack.remove();
            }catch(IllegalStateException e){
                threwEmpty = true;
            }
            check("เอารถออกตอนลานกว้างไม่ได้", threwEmpty);
        }

        





}
