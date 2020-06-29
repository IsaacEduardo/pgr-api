package ao.isaac.hossi.pgr.util;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//Class geradora de sku automaticas
@Component
public class SkuUtility {
	private static final String SALT = "salt";// salt should be protected carefully	
		
	@Bean
	public static String randomSku() {
//Carateres a gerar a senha
		String SALTCHARS = "ABCEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();

		while (salt.length() < 6) {
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));

		}
		String saltStr = salt.toString();
		return saltStr;
	}
}
