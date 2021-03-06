package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.TestCardGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static blackjack.domain.Game.DEALER_NAME;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DealerTest {

    @Test
    @DisplayName("딜러가 보유한 숫자의 합이 16초과인 경우 false를 반환한다.")
    void checkUpperSumStandardTest() {
        TestCardGenerator cardGenerator = new TestCardGenerator(
                List.of(new Card(CardNumber.EIGHT, CardType.CLOVER),
                        new Card(CardNumber.QUEEN, CardType.CLOVER)));
        Deck deck = new Deck(cardGenerator);

        Dealer dealer = new Dealer(DEALER_NAME);
        dealer.drawCard(deck);
        dealer.drawCard(deck);

        assertThat(dealer.checkUnderScoreStandard()).isFalse();
    }

    @Test
    @DisplayName("딜러가 보유한 숫자의 합이 16이하인 경우 true를 반환한다.")
    void checkUnderSumStandard() {
        TestCardGenerator cardGenerator = new TestCardGenerator(
                List.of(new Card(CardNumber.SIX, CardType.CLOVER),
                        new Card(CardNumber.QUEEN, CardType.CLOVER)));
        Deck deck = new Deck(cardGenerator);

        Dealer dealer = new Dealer(DEALER_NAME);
        dealer.drawCard(deck);
        dealer.drawCard(deck);

        assertThat(dealer.checkUnderScoreStandard()).isTrue();
    }

    @Test
    @DisplayName("딜러의 수익금은 모든 유저의 수익금의 합의 -1배이다.")
    void calculateProfitTest() {
        List<Double> profits = Arrays.asList(20000.0, 0.0, -5000.0);
        Dealer dealer = new Dealer(DEALER_NAME);

        assertThat(dealer.calculateProfit(profits)).isEqualTo(-15000);
    }
}
