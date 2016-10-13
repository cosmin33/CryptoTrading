package trading

import java.time.LocalDateTime
import trading.api.TradingPlatform
import trading.api.exceptions.StupidException
import trading.api.utils.Utils
import scala.collection.SortedSet
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.util.{Success, Try}
import trading.api.utils.Implicits.executionContext

/**
  * Created by cosmo on 26.09.2016.
  */
class Market(name: String, tradingPlatform: TradingPlatform, tickerSizes: SortedSet[TickerSize], pairs: List[TradingPair]) {
  var pairData: Map[TradingPair, Map[TickerSize, Map[Int, Candlestick]]] =
    pairs.map(
      tp => tp -> tickerSizes.map(
        ts => ts -> Map[Int, Candlestick]()
      ).toMap
    ).toMap

  /**
    * Gives a list with midnights starting from the given date and going in the past the number of days given as a parameter
    * @param dateTime the date from which to start going in the past
    * @param days the number of days to go in the past
    */
  private def midnightsFromThePast(dateTime: LocalDateTime, days: Int): List[LocalDateTime] = {
    var previousMidnight = Utils.midnightOf(dateTime)
    var result = List[LocalDateTime]()
    var remainingDays = days
    while (remainingDays > 0) {
      result = result.::(previousMidnight)
      previousMidnight = Utils.midnightOf(previousMidnight.minusSeconds(1))
      remainingDays = remainingDays - 1
    }
    result
  }

  def fillPairData(tradingPair: TradingPair, tickerSize: TickerSize) = {

    // check the existence of tradingPair and tickerSize
    pairData.getOrElse(tradingPair,
      throw new StupidException(s"Can't find trading pair $tradingPair")
    ).getOrElse(tickerSize,
      throw new StupidException(s"Can't find ticker size $tickerSize")
    )

    val now = LocalDateTime.now()
    val lastMidnight = Utils.midnightOf(now)
    pairData.get(tradingPair) foreach {
      _.get(tickerSize) foreach { map =>
        val days = midnightsFromThePast(now, 90).map(t => (t.minusDays(1), t))
        val daysPlusNow = (lastMidnight, now) :: days
        val listOfFutures = daysPlusNow map { case (start, end) =>
          getPairData(tradingPair, tickerSize, start, end)
        }
        val future = Future.sequence(listOfFutures)
        Await.result(future, Duration.Inf)
        future.value

      }
    }
  }

  def getPairData(tradingPair: TradingPair, tickerSize: TickerSize, start: LocalDateTime, end: LocalDateTime): Future[Try[Map[Int, Candlestick]]] = {
    tradingPlatform.returnChartData(tradingPair, tickerSize, start, end)
  }
}
