package ca.mcgill.cs.stg.solitaire.model;

import java.util.Map;
import java.util.EnumMap;
import java.lang.reflect.Method;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.CardStack;

/* Generic abstract class for card piles indexed by enum type T */
public abstract class CardPiles<T extends Enum<T> & Location>
{
    protected final Map<T, CardStack> aPiles;

    CardPiles(Class<T> enumClass)
    {
        aPiles = new EnumMap<T, CardStack>(enumClass);
        reset(enumClass);
    }

    /**
     * Initializes the CardPiles object to reset it to empty piles.
     */
    @SuppressWarnings("unchecked")
    protected void reset(Class<T> enumClass)
    {
        aPiles.clear();
        try {
            Method values = enumClass.getMethod("values", (Class<?>[])null);
            try {
                for( T index : (T[])values.invoke(null, (Object[])null) )
                {
                    aPiles.put(index, new CardStack());
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * @param pLocation The location of the pile to check.
     * @return True if the pile at pLocation is empty
     * @pre pLocation != null
     */
    boolean isEmpty(T pLocation)
    {
        assert pLocation != null;
        return aPiles.get(pLocation).isEmpty();
    }

    /**
     * Places a card on top of the pile at pLocation.
     * @param pCard The card to push.
     * @param pLocation The index of the destination stack.
     * @pre pCard != null && pIndex != null;
     */
    void push(Card pCard, T pLocation)
    {
        assert pCard != null && pLocation != null;
        aPiles.get(pLocation).push(pCard);
    }

    /**
     * Remove the card at the top of the pile at pLocation,
     * and returns it.
     *
     * @param pLocation The location where to obtain the card.
     * @pre pLocation != null && !isEmpty(pLocation)
     */
    Card pop(T pLocation)
    {
        assert pLocation != null && !isEmpty(pLocation);
        return aPiles.get(pLocation).pop();
    }

    /**
     * @param pLocation The location of the pile to peek at
     * @return The card on top of the pile at pLocation
     * @pre pLocation != null & !aPiles.get(pLocation).isEmpty();
     */
    Card peek(T pLocation)
    {
        assert pLocation != null && !aPiles.get(pLocation).isEmpty();
        return aPiles.get(pLocation).peek();
    }

}
