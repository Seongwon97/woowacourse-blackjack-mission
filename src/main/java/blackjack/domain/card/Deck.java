package blackjack.domain.card;

import blackjack.domain.card.generator.CardGenerateStrategy;

import java.util.LinkedList;
import java.util.Queue;

public class Deck {

    private static final String ERROR_EMPTY_DECK = "[ERROR] 더이상 뽑을 카드가 없습니다.";

    private final Queue<Card> cards;

    public Deck(CardGenerateStrategy cardGenerateStrategy) {
        this.cards = new LinkedList<>(cardGenerateStrategy.generate());
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(ERROR_EMPTY_DECK);
        }
        return cards.poll();
    }

    public Queue<Card> getCards() {
        return cards;
    }
}
