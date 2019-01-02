package org.betavzw.mijnenveger.engine;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BetamijnenvegerApplicationTests {

	@Test
	public void testMaakGewoonVakje() {
		Vakje  vakje = Vakje.MaakVakje(null, false);
		boolean ontploft = vakje.klik();
		assertEquals(false, ontploft);
	}

}

