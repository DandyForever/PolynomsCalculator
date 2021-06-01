package t;

import com.company.Rational;
import org.junit.Assert;
import org.junit.Test;

public class RationalUnitTest {
    @Test
    public void ConstructorTest1(){
        Rational r = new Rational(1, 4);
        Assert.assertEquals("1/4", r.toString());
    }

    @Test
    public void ConstructorTest2(){
        Rational r = new Rational(2, 4);
        Assert.assertEquals("1/2", r.toString());
    }

    @Test
    public void ConstructorStringTest1(){
        Rational r = new Rational("1/4");
        Assert.assertEquals("1/4", r.toString());
    }

    @Test
    public void ConstructorStringTest2(){
        Rational r = new Rational("25");
        Assert.assertEquals("25", r.toString());
    }

    @Test
    public void ConstructZeroTest1(){
        Rational r = new Rational(1, 0);
        Assert.assertEquals("0/0", r.toString());
    }

    @Test
    public void ConstructZeroTest2(){
        Rational r = new Rational(0, 0);
        Assert.assertEquals("0/0", r.toString());
    }

    @Test
    public void SimplifyTest1(){
        Rational r = new Rational(5, 5);
        Assert.assertEquals("1", r.toString());
    }

    @Test
    public void SimplifyTest2(){
        Rational r = new Rational(20, 5);
        Assert.assertEquals("4", r.toString());
    }

    @Test
    public void SimplifyTest3(){
        Rational r = new Rational(5, 20);
        Assert.assertEquals("1/4", r.toString());
    }

    @Test
    public void AddTest1(){
        Rational r = new Rational(1, 20);
        r.add(4);
        Assert.assertEquals("81/20", r.toString());
    }

    @Test
    public void AddTest2(){
        Rational r = new Rational(5, 1);
        r.add(4);
        Assert.assertEquals("9", r.toString());
    }

    @Test
    public void SubTest1(){
        Rational r = new Rational(46, 15);
        r.sub(3);
        Assert.assertEquals("1/15", r.toString());
    }

    @Test
    public void SubTest2(){
        Rational r = new Rational(9, 1);
        r.sub(3);
        Assert.assertEquals("6", r.toString());
    }

    @Test
    public void MulTest1(){
        Rational r = new Rational(1, 9);
        r.mul(new Rational(3, 1));
        Assert.assertEquals("1/3", r.toString());
    }

    @Test
    public void MulTest2(){
        Rational r = new Rational(5, 9);
        r.mul(new Rational(9, 1));
        Assert.assertEquals("5", r.toString());
    }

    @Test
    public void DivTest1(){
        Rational r = new Rational(5, 1);
        r.div(new Rational(9, 1));
        Assert.assertEquals("5/9", r.toString());
    }

    @Test
    public void DivTest2(){
        Rational r = new Rational(15, 1);
        r.div(new Rational(5, 1));
        Assert.assertEquals("3", r.toString());
    }
}