package employeeBook;

import employee.Employee;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EmployeeBook {
    private final Map<String, Employee> employeeBook;
    private int employeeCount;
    private final int idDepartment = 5;

    public EmployeeBook() {
        employeeBook = new HashMap<>();
    }

    //добавляем нового сотрудника
    public void addNewEmployee(Employee employee) {
        if (!employeeBook.containsKey(employee.getName() + employee.getSurname())) {
            employeeBook.put(employee.getName() + employee.getSurname(), employee);
        } else {
            throw new RuntimeException();
        }
    }


    //удаляем сотрудника
    public void removeEmployee(Employee employee) {
        if (employeeBook.containsKey(employee.getName() + employee.getSurname())) {
            employeeBook.remove(employee.getName() + employee.getSurname());

        } else {
            throw new RuntimeException();
        }
    }

    //для вывода списка всех сотрудников
    public void printAllEmployees() {
        Iterator<Map.Entry<String, Employee>> iterator = employeeBook.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Employee> employeeEntry = iterator.next();
            System.out.println(employeeEntry.getValue());
        }
    }

    //для вывода суммы зарплат всех сотрудников
    public void sumSalary() {
        int sum = 0;
        Iterator<Map.Entry<String, Employee>> iterator = employeeBook.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Employee> employeeEntry = iterator.next();
            sum = sum + employeeEntry.getValue().getSalary();
        }
        System.out.println("sum = " + sum);
    }

    // для вывода сотрудника с минимальной ЗП
    public void minSalaryEmployee() {
        int minSalaryEmployee = Integer.MAX_VALUE;
        Employee poorestEmployee = null;
        Iterator<Map.Entry<String, Employee>> entryIterator = employeeBook.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, Employee> employeeEntry = entryIterator.next();
            if (employeeEntry.getValue().getSalary() < minSalaryEmployee) {
                minSalaryEmployee = employeeEntry.getValue().getSalary();
                poorestEmployee = employeeEntry.getValue();
            }
        }
        System.out.println("minSalaryEmployee = " + minSalaryEmployee + "\n" +
                poorestEmployee);
    }

    // для вывода сотрудника с максиимальной ЗП
    public void maxSalaryEmployee() {
        Employee richestEmployee = null;
        int maxSalary = 0;
        Iterator<Map.Entry<String, Employee>> iterator = employeeBook.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Employee> employeeEntry = iterator.next();
            if (employeeEntry.getValue().getSalary() > maxSalary) {
                maxSalary = employeeEntry.getValue().getSalary();
                richestEmployee = employeeEntry.getValue();
            }
        }
        System.out.println("maxSalary = " + maxSalary + "\n" +
                "Наибогатейший сотрудник - " + richestEmployee);
    }

    //для вывода средней ЗП
    public void averageSalary() {
        double sum = 0;
        int count = 0;
        double averageSum = 0;
        Iterator<Map.Entry<String, Employee>> iterator = employeeBook.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Employee> employeeEntry = iterator.next();
            sum = sum + employeeEntry.getValue().getSalary();
            count++;
        }
        averageSum = sum / count;
        System.out.println("Средняя ЗП - " + averageSum);
    }
}
   /* //для вывода имен всех сотрудников
    public void printNameOfEmployees() {
        for (Employee o : employeeBook) {
            if (o != null) {
                System.out.println("ФИО сотрудника = " + o.getName());
            }

        }
        System.out.println("===================================");
    }

    //для индексирования ЗП
    public void indexSalaryByPercentage(int percent) {
        for (Employee o : employeeBook) {
            if (o != null) {
                int currentSalary = o.getSalary();
                int indexSalary = (currentSalary + (currentSalary / 100 * percent));
                o.setSalary(indexSalary);
            }
        }
        System.out.println("====================================");
    }

    //нати сотрудника с мин ЗП по номеру отдела
    public void findMinSalaryByDepartment(int numberOfDepartment) {
        Employee employee = null;
        int min = Integer.MAX_VALUE;
        for (Employee a : employeeBook)

            if (a != null && a.getDepartment() == numberOfDepartment && a.getSalary() < min) {
                min = a.getSalary();
                employee = a;
            }
        System.out.println("Минимальная ЗП по отделу " + numberOfDepartment + "\n- " + employee);
        System.out.println("====================================");
    }

    //нати сотрудника с max ЗП по номеру отдела
    public void findMaxSalaryByDepartment(int numberOfDepartment) {
        Employee employee = null;
        int max = -1;
        for (Employee o : employeeBook) {

            if (o != null && o.getDepartment() == numberOfDepartment && o.getSalary() > max) {
                max = o.getSalary();
                employee = o;

            }
        }
        System.out.println("Максимальная ЗП по отделу " + numberOfDepartment + "\n- " + employee);
        System.out.println("====================================");
    }

    //сумма затрат на ЗП по отделу
    public void getTotalSalaryCostByDepartment(int department) {
        int totalCost = 0;
        for (Employee o : employeeBook) {
            if (o != null && o.getDepartment() == department) {
                totalCost += o.getSalary();
            }
        }
        System.out.println("Суммарная ЗП по отделу " + department + " - " + totalCost);
        System.out.println("====================================");
    }

    //средняя ЗП по отделу
    public void getAverageSalaryCostByDepartment(int department) {
        int averageCost = 0;
        int count = 0;
        for (Employee o : employeeBook) {

            if (o != null && o.getDepartment() == department) {
                averageCost += o.getSalary();
                count++;
            }
        }
        averageCost = averageCost / count;
        System.out.println("Средняя ЗП по отделу " + department + " - " + averageCost);
    }

    //индексация ЗП сотрудников одного отдела
    public void indexSalaryCostByDepartment(int department, int percent) {
        for (Employee o : employeeBook) {

            if (o != null && o.getDepartment() == department) {
                int currentSalary = o.getSalary();
                int indexSalary = currentSalary + (currentSalary / 100 * percent);
                o.setSalary(indexSalary);
            }
        }
    }

    //напечатать всех сотрудников отдела - все данные, кроме отдела
    public void printEmployeesInDepartment(int department) {
        for (Employee o : employeeBook) {
            if (o.getDepartment() == department) {
                System.out.println(o.getName() + " " + o.getSalary() + " " + o.getId() + "\n=======================================");
            }
        }
    }

    // Получить в качестве параметра число и найти:
    //Всех сотрудников с зарплатой меньше числа (вывести id, Ф. И. О. и зарплатой в консоль).
    public void employeesWithLessThenSalary(int paramNum) {
        for (Employee o : employeeBook) {

            if (o != null && o.getSalary() < paramNum) {
                System.out.println("Сотрудник с ЗП меньше числа - ID " + o.getId() + " " + o.getName() + " " + o.getSalary());
            }
        }
    }

    //Всех сотрудников с зарплатой больше (или равно) числа (вывести id, Ф. И. О. с зарплатой в консоль).
    public void employeesMoreThanSalary(int paramNum) {
        for (Employee o : employeeBook) {
            if (o != null && o.getSalary() >= paramNum) {
                System.out.println("Сотрудники с ЗП выше, чем число - ID " + o.getId() + " " + o.getName() + " " + o.getSalary());
            }
        }
    }

    //обновление даных о сотруднике
    public void updateEmployee(String name, int salary, int department) {
        for (int i = 0; i < employeeBook.length; i++) {
            if (employeeBook[i].equals(name)) {
                employeeBook[i].setSalary(salary);
                employeeBook[i].setDepartment(department);
                break;
            }
        }
    }

    //напечатать список отделов и их сотрудников
    public void printAllDepartmentsAndNames() {
        for (int i = 1; i <= idDepartment; i++) {
            System.out.println("Отдел - " + i + " ");
            for (Employee o :
                    employeeBook) {
                if (o != null && o.getDepartment() == i) {
                    System.out.println("ФИО " + o.getName());
                }
            }
        }
    }
}
*/

