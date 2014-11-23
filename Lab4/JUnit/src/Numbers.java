import java.math.BigDecimal;

public class Numbers {

    static void testBigDecimal() {
        BigDecimal n1p1 = new BigDecimal( 1.1 );
        BigDecimal n2p1 = new BigDecimal( 2.1 );
        System.out.println("testBigDecimal:\t 2.1 - 1.1 = " + (n2p1.subtract( n1p1 ) ) );
        if ( n2p1.subtract(n1p1).abs().compareTo( new BigDecimal( 1 ) ) > -1 ) {
            System.out.println("testBigDecimal:\t |2.1 - 1.1| >= 1.0");
        } else {
            System.out.println("testBigDecimal:\t |2.1 - 1.1| < 1.0");
        }
    }

    static void testDouble1() {
        double n1p1 = 1.1;
        double n2p1 = 2.1;
        System.out.println("testDouble1:\t 2.1 - 1.1 = " + (n2p1 - n1p1) );
        if ( Math.abs( n2p1 - n1p1 ) >= 1.0 ) {
            System.out.println("testDouble1:\t |2.1 - 1.1| >= 1.0");
        } else {
            System.out.println("testDouble1:\t |2.1 - 1.1| < 1.0");
        }
    }

    static void testDouble2() {
        double n1p1 = 1.1;
        double n2p0 = 2.0;
        System.out.println("testDouble2:\t 2.0 - 1.1 = " + (n2p0 - n1p1) );
        if ( Math.abs( n2p0 - n1p1 ) >= 0.9 ) {
            System.out.println("testDouble2:\t |2.0 - 1.1| >= 0.9");
        } else {
            System.out.println("testDouble2:\t |2.0 - 1.1| < 0.9");
        }
    }

    static void testFloat() {
        float n1p1 = 1.1f;
        float n2p1 = 2.1f;
        System.out.println("testFloat:\t 2.1 - 1.1 = " + (n2p1 - n1p1) );
        if ( Math.abs( n2p1 - n1p1 ) >= 1.0 ) {
            System.out.println("testFloat:\t |2.1 - 1.1| >= 1.0");
        } else {
            System.out.println("testFloat:\t |2.1 - 1.1| < 1.0");
        }
    }

    public static void main( String [] args ) {
        System.out.println("Main:\t\t 2.00 - 1.10 = " + (2.0 - 1.1) );
        testFloat();
        testDouble1();
        testDouble2();
        testBigDecimal();
    }
}
