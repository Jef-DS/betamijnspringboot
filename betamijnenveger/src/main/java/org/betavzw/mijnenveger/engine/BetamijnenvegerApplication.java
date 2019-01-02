package org.betavzw.mijnenveger.engine;

import java.util.Scanner;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class BetamijnenvegerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BetamijnenvegerApplication.class, args);
	}

	private Bord bord;

	
	public BetamijnenvegerApplication(Bord bord) {
		this.bord = bord;
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		while (bord.getToestand() == BordToestandEnum.gestart) {
			toonBord();
			System.out.print("Geef rij: ");
			int rij = Integer.parseInt(scanner.nextLine());
			System.out.print("Geef kolom: ");
			int kolom = Integer.parseInt(scanner.nextLine());
			bord.klikVakje(rij, kolom);
		}
		toonBord();
		if (bord.getToestand() == BordToestandEnum.gewonnen) {
			System.out.println("GEWONNEN");
		}else {
			System.out.println("VERLOREN");
		}

	}

	private void toonBord() {
		System.out.print("  ");
		for (int kolom = 0; kolom < bord.getAantalKolommen(); kolom++) {
			System.out.printf("%2d", kolom);
		}
		System.out.println();
		for (int rij = 0; rij < bord.getAantalRijen(); rij++) {
			System.out.printf("%2d", rij);
			for (int kolom = 0; kolom < bord.getAantalKolommen(); kolom++) {
				VeldToestandEnum toestand = bord.getVeldToestand(rij, kolom);
				switch (toestand) {
				case achtburen:
					System.out.print(" 8");
					break;
				case drieburen:
					System.out.print(" 3");
					break;
				case eenbuur:
					System.out.print(" 1");
					break;
				case gemarkeerd:
					System.out.print(" M");
					break;
				case mijn:
					System.out.print(" B");
					break;
				case nulburen:
					System.out.print("  ");
					break;
				case start:
					System.out.print("..");
					break;
				case tweeburen:
					System.out.print(" 2");
					break;
				case vierburen:
					System.out.print(" 4");
					break;
				case vijfburen:
					System.out.print(" 5");
					break;
				case zesburen:
					System.out.print(" 6");
					break;
				case zevenburen:
					System.out.print(" 7");
					break;
				default:
					break;

				}
			}
			System.out.println();
		}
	}

}
