package trading.api

import java.time.LocalDateTime
import java.util.Date

import trading.{Candlestick, TickerSize, TradingPair}

import scala.collection.immutable.{Map, HashMap}
import scala.concurrent.Future
import scala.util.Try

/**
  * Created by cosmo on 26.09.2016.
  */
abstract class TradingPlatform {
  def getTradingPairText(tp: TradingPair): String
  def returnChartData(tradingPair: TradingPair, tickerSize: TickerSize, start: LocalDateTime, end: LocalDateTime): Future[Try[Map[Int, Candlestick]]]
}
