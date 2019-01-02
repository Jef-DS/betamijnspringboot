package org.betavzw.mijnenveger.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BetamijnenvegerApplicationTests {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void testBeginToestandVeld() {
		Vakje vakje = Vakje.MaakVakje(null, false);
		assertEquals(VeldToestandEnum.start, vakje.getToestand());
	}
	@Test
	public void testMaakGewoonVakje() {
		Vakje  vakje = Vakje.MaakVakje(null, false);
		boolean ontploft = vakje.klik();
		assertFalse(ontploft);
	}
	@Test
	public void testMaakBomVakje() {
		Vakje vakje = Vakje.MaakVakje(null, true);
		boolean ontploft = vakje.klik();
		assertTrue(ontploft);
	}
	@Test
	public void testMarkeerVakje() {
		Vakje vakje = Vakje.MaakVakje(null, false);
		vakje.markeer();
		assertEquals(VeldToestandEnum.gemarkeerd, vakje.getToestand());
	}
	@Test
	public void testOnmarkeerVakje() {
		Vakje vakje = Vakje.MaakVakje(null, false);
		vakje.markeer();
		vakje.markeer();
		assertEquals(VeldToestandEnum.start, vakje.getToestand());
	}
	@Test
	public void testVeldGeneratorTeGroteRij() {
		int aantalrijen = 2;
		int aantalkolommen = 2;
		Set<Positie> posities = new HashSet<Positie>();
		VeldGenerator generator = new VasteVeldGenerator(aantalrijen, aantalkolommen, posities);
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("Rij 2 is groter dan 2");
		generator.isBom(2, 2);
	}


}

