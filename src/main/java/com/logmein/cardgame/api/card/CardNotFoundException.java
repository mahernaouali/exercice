package com.logmein.cardgame.api.card;

public class CardNotFoundException extends RuntimeException {

	public CardNotFoundException(String exception) {
		super(exception);
	}
}
