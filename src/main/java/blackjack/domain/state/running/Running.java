package blackjack.domain.state.running;

import blackjack.domain.card.HoldingCards;
import blackjack.domain.state.Started;

public abstract class Running extends Started {

    public Running(HoldingCards holdingCards) {
        super(holdingCards);
    }

    @Override
    public final boolean isFinished() {
        return false;
    }
}
