package blackjack

import domain.card.Cards
import domain.gamer.Gamers
import domain.gamer.dealer.Dealer
import domain.gamer.dealer.DealerState
import domain.gamer.player.Player
import domain.gamer.player.PlayerState
import domain.gamer.player.Players
import domain.turn.FinalTurn
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FinalTurnTest {
    @Test
    fun `딜러 버스트이면 모든 플레이어가 승리`() {
        val players = playersWithTwoPlayer

        val result = FinalTurn.result(
            Gamers.of(
                Dealer(
                    DealerState(Cards(listOf(spadeTen, spadeKing, spadeJack))),
                ),
                players,
            )
        )

        assertThat(result.numOfLoser).isEqualTo(0)
        assertThat(result.numOfWinner).isEqualTo(players.list.size)
    }

    @Test
    fun `딜러 블랙잭이면 모든 플레이어가 패배`() {
        val players = playersWithTwoPlayer

        val result = FinalTurn.result(
            Gamers.of(
                Dealer(
                    DealerState(Cards(listOf(spadeTen, spadeAce))),
                ),
                players,
            )
        )

        assertThat(result.numOfLoser).isEqualTo(players.list.size)
        assertThat(result.numOfWinner).isEqualTo(0)
    }

    @Test
    fun `플레이어 승패 계산 테스트`() {
        val winner = Player("winner", PlayerState(Cards(listOf(spadeTen, spadeKing))))
        val loser = Player("loser", PlayerState(Cards(listOf(spadeJack))))
        val players = Players(listOf(winner, loser))

        val result = FinalTurn.result(
            Gamers.of(
                Dealer(
                    DealerState(Cards(listOf(spadeTen, spadeSeven))),
                ),
                players,
            )
        )

        assertThat(result.winners.list).isEqualTo(listOf(winner))
        assertThat(result.losers.list).isEqualTo(listOf(loser))
    }
}
