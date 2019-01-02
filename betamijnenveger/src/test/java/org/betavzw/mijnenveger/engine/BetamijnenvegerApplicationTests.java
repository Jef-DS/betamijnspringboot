package org.betavzw.mijnenveger.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
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
	public void testBomVakjeKlik() {
		Vakje vakje = Vakje.MaakVakje(null, true);
		vakje.klik();
		assertEquals(VeldToestandEnum.mijn, vakje.getToestand());
	}
	@Test
	public void testBomVakjeNietGemarkeerdKlik() {
		Vakje vakje = Vakje.MaakVakje(null, true);
		vakje.markeer();
		expectedEx.expect(IllegalArgumentException.class);
		vakje.klik();
	}
	@Test
	public void testAantalBomBuren() {
		Vakje vakje = new BordVakje();
		vakje.setBuren(List.of(
				     new BordVakje(),
				     new BomVakje(),
				     new BordVakje(),
				     new BomVakje(),
				     new BomVakje()
				));
		vakje.klik();
		assertEquals(VeldToestandEnum.drieburen, vakje.getToestand());
	}
	@Test
	public void testGeenKlikBijMarkeren() {
		Vakje vakje = new BordVakje();
		vakje.markeer();
		expectedEx.expect(IllegalArgumentException.class);
		vakje.klik();
	}
	@Test
	public void testToonBordVakje() {
		Vakje vakje = new BordVakje();
		vakje.setBuren(List.of(
			     new BordVakje(),
			     new BomVakje(),
			     new BordVakje(),
			     new BomVakje(),
			     new BomVakje()
			));
		vakje.toon();
		assertEquals(VeldToestandEnum.drieburen, vakje.getToestand());
	}
	@Test
	public void testToonBomVakje() {
		Vakje vakjezonderBomBuren = new BordVakje();
		Vakje vakjemetBomBuren = new BordVakje();
		vakjemetBomBuren.setBuren(List.of(
				vakjezonderBomBuren,
				new BomVakje(),
				new BomVakje()
				));
		vakjezonderBomBuren.setBuren(List.of(
				vakjemetBomBuren,
				new BordVakje(),
				new BordVakje()
				));
		vakjezonderBomBuren.klik();
		assertEquals(VeldToestandEnum.tweeburen, vakjemetBomBuren.getToestand());
	}
	@Test
	public void testVeldZonderBomBuren() {
		Vakje vakje = new BordVakje();
		
	}
	@Test
	public void testVeldGeneratorTeGroteRij() {
		int aantalrijen = 2;
		int aantalkolommen = 2;
		Set<Positie> posities = new HashSet<Positie>();
		VeldGenerator generator = new VasteVeldGenerator(aantalrijen, aantalkolommen, posities);
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("Rij 2 is groter dan 2");
		generator.isBom(2, 1);
	}
	@Test
	public void testVeldGeneratorTeGroteKolom() {
		int aantalrijen = 2;
		int aantalkolommen = 2;
		Set<Positie> posities = new HashSet<Positie>();
		VeldGenerator generator = new VasteVeldGenerator(aantalrijen, aantalkolommen, posities);
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("Kolom 2 is groter dan 2");
		generator.isBom(1, 2);
	}
	@Test
	public void testVeldGeneratorBevatBom() {
		int aantalrijen = 2;
		int aantalkolommen = 2;
		Set<Positie> posities = Set.of(
			new Positie(0, 0)
		);
		VeldGenerator generator = new VasteVeldGenerator(aantalrijen, aantalkolommen, posities);
		boolean isBom = generator.isBom(0, 0);
		assertTrue("test op bevat bom mislukt", isBom);
		boolean isGeenBom = generator.isBom(1, 1);
		assertFalse("test op bevat geen bom mislukt", isGeenBom);
	}
	@Test
	public void testBordMetRijenEnKolommen() {
		int aantalrijen = 3;
		int aantalkolommen = 3;
		Set<Positie> posities = Set.of(
				new Positie(0, 0)
			);
		VeldGenerator generator = new VasteVeldGenerator(aantalrijen, aantalkolommen, posities);
		Bord bord = new BordImpl(generator);
		assertEquals(aantalrijen, bord.getAantalRijen());
		assertEquals(aantalkolommen, bord.getAantalKolommen());
	}
	@Test
	public void testBordJuisteBommen() {
		int aantalRijen = 3;
		int aantalKolommen = 3;
		Set<Positie> posities = Set.of(
				new Positie(0, 1)
				);
		VeldGenerator generator = new VasteVeldGenerator(aantalRijen, aantalKolommen, posities);
		Bord bord = new BordImpl(generator);
		bord.klikVakje(0, 1);
		VeldToestandEnum toestand = bord.getVeldToestand(0, 1);
		assertEquals(VeldToestandEnum.mijn, toestand);
	}
	@Test
	public void testBordJuisteBuren() {
		int aantalRijen = 3;
		int aantalKolommen = 3;
		Set<Positie> posities = Set.of(
				new Positie(0, 1), new Positie(1,1), new Positie(1,0)
				);
		VeldGenerator generator = new VasteVeldGenerator(aantalRijen, aantalKolommen, posities);
		Bord bord = new BordImpl(generator);
		bord.klikVakje(0, 0);
		VeldToestandEnum toestand = bord.getVeldToestand(0, 0);
		assertEquals(VeldToestandEnum.drieburen, toestand);
	}
	@Test
	public void testBordGestart() {
		int aantalRijen = 3;
		int aantalKolommen = 3;
		Set<Positie> posities = Set.of(
				new Positie(0, 1), new Positie(1,1), new Positie(1,0)
				);
		VeldGenerator generator = new VasteVeldGenerator(aantalRijen, aantalKolommen, posities);
		Bord bord = new BordImpl(generator);	

		assertEquals(BordToestandEnum.gestart, bord.getToestand());
	}
	@Test
	public void testBordVerloren() {
		int aantalRijen = 3;
		int aantalKolommen = 3;
		Set<Positie> posities = Set.of(
				new Positie(0, 1), new Positie(1,1), new Positie(1,0)
				);
		VeldGenerator generator = new VasteVeldGenerator(aantalRijen, aantalKolommen, posities);
		Bord bord = new BordImpl(generator);	
		bord.klikVakje(1, 1);
		assertEquals(BordToestandEnum.verloren, bord.getToestand());
	}
	@Test
	public void testBordTonenNaVerloren() {
		int aantalRijen = 3;
		int aantalKolommen = 3;
		Set<Positie> posities = Set.of(
				new Positie(0, 1), new Positie(1,1), new Positie(1,0)
				);
		VeldGenerator generator = new VasteVeldGenerator(aantalRijen, aantalKolommen, posities);
		Bord bord = new BordImpl(generator);	
		bord.klikVakje(1, 1);
		assertEquals(VeldToestandEnum.mijn, bord.getVeldToestand(0,1));
		assertEquals(VeldToestandEnum.drieburen, bord.getVeldToestand(0, 0));
	}
	@Test
	public void testBordGewonnen() {
		int aantalRijen = 3;
		int aantalKolommen = 3;
		Set<Positie> posities = Set.of(
				new Positie(0, 2), new Positie(1,2)
				);
		VeldGenerator generator = new VasteVeldGenerator(aantalRijen, aantalKolommen, posities);
		Bord bord = new BordImpl(generator);	
		bord.klikVakje(0, 0);
		bord.klikVakje(2, 2);
		assertEquals(BordToestandEnum.gewonnen, bord.getToestand());
	}
}

