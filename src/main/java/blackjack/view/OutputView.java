package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.Result;
import blackjack.domain.result.UserResult;
import blackjack.dto.ParticipantDto;
import blackjack.view.cardview.OriginCardNumber;
import blackjack.view.cardview.OriginalCardType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

public class OutputView {

    private static final String INIT_DISTRIBUTE_FORMAT = "딜러와 %s에게 2장의 카드를 나누었습니다.";
    private static final String MORE_DEALER_DRAW_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String CARD_SEPARATOR = ", ";
    private static final String CARD_PRINT_FORMAT = "%s: %s";
    private static final String SCORE_FORMAT = " - 점수 : %d";
    private static final String FINAL_RESULT_MESSAGE = "## 최종 승패";
    private static final String DEALER_RESULT_FORMAT = "딜러: %d승 %d무 %d패";
    private static final String USER_RESULT_FORMAT = "%s: %s";

    public static void printInitDistribute(List<ParticipantDto> participantDtos) {
        System.out.printf(lineSeparator() + INIT_DISTRIBUTE_FORMAT + lineSeparator(),
                participantDtos.stream()
                        .map(ParticipantDto::getName)
                        .collect(Collectors.joining(CARD_SEPARATOR)));

        for (ParticipantDto participantDto : participantDtos) {
            printParticipantCards(participantDto);
        }
        System.out.print(lineSeparator());
    }

    public static void printParticipantCards(ParticipantDto participant) {
        System.out.printf(CARD_PRINT_FORMAT + lineSeparator(), participant.getName(), getHoldingCards(participant.getCards()));
    }

    private static String getHoldingCards(List<Card> cards) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Card card : cards) {
            stringBuilder.append(OriginCardNumber.getOriginalName(card.getCardNumber()))
                    .append(OriginalCardType.getOriginalName(card.getType()))
                    .append(CARD_SEPARATOR);
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return stringBuilder.toString();
    }

    public static void printDealerDraw() {
        System.out.println(lineSeparator() + MORE_DEALER_DRAW_CARD);
    }

    public static void printFinalCard(List<ParticipantDto> participantDtos) {
        for (ParticipantDto participantDto : participantDtos) {
            printParticipantCardsWithScore(participantDto);
        }
    }

    private static void printParticipantCardsWithScore(ParticipantDto participant) {
        System.out.printf(lineSeparator() + CARD_PRINT_FORMAT + SCORE_FORMAT,
                participant.getName(), getHoldingCards(participant.getCards()), participant.getScore());
    }

    public static void printFinalResult(DealerResult dealerResult, List<UserResult> userResults) {
        printDealerResult(dealerResult.getResult());

        for (UserResult userResult : userResults) {
            printUserResult(userResult);
        }
    }

    private static void printDealerResult(Map<Result, Integer> count) {
        System.out.println(lineSeparator() + lineSeparator() + FINAL_RESULT_MESSAGE);
        System.out.printf(DEALER_RESULT_FORMAT, count.getOrDefault(Result.WIN, 0),
                count.getOrDefault(Result.DRAW, 0), count.getOrDefault(Result.LOSE, 0));
        System.out.print(lineSeparator());
    }

    private static void printUserResult(UserResult userResult) {
        System.out.printf(USER_RESULT_FORMAT + lineSeparator(), userResult.getName(), userResult.getResult().getName());
    }
}
