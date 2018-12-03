package com.logmein.cardgame.api.game;

public class GameAlreadyContainsPlayerException extends RuntimeException {

	public GameAlreadyContainsPlayerException(String message) {
		super(message);
	}

}
