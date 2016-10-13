package controllers

import javax.inject.Inject

import play.api.libs.ws._
import play.api.mvc._

/**
  * Created by cosmo on 26.09.2016.
  */
class HomeController @Inject() (ws: WSClient) extends Controller {

  def index = Action {
    Ok(views.html.index("class: application ready"))
  }

}