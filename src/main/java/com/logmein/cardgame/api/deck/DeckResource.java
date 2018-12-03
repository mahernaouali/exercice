package com.logmein.cardgame.api.deck;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeckResource {
	
	@Autowired
	private DeckRepository deckRepository;
	
	/**
	 * 
	 *Gel list of all decks created 
	 * 
	 */
	@GetMapping("/api/decks")
	public List<Deck> retrieveAllDecks(){
		
		return deckRepository.findAll();
	}

	/**
	 * Create new Deck and initilize his cards for the first time , and them shuffle them 
	 * 
	 */
	@RequestMapping("/api/deck/new")
	public void addDeck() {
		Deck deck = new Deck();
		deck.initialize();
		deck.shuffle();
		deckRepository.save(deck);

	}
	
	/**
	 * Delete existing Deck , this operation can be done only if the deck is not related to one Game.  
	 * @param id
	 */
	@RequestMapping("/api/deck/delete/{id}")
	public void deleteGame(@PathVariable long id) {
		deckRepository.deleteById(id);
	}
}
