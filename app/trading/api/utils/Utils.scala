package trading.api.utils

import java.time._
import java.time.temporal.TemporalAdjusters

/**
  * Created by cosmo on 29.09.2016.
  */
object Utils {

  val utcZoneId = ZoneId.of("UTC")

  def midnightOf(dateTime: LocalDateTime) = dateTime.`with`(LocalTime.MIN)

  def beginningOfMonthOf(dateTime: LocalDateTime) = dateTime.`with`(TemporalAdjusters.firstDayOfMonth())

  def dateTimeToUnix(dateTime: LocalDateTime) = dateTime.atZone(utcZoneId).toEpochSecond

}
