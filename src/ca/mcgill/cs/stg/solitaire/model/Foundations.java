/*******************************************************************************
 * Solitaire
 *
 * Copyright (C) 2016 by Martin P. Robillard
 *
 * See: https://github.com/prmr/Solitaire
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package ca.mcgill.cs.stg.solitaire.model;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.CardStack;
import ca.mcgill.cs.stg.solitaire.cards.Rank;

/**
 * Represents the four piles that must be completed to win the game, with the ace
 * at the bottom, face up, and all cards of the same suit on top, in sequence.
 */
class Foundations extends CardPiles<FoundationPile>
{
	/**
	 * Creates an initialized FoundationPiles object that consists of four empty piles.
	 */
	Foundations()
	{
		super(FoundationPile.class);
	}

	/**
	 * @return The total number of cards in all the foundation piles.
	 */
	int getTotalSize()
	{
		int total = 0;
		for( CardStack stack : aPiles.values())
		{
			total += stack.size();
		}
		return total;
	}

	/**
	 * Initializes the FoundationPiles object to reset it to four empty piles.
	 */
	void initialize()
	{
		reset(FoundationPile.class);
	}

	/**
	 * @param pCard The card we wish to move
	 * @param pLocation The desired location for pCard
	 * @return True if pCard can be moved to the top of pLocation.
	 * This is only possible if its rank is immediately superior
	 * to that of the card currently on top of the pile or, in
	 * the case of an ace, if the location is empty.
	 * @pre pCard != null && pLocation != null
	 */
	boolean canMoveTo(Card pCard, FoundationPile pLocation )
	{
		assert pCard != null && pLocation != null;
		if( isEmpty(pLocation))
		{
			return pCard.getRank() == Rank.ACE;
		}
		else
		{
			return pCard.getSuit() == peek(pLocation).getSuit() &&
					pCard.getRank().ordinal() == peek(pLocation).getRank().ordinal()+1;
		}
	}

}
