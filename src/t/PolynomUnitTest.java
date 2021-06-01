package t;

import com.company.Polynom;
import org.junit.Assert;
import org.junit.Test;

public class PolynomUnitTest {
    @Test
    public void JustBiasTest(){
        String poly = "29";
        Assert.assertEquals(poly, new Polynom(poly).toString());
    }

    @Test
    public void JustNegativeBiasTest(){
        String poly = "-20";
        Assert.assertEquals(poly, new Polynom(poly).toString());
    }

    @Test
    public void NoBiasTest(){
        String poly = "x";
        Assert.assertEquals(poly, new Polynom(poly).toString());
    }

    @Test
    public void NoBiasNegativeTest(){
        String poly = "-x";
        Assert.assertEquals(poly, new Polynom(poly).toString());
    }

    @Test
    public void FirstCoefTest(){
        String poly = "2x";
        Assert.assertEquals(poly, new Polynom(poly).toString());
    }

    @Test
    public void FirstCoefNegativeTest(){
        String poly = "-62x";
        Assert.assertEquals(poly, new Polynom(poly).toString());
    }

    @Test
    public void BiasFirstTest(){
        String poly = "15+x";
        Assert.assertEquals(poly, new Polynom(poly).toString());
    }

    @Test
    public void NegativeBiasFirstTest(){
        String poly = "-16+x";
        Assert.assertEquals(poly, new Polynom(poly).toString());
    }

    @Test
    public void NegativeBiasNegativeFirstTest(){
        String poly = "-56-x";
        Assert.assertEquals(poly, new Polynom(poly).toString());
    }

    @Test
    public void BiasNegativeFirstTest(){
        String poly = "17-x";
        Assert.assertEquals(poly, new Polynom(poly).toString());
    }

    @Test
    public void BiasNegativeFirstCoefTest(){
        String poly = "17-55x";
        Assert.assertEquals(poly, new Polynom(poly).toString());
    }

    @Test
    public void SecondTest(){
        String poly = "x^2";
        Assert.assertEquals(poly, new Polynom(poly).toString());
    }

    @Test
    public void NegativeSecondTest(){
        String poly = "-x^2";
        Assert.assertEquals(poly, new Polynom(poly).toString());
    }

    @Test
    public void FirstSecondTest(){
        String poly = "x-x^2";
        Assert.assertEquals(poly, new Polynom(poly).toString());
    }

    @Test
    public void FirstNegativeSecondTest(){
        String poly = "x-x^2";
        Assert.assertEquals(poly, new Polynom(poly).toString());
    }

    @Test
    public void BiasFirstSecondTest(){
        String poly = "22+x+x^2";
        Assert.assertEquals(poly, new Polynom(poly).toString());
    }

    @Test
    public void BiasFirstNegativeSecondTest(){
        String poly = "25+x-x^2";
        Assert.assertEquals(poly, new Polynom(poly).toString());
    }

    @Test
    public void BiasFirstNegativeSecondCoefTest(){
        String poly = "25+x-22x^2";
        Assert.assertEquals(poly, new Polynom(poly).toString());
    }

    @Test
    public void StressTest1(){
        String poly = "-x+x^2+x^5-x^7";
        Assert.assertEquals(poly, new Polynom(poly).toString());
    }

    @Test
    public void StressTest2(){
        String poly = "190-11x^2+123x^50-56x^70";
        Assert.assertEquals(poly, new Polynom(poly).toString());
    }

    @Test
    public void RationalCoef(){
        String poly = "3/10-25/2x^3+123/71x^50-56/57x^98";
        Assert.assertEquals(poly, new Polynom(poly).toString());
    }

    @Test
    public void RationalPower(){
        String poly = "3/10-25/2x^3/10+123/71x^50/31-56/57x^98/17";
        Assert.assertEquals(poly, new Polynom(poly).toString());
    }

    @Test
    public void RationalNegativePower(){
        String poly = "3/10-25/2x^-3/10+123/71x^50/31-56/57x^98/17";
        Assert.assertEquals(poly, new Polynom(poly).toString());
    }
}
