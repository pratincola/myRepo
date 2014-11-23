import junit.framework.TestCase;
import org.junit.Test;

public class RationalTest extends TestCase {

    protected Rational HALF;

    protected void setUp() {
        HALF = new Rational( 1, 2 );
    }

    // Create new test
    public RationalTest (String name) {
        super(name);
    }

    public void testEquality() {
        assertEquals(new Rational(1,3), new Rational(1,3));
        assertEquals(new Rational(1,3), new Rational(2,6));
        assertEquals(new Rational(3,3), new Rational(1,1));
    }

    // Test for nonequality
    public void testNonEquality() {
        assertFalse(new Rational(2,3).equals(
                new Rational(1,3)));
        assertFalse(new Rational(2,3).equals(null));
        assertFalse(new Rational(4,1).equals(new Integer(4)));
    }

    public void testAccessors() {
        assertEquals(new Rational(2,3).numerator(), 2);
        assertEquals(new Rational(2,3).denominator(), 3);
    }

    public void testEquals(){
        assertTrue(new Rational(5, 10).equals(new Rational(15,30)));
        assertTrue(new Rational(0, 1).equals(new Rational(0,1)));
        assertTrue(new Rational(46341,1).equals(new Rational(46341,1)));
        assertTrue(new Rational(-46341,-1).equals(new Rational(46341,1)));
        assertFalse(new Rational(-46341, 1).equals(null));
    }


    public void testMultiply(){
        Rational a = null;
        a = new Rational(0,1).times(new Rational(15,30));
        assertTrue(a.equals(new Rational(0,1)));
    }

    public void testDivide(){
        Rational a = null;
        a = new Rational(5,10).divides(new Rational(15, 30));
        assertTrue(a.equals(new Rational(1,1)));
    }

    public void testSetTolerance(){
        Rational a = new Rational(3,10);
        Rational.setTolerance(a);
        Rational b = Rational.getTolerance();
        assertTrue(a.equals(b));
    }

    @Test(expected = IllegalArgumentToSquareRootException.class)
    public void testIllegalArgument(){
        Rational high = new Rational(46349,1);
    }

    public void testAbs(){
        assertTrue(new Rational(-449,3).abs().equals(new Rational(449,3)));
        assertTrue(new Rational(449,-3).abs().equals(new Rational(449,3)));
        assertTrue(new Rational(-449,-3).abs().equals(new Rational(449,3)));
    }

    public void testMain() {
        String [] args = {"test"};
        new Rational(1,1).main(args);
    }

    public void testPlusLCM() {
        assertTrue(new Rational(10, 10).plus(new Rational(15, 30)).equals(new Rational(45, 30)));
    }

    public void testPlusNegative() {
        assertTrue(new Rational(-3, 2).plus(new Rational(6, 4))
                .equals(new Rational(0, 0)));
    }

    public void testPlusPositive() {
        assertTrue(new Rational(3, 2).plus(new Rational(6, 4))
                .equals(new Rational(3, 2)));
    }

    @Test(expected = NullPointerException.class)
    public void testPlus(){
        Rational a = null;
        try{
             a = new Rational(0,0).plus(new Rational(15,30));
        } catch(ArithmeticException e){
            e.printStackTrace();
        }
        assertTrue(a.equals(new Rational(1, 2)));
    }

    public void testRoot() {
        Rational s = new Rational( 1, 4 );
        Rational sRoot = null;
        try {
            sRoot = s.root();
        } catch (IllegalArgumentToSquareRootException e) {
            e.printStackTrace();
        }
        assertTrue( sRoot.isLessThan( HALF.plus( Rational.getTolerance() ) )
                && HALF.minus( Rational.getTolerance() ).isLessThan( sRoot ) );
    }

    public static void main(String args[]) {
        String[] testCaseName =
                { RationalTest.class.getName() };
        // junit.swingui.TestRunner.main(testCaseName);
        junit.textui.TestRunner.main(testCaseName);
    }
}
