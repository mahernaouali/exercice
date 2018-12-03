package com.logmein.cardgame.api.card;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Card {

	@Id
	private String id;
	private String face;
	private String suit;
	private int value;
	private String image;

	public Card() {
		super();
	}

	public Card(String id, String face, String suit, int value, String image) {

		this.id = id;
		this.face = face;
		this.suit = suit;
		this.value = value;
		this.image = image;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getSuit() {

		switch (suit) {
		case "H":
			return "Hearts";
		case "C":
			return "Clubs";
		case "D":
			return "Diamonds";
		case "S":
			return "Spades";
		default:
			return suit;
		}

	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
