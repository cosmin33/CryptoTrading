package trading

/**
  * Created by cosmo on 26.09.2016.
  */
sealed trait TickerSize extends Ordered[TickerSize] {
  def name: String
  def size: Int


  override def compare(that: TickerSize): Int = {
    if (this.size < that.size) -1
    else if (this.size == that.size) 0
    else 1
  }

  override def toString: String = this.name
}

object TickerSize {
  case object Ticker1 extends TickerSize {val name = "1"; val size = 60}
  case object Ticker3 extends TickerSize {val name = "3"; val size = 60 * 3}
  case object Ticker5 extends TickerSize {val name = "5"; val size = 300}
  case object Ticker15 extends TickerSize {val name = "15"; val size = 300 * 3}
  case object Ticker30 extends TickerSize {val name = "30"; val size = 300 * 6}
  case object Ticker1h extends TickerSize {val name = "1h"; val size = 3600}
  case object Ticker2h extends TickerSize {val name = "2h"; val size = 3600 * 2}
  case object Ticker4h extends TickerSize {val name = "4h"; val size = 3600 * 4}
  case object Ticker6h extends TickerSize {val name = "6h"; val size = 3600 * 6}
  case object Ticker12h extends TickerSize {val name = "12h"; val size = 3600 * 12}
  case object Ticker1d extends TickerSize {val name = "1d"; val size = 3600 * 24}
}
