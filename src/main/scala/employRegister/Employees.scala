package employRegister
import scalikejdbc.{AutoSession, DBSession}

import scala.io.StdIn.readLine
import scala.util.{Failure, Success, Try}

object Employees {
  def main(args: Array[String]) = {
    println("Employees Registered")
    implicit val session = AutoSession
    action.dbConnection
    action.makeTable()

    var opt: Int = action.selectOpt

    while (opt <= 6 & opt > 0) {
      if (opt == 1) {
        val newEmp = action.createEmp()
        val empId = action.addUserAccout(newEmp)
        println(s"New Employ detail added and id = ${empId}")
        opt = action.repeatOpt
      } else if (opt == 2) {
        val empList = action.listEmployees()
        emplistRes(empList)
        opt = action.repeatOpt
      } else if (opt == 3) {
        var amt, empId: Long = 0
        println("Please Enter emp Id")
        empId = readInt()
        println("Enter the Icreament Salary")
        amt = readInt()

        val employ = action.incrementSalary(amt, empId)
        employ match {
          case Failure(exception) =>
            println(s"Failed due to : ${exception.getMessage}")
          case Success(empId) =>
            println(s"Salary of Employ id ${empId} Updated ")
        }
//        incrementSalaryRes(employ)
        opt = action.repeatOpt

      } else if (opt == 4) {
        var empId: Long = -1
        println("Please Enter emp Id")
        empId = readInt()
        val deletEmpId = action.deleteEmp(empId)
        println(s"EmpId ${deletEmpId} deleted successfull")
        opt = action.repeatOpt
      } else if (opt == 5) {
        System.exit(0)
      } else if (opt == 6) {
        opt = action.selectOpt
      } else {
        opt = action.selectOpt
      }
    }
  }

  def emplistRes(empList: Try[Seq[empDetail]]): Unit = {
    empList match {
      case Failure(exception) =>
        println(s"Failed due to : ${exception.getMessage}")
      case Success(emps) =>
        emps.foreach(emp =>
          println(
            s" id ; ${emp.name} has salary of ${emp.salary} rs' , live in ${emp.address}"
          )
        )
    }
  }
//  def incrementSalaryRes(empList: Try[Seq[empDetail]]): Unit = {
//    empList match {
//      case Failure(exception) =>
//        println(s"Failed due to : ${exception.getMessage}")
//      case Success(emps) =>
//        emps.foreach(emp =>
//          println(
//            s" id ; ${emp.name} has salary of ${emp.salary} rs' , live in ${emp.address}"
//          )
//        )
//    }
//  }

}
