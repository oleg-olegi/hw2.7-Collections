import employee.Employee;
import employeeBook.EmployeeBook;

public class Main {
    public static void main(String[] args) {
        EmployeeBook employeeBook = new EmployeeBook();
        Employee ivanPetroff = new Employee("Ivan", "Petroff", 1, 1000);
        Employee olegTinkov = new Employee("Oleg", "Tinkov", 4, 10000);
        employeeBook.addNewEmployee(ivanPetroff);
        employeeBook.addNewEmployee(olegTinkov);
        employeeBook.printAllDepartmentsAndNames();
    }
}