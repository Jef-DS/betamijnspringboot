package org.betavzw.mijnenveger.engine;

import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
	
	
}
