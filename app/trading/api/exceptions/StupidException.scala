package trading.api.exceptions

/**
  * Created by cosmo on 30.09.2016.
  */
class StupidException(ex: RuntimeException) extends RuntimeException(ex) {
  def this(message:String) = this(new RuntimeException(message))
  def this(message:String, throwable: Throwable) = this(new RuntimeException(message, throwable))
}
