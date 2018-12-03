package com.logmein.cardgame.api;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.logmein.cardgame.api.card.Card;
import com.logmein.cardgame.api.card.CardRepository;

@SpringBootApplication
public class CardgameApplication implements ApplicationRunner {

	@Autowired
	private CardRepository cardRepository;

	public static void main(String[] args) {
		SpringApplication.run(CardgameApplication.class, args);
	}

	/**
	 *  This part is used to create the cards for the first time.
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception

	{

		Arrays.asList("S", "H", "D", "C").forEach(element -> {

			for (int i = 1; i <= 10; i++) {
				Card card = new Card(i + element, "" + i + "", element, i, i + element + ".png");
				cardRepository.save(card);
			}

			cardRepository.save(new Card("J" + element, "J", element, 11, "J" + element + ".png")); // creating the Jack
																									// cards
			cardRepository.save(new Card("Q" + element, "Q", element, 11, "Q" + element + ".png")); // creating the
																									// Queen cards
			cardRepository.save(new Card("K" + element, "K", element, 11, "K" + element + ".png")); // creating the King
																									// cards
		}

		);
	}

}
