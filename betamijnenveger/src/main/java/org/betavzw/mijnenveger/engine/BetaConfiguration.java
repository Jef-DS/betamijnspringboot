package org.betavzw.mijnenveger.engine;

import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BetaConfiguration {
	@Bean
	public VeldGenerator getGenerator() {
		return new VasteVeldGenerator(10, 10, Set.of(
				new Positie(1, 1),
				new Positie(4,4),
				new Positie(4,5)
				));
	}
	@Bean
	@Primary
	public VeldGenerator getRandomVeldGenerator() {
		return new RandomVeldGenerator(10, 10, 10);
	}
	
	
}
