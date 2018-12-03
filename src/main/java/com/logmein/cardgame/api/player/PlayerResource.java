package com.logmein.cardgame.api.player;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logmein.cardgame.api.card.Card;

@RestController
public class PlayerResource {

	@Autowired
	private PlayerRepository playerRepository;

	/**
	 * 
	 * Returns the list of all players
	 * @return
	 */
	@GetMapping("/api/players")
	public List<Player> retrieveAllPlayers() {

		return playerRepository.findAll();
	}

	/**
	 * 
	 * Create a new player , I added the pseudo as entry
	 * @param pseudo
	 */
	@RequestMapping("/api/player/new/{pseudo}")
	public void addPlayer(@PathVariable String pseudo) {
		Player player = new Player();
		player.setPseudo(pseudo);
		playerRepository.save(player);

	}

	/**
	 * 
	 * Retuns the list of cards of a specified player
	 * @param idPlayer
	 * @return
	 */
	@RequestMapping("/api/player/listCards/{idPlayer}")
	public List<Card> listOfCards(@PathVariable long idPlayer) {
		if (playerRepository.findById(idPlayer).isPresent())
			return playerRepository.findById(idPlayer).get().getCards();
		else
			throw new PlayerNotFoundException("Player : " + idPlayer + " Not Found !");
	}

}
