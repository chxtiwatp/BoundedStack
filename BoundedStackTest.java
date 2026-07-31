import java.util.List;

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
       
    }
    // --Patition : ว่าง / ที่จอดรถเต็ม / input ที่ผิดเงื่อนไข ไว้มาแก้ เขียนไว้ก่อน --
    private static void testCreators(){ 
        System.out.println("-- Creators --");
        // กรณีที่ปกติ
        BoundedStack stack = new BoundedStack(10);
        check(" สร้างช่องจอดรถ 10 ช่อง -> จำนวนรถเริ่มต้น 0 ", stack.count()==0);
        check(" สร้างช่องจอดรถ 10 ช่อง -> ช่องจอดรถต้องว่าง -> true ", stack.isEmpty());
        check(" สร้างช่องจอดรถ 10 ช่อง -> ช่องจอดรถต้องเต็ม -> false ", stack.isFull());

        //กรณีขอบ (Boundary Case)
        stack = new BoundedStack(1);
        check(" สร้างช่องจอดรถ 1 ช่อง -> จำนวนรถเริ่มต้น 0 ", stack.count()==0);
        check(" สร้างช่องจอดรถ 1 ช่อง -> ช่องจอดรถต้องว่าง -> true ", stack.isEmpty());
        check(" สร้างช่องจอดรถ 1 ช่อง -> ช่องจอดรถต้องเต็ม -> false ", stack.isFull());

        //กรณีที่Inputผิดเงื่อนไข ต้องโยน exception !!
        boolean threwZero = false ;
        try{
            new BoundedStack(0);
        } catch (IllegalArgumentException e){
            threwZero = true ;
            //System.out.println("สร้างไม่ได้");
        }
        check("สร้างช่องจอดรถ 0 ช่อง -> ต้องโยน IllegalArgumentException", threwZero);

        boolean threwNegative = false ;
        try{
            new BoundedStack(-10);
        } catch (IllegalArgumentException e){
            threwNegative = true ;
        } 
        check("สร้างช่องจอดรถ ติดลบ ช่องซึ่งเป็นไปไม่ได้ -> ต้องโยน IllegalArgumentException", threwNegative);

    }
    private static void testPush(){
        System.out.println("-- Push --");
        //กรณีปกติ
        BoundedStack stack = new BoundedStack(5); //ลานจอดรถขนาด6ช่อง ( ลานกว้าง )
        //  จอดคันแรก
        check("push 001 คันแรก -> count ต้องเป็น 1",stack.count()==1);
        check("push 001 คันแรก -> top ต้องเป็น 001",stack.top() == 001);
        check("push 001 คันแรก -> isEmtry ต้องเป็น false",!stack.isEmpty());

        check("push 002 คันแรก -> count ต้องเป็น 2",stack.count()==1);
        check("push 002 คันแรก -> top ต้องเป็น 002",stack.top() == 002);
        check("push 002 คันแรก -> isEmtry ต้องเป็น false",!stack.isEmpty());

        check("push 003 คันแรก -> count ต้องเป็น 3",stack.count()==1);
        check("push 003 คันแรก -> top ต้องเป็น 003",stack.top() == 003);
        check("push 003 คันแรก -> isEmtry ต้องเป็น false",!stack.isEmpty());

        check("push 004 คันแรก -> count ต้องเป็น 4",stack.count()==1);
        check("push 004 คันแรก -> top ต้องเป็น 004",stack.top() == 004);
        check("push 004 คันแรก -> isEmtry ต้องเป็น false",!stack.isEmpty());

        check("push 005 คันแรก -> count ต้องเป็น 5",stack.count()==1);
        check("push 005 คันแรก -> top ต้องเป็น 005",stack.top() == 005);
        check("push 005 คันแรก -> isEmtry ต้องเป็น false",stack.isFull());

        //กรณีที่Inputผิดเงื่อนไข ต้องโยน exception !!
        boolean threwZeroId = false ; 
        try{
            new BoundedStack(0);
        } catch(IllegalArgumentException e){
            threwZeroId = true ;   
        }
        check("push 0 ต้องโยน IllegalArgumentException", threwZeroId);

        boolean threwNegativeId = false ;
        try{
            new BoundedStack(-2);
        } catch(IllegalArgumentException e){
            threwNegativeId = true ;
        }
        check("push -2 ต้องโยน IllegalArgumentException", threwNegativeId);

        //กรณีลานจอรถเต็มแล้วแต่ฝืนจอดเพิ่ม
        boolean threwFull = false ; 
        try{
            stack.push(006); //ลานจอดมี 5 แต่พยายามจอด 6
        } catch(IllegalArgumentException e){
            threwFull = true ;
        }
        check("push ตอนลานจอดรถเต็ม -> ต้องโยนIllegalArgumentException", threwFull);
        check("push ตอนลานจอดรถไม่เต็ม -> จำนวนในลานจอดรถยังคงเป็นเท่าเดิม", stack.count()==5);



        }







}
