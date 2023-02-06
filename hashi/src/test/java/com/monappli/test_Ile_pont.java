package test.java.com.monappli;

import main.java.com.monappli.Ilot;
import main.java.com.monappli.Pont;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class test_Ile_pont{
	@Test
	public void creaPont(){
		Pont p= new Pont(new Ilot(10,5,3),new Ilot(9,6,4));
<<<<<<< HEAD
		assertTrue(Math.sqrt(2) == p.tailleTrait());
=======
    assertEquals(Math.sqrt(2), p.tailleTrait());
>>>>>>> bf08f116a16ad550b67e0bfac3ce20ed7f1fdad4
	}
}
