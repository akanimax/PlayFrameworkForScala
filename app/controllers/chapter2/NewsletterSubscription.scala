package controllers.chapter2

import java.io.File

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

  // accepts 10 MB file upload this limit has been set in the application.conf
  def addUserPic = Action(parse.multipartFormData) {
    request =>

      val path = "public/images/media"

      request.body.file("pic") match {
        case Some(pic) => {
          // There is indeed some file here

          // check if the file is indeed an image
          pic.contentType.get.split("/")(0) match {
            case "image" => {
              // image file obtained. so save the file
              val filename = "Source"
              // save the file in images directory of the project
              pic.ref.moveTo(new File(s"$path/$filename"), replace = true)
            }

            case _ => Ok("Uploaded File is not an Image")
          }

          Ok(views.html.chapter2_views.user_image_added())
        }

        case None => Ok("File Not selected") // file not uploaded here
      }
  }

  def currentImage = Action {
    Ok(views.html.chapter2_views.current_image())
  }


}
