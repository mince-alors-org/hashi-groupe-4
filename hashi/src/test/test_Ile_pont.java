import org.junit.Test;
import static org.junit.Assert.*;

public class test_Ile_pont{
	@Test
	public void creaPont(){
		Pont p= new Pont(new Ilot(10,5,3),new Ilot(9,6,4));
	}
}
