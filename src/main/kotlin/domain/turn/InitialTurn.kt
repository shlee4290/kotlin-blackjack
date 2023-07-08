package domain.turn

import domain.card.CardDeck
import domain.gamer.Gamers

object InitialTurn : Turn {
    override fun proceed(
        gamers: Gamers,
        cardDeck: CardDeck,
        changeState: (Turn) -> Unit
    ) {
        gamers.hit(cardDeck)
        gamers.hit(cardDeck)
        changeState(IntermediateTurn)
    }
}
