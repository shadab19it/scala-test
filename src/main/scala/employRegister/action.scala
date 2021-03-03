package employRegister
import scalikejdbc._

import scala.io.StdIn._
import scala.util.Try

object action {
  def dbConnection: Unit = {
    Class.forName("org.postgresql.Driver")
    //Generate a collection of connection which has only 1 connection
    ConnectionPool.singleton(
      "jdbc:postgresql://localhost:8032/localgiga",
      "giga-admin",
      "pgpass"
    )
  }

  def makeTable()(implicit session: DBSession): Unit =
    sql"""CREATE TABLE IF NOT EXISTS emp (
                          |      user_Id SERIAL NOT NULL,
                          |      name varchar(45) NOT NULL,
                          |      email varchar(50),
                          |      address varchar(225) NOT NULL,
                          |      salary INT4 NOT NULL
                               )""".stripMargin.execute.apply()

  def addUserAccout(user: empDetail)(implicit session: DBSession): Long = {
    sql"""INSERT INTO emp(name,email,address,salary)  VALUES(${user.name}, ${user.email}, ${user.address},${user.salary})"""
      .updateAndReturnGeneratedKey()
      .apply()
  }

  def listEmployees()(implicit
      session: DBSession
  ): Try[Seq[empDetail]] = {
    Try {
      sql"""SELECT user_Id, name,email,address,salary, address FROM emp"""
        .map { result =>
          empDetail(
            name = result.string("name"),
            email = result.string(columnLabel = "email"),
            salary = result.int("salary"),
            address = result.string("address")
          )
        }
        .list()
        .apply()
    }
  }

  def incrementSalary(amt: Long, empId: Long)(implicit
      session: DBSession
  ): Try[Seq[empDetail]] = {
    Try {
      sql"""UPDATE emp set salary=${amt} WHERE user_id=${empId}"""
        .map { result =>
          empDetail(
            name = result.string("name"),
            email = result.string(columnLabel = "email"),
            salary = result.int("salary"),
            address = result.string("address")
          )
        }
        .list()
        .apply()
    }

  }

  def selectOpt: Int = {
    var opt: Int = 0
    println("Select Option to Perform a Task")
    println("")
    println("Press ' 1 ' for add Employ")
    println("Press ' 2 ' for to view all Employees detail")
    println("Press ' 3 ' for Increment Employ Salary")
    println("Press ' 4 ' for Edit Employ")
    println("Press ' 5 ' for Exit ")
    opt = scala.io.StdIn.readInt()
    opt
  }

  def repeatOpt: Int = {
    var opt: Int = 0
    println("Select the Option")
    println("")
    println("Press ' 6 ' for return Main Menu")
    println("Press ' 5 ' for Exit ")
    opt = scala.io.StdIn.readInt()
    opt
  }

  def createEmp(): empDetail = {
    var name, email, address: String = ""
    var salary: Int = 0

    //  take a inpu from user
    name = readLine("Enter The name \n")
    email = readLine("Enter The Email \n")
    address = readLine("Enter The Address \n")
    println("Enter The Salary \n")
    salary = readInt()

    val newEmp = empDetail(
      name,
      email,
      address,
      salary
    )
    return newEmp;
  }

}

case class empDetail(
    name: String,
    email: String,
    address: String,
    salary: Int
);
