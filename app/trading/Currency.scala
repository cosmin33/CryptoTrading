package trading

/**
  * Created by cosmo on 26.09.2016.
  */
class Currency(code, name) {
  def code: String
  def name: String
}

object Currency {
  case object BTC extends Currency {val code = "BTC"; val name = "Bitcoin"}
  case object USD extends Currency {val code = "USD"; val name = "US Dollar"}
  case object EUR extends Currency {val code = "EUR"; val name = "Euro"}
  case object XMR extends Currency {val code = "XMR"; val name = "Monero"}
  case object XRP extends Currency {val code = "XRP"; val name = "Ripple"}
  case object ETH extends Currency {val code = "ETH"; val name = "Ethereum"}
  case object FCT extends Currency {val code = "FCT"; val name = "Factom"}
  case object STR extends Currency {val code = "STR"; val name = "Stellar"}
  case object MAID extends Currency {val code = "MAID"; val name = "MaidSafeCoin"}
  case object DASH extends Currency {val code = "DASH"; val name = "Dash"}
  case object LTC extends Currency {val code = "LTC"; val name = "Litecoin"}
  case object BTS extends Currency {val code = "BTS"; val name = "BitShares"}
  case object CLAM extends Currency {val code = "CLAM"; val name = "Clam"}
  case object DOGE extends Currency {val code = "DOGE"; val name = "Dogecoin"}
}
