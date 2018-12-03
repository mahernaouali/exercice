package com.logmein.cardgame.api.player;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.logmein.cardgame.api.card.Card;

@Entity
public class Player implements Comparable<Player> {

	@Id
	@GeneratedValue
	private Long id;
	private String pseudo;
	private int cardValueCount;

	@OneToMany
	private List<Card> cards;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public int getCardValueCount() {
		int count = 0;
		for (Card card : this.cards) {
			count = count + card.getValue();
		}

		return count;
	}

	public void setCardValueCount(int cardValueCount) {
		this.cardValueCount = cardValueCount;
	}

	@Override
	public int compareTo(Player arg0) {
		if (this.cardValueCount > arg0.cardValueCount)
			return 1;
		if (this.cardValueCount < arg0.cardValueCount)
			return -1;
		else
			return 0;
	}

}
