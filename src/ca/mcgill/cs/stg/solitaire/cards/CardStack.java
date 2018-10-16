package ca.mcgill.cs.stg.solitaire.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a general-purpose stack of cards. New CardStack
 * instances are initially empty.
 */
public class CardStack
{
	private final List<Card> aCards = new ArrayList<>();
	
	/**
	 * Pushes pCard onto the stack.
	 * 
	 * @param pCard The card to push.
	 * @pre pCard != null;
	 * @pre !aCards.contains(pCard)
	 */
	public void push(Card pCard)
	{
		assert pCard != null && !aCards.contains(pCard);
		aCards.add(pCard);
	}
	
	/**
	 * Removes the card on top of the stack and returns it.
	 * 
	 * @return The card on top of the stack.
	 * @pre !isEmpty()
	 */
	public Card pop()
	{
		assert !isEmpty();
		return aCards.remove(aCards.size()-1);
	}
	
	/**
	 * @return The card at the top of the stack.
	 * @pre !isEmpty();
	 */
	public Card peek()
	{
		assert !isEmpty();
		return aCards.get(aCards.size()-1);
	}
	
	/**
	 * Removes all the cards in the stack.
	 */
	public void clear()
	{
		aCards.clear();
	}
	
	/**
	 * Shuffles the cards in the stack.
	 */
	public void shuffle()
	{
		Collections.shuffle(aCards);
	}
	
	/**
	 * @return True if and only if the stack has no cards in it.
	 */
	public boolean isEmpty()
	{
		return aCards.size() == 0;
	}
}
