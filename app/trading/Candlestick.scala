package trading

/**
  * Created by cosmo on 26.09.2016.
  */
case class Candlestick(
  date: Int,
  high: Double,
  low: Double,
  open: Double,
  close: Double,
  volume: Double,
  quoteVolume: Double,
  weightedAverage: Double
)
