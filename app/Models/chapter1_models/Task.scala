package Models.chapter1_models

/**
  * Created by botman on 19/2/17.
  */

// The task case class for capturing the data
case class Task(id: Int, taskName: String)

// The object that can be used to utilise the model in the templates
object TaskList {

  private var taskList: List[Task] = List()

  def addTask(name: String): Unit = taskList match {
    case Nil => taskList = List(Task(0, name))
    case _ => taskList = taskList ++ List(Task(taskList.last.id + 1, name))
  }

  def delete(id: Int): Unit = {
    taskList = taskList.filterNot(t => t.id == id)
  }

  def fetchAllTasks: List[Task] = taskList
}