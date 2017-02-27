package controllers.chapter2

import Models.chapter2_models.{Subscriber, SubscriptionList}
import play.Logger
import play.api.libs.json.Json
import play.api.mvc.{Action, BodyParser, Controller}

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by botman on 27/2/17.
  */
object NewsletterSubscription extends Controller {


  // define a custom parser for our NewsletterSubscription
  def subscriberParser: BodyParser[Subscriber] = parse.using {
    _ =>
      parse.urlFormEncoded.map {
        body =>
          val email = body("email").head
          val interval = body("interval").head.toLong
          Subscriber(email, interval)
      }
  }

  def subscribe: Action[Subscriber] = Action(subscriberParser) {
    request =>
      val subscriber = request.body

      // log the object to the console
      Logger.info("Data received: " + subscriber.toString)

      // save the new subscription in the subscription List
      SubscriptionList.addSubscriber(subscriber)
      Logger.info(s"Current Subscriptions: ${SubscriptionList.getCurrentSubscribers}")

      // convert the case class to a json and print it
      Ok("Data received: \n" + Json.toJson(subscriber)(Json.writes[Subscriber]))
  }

  def addUser = TODO
}
