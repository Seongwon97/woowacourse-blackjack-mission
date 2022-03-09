package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private final List<Card> cards;

    public Dealer() {
        this.cards = new ArrayList<>();
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }
}
