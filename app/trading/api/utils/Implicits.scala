package trading.api.utils

/**
  * Created by cosmo on 30.09.2016.
  */
import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext

object Implicits {
  implicit lazy val executionContext = new ExecutionContext {
    val threadPool = Executors.newFixedThreadPool(100)
    def execute(runnable: Runnable) {
      threadPool.submit(runnable)
    }
    def reportFailure(t: Throwable) {}
  }
}
