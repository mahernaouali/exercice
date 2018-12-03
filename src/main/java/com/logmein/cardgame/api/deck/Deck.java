package com.logmein.cardgame.api.deck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.logmein.cardgame.api.card.Card;

@Entity
public class Deck {

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany
	private List<Card> cards;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	// Initialize the Deck by creating all the cards for the first time.
	public void initialize() {

		List<Card> cards = new ArrayList<Card>();

		// Create the cards from 1 to 10 in the different suits.The cards having 10
		Arrays.asList("S", "H", "D", "C").forEach(element -> {

			for (int i = 1; i <= 10; i++) {
				Card card = new Card(i + element, "" + i + "", element, i, i + element + ".png");
				cards.add(card);
			}

			cards.add(new Card("J" + element, "J", element, 11, "J" + element + ".png")); // creating the Jack cards
			cards.add(new Card("Q" + element, "Q", element, 11, "Q" + element + ".png")); // creating the Queen cards
			cards.add(new Card("K" + element, "K", element, 11, "K" + element + ".png")); // creating the King cards
		}

		);

		this.cards = cards;

	}

	// Method to shuffle cards in a Random order without using the provided Collections.shuffle(list) 
	public void shuffle() {
		int n = this.cards.size();
		Random random = new Random();
		random.nextInt();
		for (int i = 0; i < n; i++) {
			int change = i + random.nextInt(n - i);
			swapCards(this.cards, i, change);
		}
	}

	private static void swapCards(List<Card> cards, int i, int change) {
		Card helper = cards.get(i);
		cards.set(i, cards.get(change));
		cards.set(change, helper);
	}

}
