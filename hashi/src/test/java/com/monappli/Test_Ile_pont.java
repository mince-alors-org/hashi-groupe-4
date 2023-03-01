package com.monappli;

import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;


/**
 * Test pour Ilot et Pont
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_Ile_pont {
	private Ilot ilot;
	private Pont p;
	@BeforeEach
	public void init(TestInfo testInfo){
		this.ilot = new Ilot(5,6,6);
		this.p = new Pont(new Ilot(10,5,3),new Ilot(9,6,4));
		System.out.println("Lancement du Test " + testInfo.getTestClass().get().getSimpleName());
	}
	@AfterEach
	public void afficheOK(TestInfo testInfo){
		this.ilot=null;
		this.p=null;
		System.out.println("Test Ok "+testInfo.getTestClass().get().getName());
		Assertions.assertTrue(true);
	}

	/**
	 *
	 */
	@Test
	public void taillePont(){
		Assertions.assertEquals(Math.sqrt(2), p.tailleTrait(), "Erronnée : "+Math.sqrt(2)+" != "+ p.tailleTrait());
	}

	/**
	 *
	 */
	@Test
	public void test_addPont(){
		this.ilot.addPont(new Pont(new Ilot(8,6,7),new Ilot(5,6,7)));
		this.ilot.addPont(new Pont(new Ilot(8,6,7),new Ilot(5,6,7)));
		Assertions.assertEquals(this.ilot.getPonts().size(),2,"Erreur il n'y a pas deux pont il y a "+this.ilot.getPonts().size()+" pont");
	}

	/**
	 *
	 */
	@Test
	public void voisin(){
		Ilot ile = new Ilot(16,2,5);
		Pont pTest= new Pont(this.ilot,ile);
		Assertions.assertEquals(this.ilot,pTest.voisin(ile),"Erreur Ile retourner n'est pas la bonne");
	}

	/**
	 *
	 */
	@Test
	public void listeVois(){
		Pont p1 = new Pont (this.ilot,new Ilot(45,3,8));
		Pont p2 = new Pont (this.ilot,new Ilot(65,8,3));
		Pont p3 = new Pont (this.ilot,new Ilot(4,10,2));
		Pont p4 = new Pont (this.ilot,new Ilot(5,60,1));
		Assertions.assertEquals(4,this.ilot.listeVoisin().size(),"Erreur l'ile n'a pas 4 voisin elle en a "+this.ilot.listeVoisin().size());
	}

	/**
	 * Vérifie le renvoi de ArrayIndexOutOfBoundsException en cas de suppression d'un pont
	 */
	@Test
	public void deletePontIlot(){

		 ArrayIndexOutOfBoundsException exception = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, ()->{
			 this.ilot.deletePont(new Pont(new Ilot(7,8,9), new Ilot(6,7,8)));

		});
		Assertions.assertEquals("Index out of bounds", exception.getMessage());


	}






}
