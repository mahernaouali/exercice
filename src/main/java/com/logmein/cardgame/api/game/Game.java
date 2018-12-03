package com.logmein.cardgame.api.game;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.logmein.cardgame.api.deck.Deck;
import com.logmein.cardgame.api.player.Player;

@Entity
public class Game {

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany
	private List<Player> players;
	
	@OneToMany
	private List<Deck> decks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Deck> getDecks() {
		return decks;
	}

	public void setDecks(List<Deck> decks) {
		this.decks = decks;
	}
	
	

}
