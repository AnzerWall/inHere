package util;

/**
 *
 */
public class T2 extends T1 {

    int a = 12;

    public static void main(String[] args){
        T1 t1 = new T2();
        t1.test();
        T2 t3 = (T2)t1;
        System.out.print(t3.a);
    }
}
