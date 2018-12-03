package com.logmein.cardgame.api.deck;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeckResourceTests {

	@Autowired
	private DeckRepository deckRepository;
	
	@Test
    public void whenFindById_thenReturnDeck() {
    	Deck deck= new Deck();
    	deckRepository.save(deck);
    	deckRepository.flush();
    	
    	Deck found = deckRepository.findAll().get(0);
    	
    	assertEquals(found.getId(),deck.getId());
    }
}
