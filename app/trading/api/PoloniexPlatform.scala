package trading.api

import java.time.LocalDateTime
import java.util.Date
import javax.inject.Inject

import akka.actor.Actor
import com.google.common.util.concurrent.RateLimiter
import play.api.libs.functional.syntax._
import play.api.libs.json.{Reads, _}
import play.api.libs.ws._
import trading.api.exceptions.JsonParseException
import trading.api.utils.Utils
import trading.{Candlestick, TickerSize, TradingPair}

import scala.collection.immutable.{Map, _}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.{Failure, Success, Try}

/**
  * Created by cosmo on 26.09.2016.
  */
class PoloniexPlatform @Inject() (ws: WSClient) extends TradingPlatform {

  val rateLimiter: RateLimiter = RateLimiter.create(5)

  implicit def candlestickReads: Reads[Candlestick] = (
    (JsPath \ "date").read[Int] and
    (JsPath \ "high").read[Double] and
    (JsPath \ "low").read[Double] and
    (JsPath \ "open").read[Double] and
    (JsPath \ "close").read[Double] and
    (JsPath \ "volume").read[Double] and
    (JsPath \ "quoteVolume").read[Double] and
    (JsPath \ "weightedAverage").read[Double]
    )(Candlestick.apply _)
  implicit def chartDataReads: Reads[Map[Int, Candlestick]] = {
    new Reads[Map[Int, Candlestick]] {
      override def reads(json: JsValue): JsResult[Map[Int, Candlestick]] = {
        json.validate[List[Candlestick]].map { list =>
          list.map(candlestick => (candlestick.date, candlestick)).toMap
        }
      }
    }
  }

  override def getTradingPairText(tp: TradingPair) = tp.from + "_" + tp.to

  override def returnChartData(tradingPair: TradingPair, tickerSize: TickerSize, start: LocalDateTime, end: LocalDateTime): Future[Try[Map[Int, Candlestick]]] = {
    rateLimiter.acquire()
    val request = ws.url("https://poloniex.com/public")
      .withHeaders("Accept" -> "application/json")
      .withRequestTimeout(10000.millis)
      .withQueryString(
        "command" -> "returnChartData",
        "currencyPair" -> getTradingPairText(tradingPair),
        "start" -> start.atZone(Utils.utcZoneId).toEpochSecond.toString,
        "end" -> start.atZone(Utils.utcZoneId).toEpochSecond.toString,
        "period" -> tickerSize.size.toString
      )
    val futureResponse = request.get
    futureResponse map { r =>
      val jsResult = r.json.validate[Map[Int, Candlestick]]
      jsResult match {
        case JsSuccess(map, _) => Success(map)
        case JsError(errors) => Failure(throw new JsonParseException(errors.toString()))
      }
    }
  }
}
