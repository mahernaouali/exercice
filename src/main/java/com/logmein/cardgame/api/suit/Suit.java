package com.logmein.cardgame.api.suit;

/**
 * Specification of the suit values for a standard deck of cards.
 */
public final class Suit {
	private String name;
	private String symbol;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	private Suit(String nameValue, String symbolValue) {
		name = nameValue;
		symbol = symbolValue;
	}

	/**
	 * The suit clubs.
	 */
	public final static Suit CLUBS = new Suit("Clubs", "C");
	/**
	 * The suit diamonds.
	 */
	public final static Suit DIAMONDS = new Suit("Diamonds", "D");
	/**
	 * The suit hearts.
	 */
	public final static Suit HEARTS = new Suit("Hearts", "H");
	/**
	 * The suit spades.
	 */
	public final static Suit SPADES = new Suit("Spades", "S");

}
