package com.logmein.cardgame.api.game;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logmein.cardgame.api.card.Card;
import com.logmein.cardgame.api.deck.Deck;
import com.logmein.cardgame.api.deck.DeckNotFoundException;
import com.logmein.cardgame.api.deck.DeckRepository;
import com.logmein.cardgame.api.player.Player;
import com.logmein.cardgame.api.player.PlayerNotFoundException;
import com.logmein.cardgame.api.player.PlayerRepository;

@RestController
public class GameResource {

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private DeckRepository deckRepository;

	@GetMapping("/api/games")
	public List<Game> retrieveAllGames() {

		return gameRepository.findAll();
	}

	/**
	 * 
	 * Create a new Game
	 */
	@RequestMapping("/api/game/new")
	public void addGame() {
		gameRepository.save(new Game());

	}

	/**
	 * 
	 * Delete and existing game
	 */
	@RequestMapping("/api/game/delete/{id}")
	public void deleteGame(@PathVariable long id) {
		gameRepository.deleteById(id);
	}

	/**
	 * Add a given player to a given game
	 * 
	 * @param idGame
	 * @param idPlayer
	 */
	@RequestMapping("/api/game/{idGame}/addPlayer/{idPlayer}")
	public void addPlayer(@PathVariable long idGame, @PathVariable long idPlayer) {

		Optional<Game> game = gameRepository.findById(idGame);
		Optional<Player> player = playerRepository.findById(idPlayer);
		if (game.isPresent()) {

			if (player.isPresent()) {

				game.get().getPlayers().add(player.get());
				gameRepository.save(game.get());

			} else {
				throw new PlayerNotFoundException("Player : " + idPlayer + " Not Found !");
			}

		} else {

			throw new GameNotFoundException("Game : " + idGame + " Not Found !");

		}

	}

	/**
	 * Removing one given player from one given Game.
	 * 
	 * @param idGame
	 * @param idPlayer
	 */
	@RequestMapping("/api/game/{idGame}/removePlayer/{idPlayer}")
	public void removePlayer(@PathVariable long idGame, @PathVariable long idPlayer) {

		Optional<Game> game = gameRepository.findById(idGame);
		Optional<Player> player = playerRepository.findById(idPlayer);
		if (game.isPresent()) {

			if (player.isPresent()) {

				game.get().getPlayers().remove(player.get());
				gameRepository.save(game.get());

			} else {
				throw new PlayerNotFoundException("Player : " + idPlayer + " Not Found !");
			}

		} else {

			throw new GameNotFoundException("Game : " + idGame + " Not Found !");

		}

	}

	/**
	 * Add one given Deck to a given Game
	 * 
	 * @param idGame
	 * @param idDeck
	 */
	@RequestMapping("/api/game/{idGame}/addDeck/{idDeck}")
	public void addDeck(@PathVariable long idGame, @PathVariable long idDeck) {

		Optional<Game> game = gameRepository.findById(idGame);
		Optional<Deck> deck = deckRepository.findById(idDeck);
		if (game.isPresent()) {

			if (deck.isPresent()) {

				game.get().getDecks().add(deck.get());
				gameRepository.save(game.get());

			} else {
				throw new DeckNotFoundException("Deck : " + idDeck + " Not Found !");
			}

		} else {

			throw new GameNotFoundException("Game : " + idGame + " Not Found !");

		}

	}

	/**
	 * Deal a card from a specified Deck of one game to a specified Player.The
	 * dealed cards will be transfered from the Deck to the player
	 * 
	 * @param idGame
	 * @param idDeck
	 * @param idPlayer
	 */

	@RequestMapping("/api/game/dealCard/{idGame}/deck/{idDeck}/player/{idPlayer}")
	public void dealCards(@PathVariable long idGame, @PathVariable long idDeck, @PathVariable long idPlayer) {

		Optional<Game> game = gameRepository.findById(idGame);
		Optional<Deck> deck = deckRepository.findById(idDeck);
		Optional<Player> player = playerRepository.findById(idPlayer);

		if (game.isPresent()) {

			if (game.get().getDecks().contains(deck.get())) {

				if (game.get().getPlayers().contains(player.get())) {

					player.get().getCards().add(deck.get().getCards().get(0));
					deck.get().getCards().remove(0);
					deckRepository.save(deck.get());
					playerRepository.save(player.get());
					gameRepository.save(game.get());

				} else {
					throw new PlayerNotFoundException("Player : " + idPlayer + " Not Found !");
				}

			} else {

				throw new DeckNotFoundException("Deck : " + idDeck + " Not Found !");
			}

		} else {

			throw new GameNotFoundException("Game : " + idGame + " Not Found !");

		}

	}

	/**
	 * 
	 * This Operation results in shuffling the remaing cards in a choosen deck of a
	 * specified game
	 * 
	 * @param idGame
	 * @param idDeck
	 */
	@RequestMapping("/api/game/shuffle/{idGame}/deck/{idDeck}")
	public void shuffle(@PathVariable long idGame, @PathVariable long idDeck) {

		Optional<Game> game = gameRepository.findById(idGame);
		Optional<Deck> deck = deckRepository.findById(idDeck);
		if (game.isPresent()) {

			if (deck.isPresent()) {

				deck.get().shuffle();
				deckRepository.save(deck.get());
				gameRepository.save(game.get());

			} else {
				throw new DeckNotFoundException("Deck : " + idDeck + " Not Found !");
			}

		} else {

			throw new GameNotFoundException("Game : " + idGame + " Not Found !");

		}

	}

	/**
	 * Return the list of player in descending order of CardValueCount wich is the
	 * total of card value in player hand
	 * 
	 * @param idGame
	 * @return
	 */
	@RequestMapping("/api/game/listPlayerDesc/{idGame}")
	public List<Player> getSortedDesc(@PathVariable long idGame) {

		Optional<Game> game = gameRepository.findById(idGame);

		if (game.isPresent()) {

			List<Player> players = game.get().getPlayers();
			Collections.sort(players);
			return players;

		} else {

			throw new GameNotFoundException("Game : " + idGame + " Not Found !");

		}

	}

	/**
	 * Returns the list of remaining cards grouped by suit and ordered by values
	 * @param idGame
	 * @param idDeck
	 * @return
	 */
	@RequestMapping("/api/game/remainingCards/{idGame}/{idDeck}")
	public Map<String,Map<Object,List<Card>>> getRemainingCards(@PathVariable long idGame, @PathVariable long idDeck) {

		Optional<Game> game = gameRepository.findById(idGame);
		Optional<Deck> deck = deckRepository.findById(idDeck);

		if (game.isPresent()) {
			if (deck.isPresent()) {
				List<Card> cards = deck.get().getCards();
				Stream<Card> sCards = cards.stream();
				Map<String, Map<Object, List<Card>>> map= sCards.collect(Collectors.groupingBy(Card::getSuit,Collectors.groupingBy(Card::getValue)));
				
				return map;
			} else {
				throw new DeckNotFoundException("Deck : " + idDeck + " Not Found !");
			}

		} else {

			throw new GameNotFoundException("Game : " + idGame + " Not Found !");

		}

	}

}
