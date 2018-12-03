package com.logmein.cardgame.api.card;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardResource {
	
	@Autowired
	private CardRepository cardRepository;
	
	@GetMapping("/api/cards")
	public List<Card> retrieveAllCards(){
		return cardRepository.findAll();
	}
	
		
	public void createCards () {
		Arrays.asList("S", "H", "D", "C").forEach(element -> {

			for (int i = 1; i <= 10; i++) {
				Card card = new Card(i + element, "" + i + "", element, i, i + element + ".png");
				cardRepository.save(card);
			}
			
			cardRepository.save(new Card("J"+element,"J",element,11,"J"+ element + ".png")); //creating the Jack cards 
			cardRepository.save(new Card("Q"+element,"Q",element,11,"Q"+ element + ".png")); //creating the Queen cards
			cardRepository.save(new Card("K"+element,"K",element,11,"K"+ element + ".png")); //creating the King cards
		}

		);
	}

}
 