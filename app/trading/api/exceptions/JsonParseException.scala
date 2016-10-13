package trading.api.exceptions

/**
  * Created by cosmo on 29.09.2016.
  */
class JsonParseException(ex: RuntimeException) extends RuntimeException(ex) {
  def this(message:String) = this(new RuntimeException(message))
  def this(message:String, throwable: Throwable) = this(new RuntimeException(message, throwable))
}
