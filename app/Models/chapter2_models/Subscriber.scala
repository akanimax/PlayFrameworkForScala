package Models.chapter2_models

/**
  * Created by botman on 27/2/17.
  */

// model for the subscription
case class Subscriber(email: String, interval: Long)

object SubscriptionList {
  private var currentSubscribers: Vector[Subscriber] = Vector()

  def addSubscriber(subscriber: Subscriber): Unit = {
    // add the new subscriber only if the email doesn't already exists.
    if(!currentSubscribers.exists(subs => subs.email == subscriber.email))
      currentSubscribers = currentSubscribers ++ Vector(subscriber)
  }

  def delSubscriber(email: String): Unit = {
    currentSubscribers = currentSubscribers.filterNot(subs => subs.email == email)
  }


  def getCurrentSubscribers: Vector[Subscriber] = currentSubscribers
}