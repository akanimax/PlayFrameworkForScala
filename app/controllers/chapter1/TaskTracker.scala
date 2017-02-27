package controllers.chapter1

import Models.chapter1_models.TaskList
import play.api.mvc._

/**
  * Created by botman on 19/2/17.
  */

object TaskTracker extends Controller{

  def home = Action {
    Redirect(routes.TaskTracker.tasks)
  }

  def tasks = Action {
    Ok(views.html.chapter1_views.tt_home(TaskList.fetchAllTasks))
  }

  def addTask = Action(parse.urlFormEncoded) {
    request =>
      TaskList.addTask(request.body("taskName").head)
      Redirect(routes.TaskTracker.home)
  }

  def delTask(id: Int) = Action {
    TaskList.delete(id)
    Ok
  }
}
