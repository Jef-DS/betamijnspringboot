package org.betavzw.mijnenveger.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BetamijnenvegerApplicationTests {

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


}

