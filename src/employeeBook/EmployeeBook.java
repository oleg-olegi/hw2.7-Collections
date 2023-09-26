package employeeBook;

import employee.Employee;

import java.util.*;

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
            Optional<Employee> richestEmployeeCollction = employeeBook.values()
                    .stream()
                    .max(Comparator.comparingDouble(Employee::getSalary));

            if (richestEmployeeCollction.isPresent()) {
                maxSalary = richestEmployeeCollction.get().getSalary();
                richestEmployee = richestEmployeeCollction.get();
            }
        }
        System.out.println("maxSalary = " + maxSalary + "\n" +
                "Наибогатейший сотрудник -\n" + richestEmployee);
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

    //для вывода имен всех сотрудников
    public void printNameOfEmployees() {
        Iterator<Map.Entry<String, Employee>> iterator = employeeBook.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Employee> employeeEntry = iterator.next();
            System.out.println(employeeEntry.getValue().getName() + " " +
                    employeeEntry.getValue().getSurname());
        }
    }

    //для индексирования ЗП
    public void indexSalaryByPercentage(int percent) {
        for (Map.Entry<String, Employee> employeeEntry : employeeBook.entrySet()) {
            int currentSalary = employeeEntry.getValue().getSalary();
            int indexSalary = (currentSalary + (currentSalary / 100 * percent));
            employeeEntry.getValue().setSalary(indexSalary);
        }
    }

    //нати сотрудника с мин ЗП по номеру отдела
    public void findMinSalaryByDepartment(int numberOfDepartment) {
        Employee employee = null;
        int min = Integer.MAX_VALUE;
        for (Map.Entry<String, Employee> employeeEntry : employeeBook.entrySet())
            if (employeeEntry.getValue().getDepartment() == numberOfDepartment &&
                    employeeEntry.getValue().getSalary() < min) {
                min = employeeEntry.getValue().getSalary();
                employee = employeeEntry.getValue();
            }
        System.out.println("Минимальная ЗП по отделу " + numberOfDepartment + "\n- " + employee);
        System.out.println("====================================");
    }

    //нати сотрудника с max ЗП по номеру отдела
    public void findMaxSalaryByDepartment(int numberOfDepartment) {
        Employee employee = null;
        int max = -1;
        for (Map.Entry<String, Employee> employeeEntry : employeeBook.entrySet()) {
            if (employeeEntry.getValue().getDepartment() == numberOfDepartment &&
                    employeeEntry.getValue().getSalary() > max) {
                max = employeeEntry.getValue().getSalary();
                employee = employeeEntry.getValue();
            }
        }
        System.out.println("Максимальная ЗП по отделу " + numberOfDepartment + "\n- " + employee);
        System.out.println("====================================");
    }

    //сумма затрат на ЗП по отделу
    public void getTotalSalaryCostByDepartment(int department) {
        int totalCost = 0;
        for (Map.Entry<String, Employee> employeeEntry : employeeBook.entrySet()) {
            if (employeeEntry.getValue().getDepartment() == department) {
                totalCost += employeeEntry.getValue().getSalary();
            }
        }
        System.out.println("Суммарная ЗП по отделу " + department + " - " + totalCost);
        System.out.println("====================================");
    }

    //средняя ЗП по отделу
    public void getAverageSalaryCostByDepartment(int department) {
        int averageCost = 0;
        int count = 0;
        for (Map.Entry<String, Employee> employeeEntry : employeeBook.entrySet()) {
            if (employeeEntry.getValue().getDepartment() == department) {
                averageCost += employeeEntry.getValue().getSalary();
                count++;
            }
        }
        averageCost = averageCost / count;
        System.out.println("Средняя ЗП по отделу " + department + " - " + averageCost);
    }

    //индексация ЗП сотрудников одного отдела
    public void indexSalaryCostByDepartment(int department, int percent) {
        for (Map.Entry<String, Employee> employeeEntry : employeeBook.entrySet()) {
            if (employeeEntry.getValue().getDepartment() == department) {
                int currentSalary = employeeEntry.getValue().getSalary();
                int indexSalary = currentSalary + (currentSalary / 100 * percent);
                employeeEntry.getValue().setSalary(indexSalary);
            }
        }
    }

    //напечатать всех сотрудников отдела - все данные, кроме отдела
    public void printEmployeesInDepartment(int department) {
        for (Map.Entry<String, Employee> employeeEntry : employeeBook.entrySet()) {
            if (employeeEntry.getValue().getDepartment() == department) {
                System.out.println(employeeEntry.getValue().getName() + " " +
                        employeeEntry.getValue().getSurname() + " " +
                        employeeEntry.getValue().getSalary() + " " +
                        employeeEntry.getValue().getId() +
                        "\n=======================================");
            }
        }
    }

    // Получить в качестве параметра число и найти:
    //Всех сотрудников с зарплатой меньше числа (вывести id, Ф. И. О. и зарплатой в консоль).
    public void employeesWithLessThenSalary(int paramNum) {
        for (Map.Entry<String, Employee> employeeEntry : employeeBook.entrySet()) {
            if (employeeEntry.getValue().getSalary() < paramNum) {
                System.out.println("Сотрудник с ЗП меньше числа - ID " +
                        employeeEntry.getValue().getId() + " " +
                        employeeEntry.getValue().getName() + " " +
                        employeeEntry.getValue().getSalary());
            }
        }
    }

    //Всех сотрудников с зарплатой больше (или равно) числа (вывести id, Ф. И. О. с зарплатой в консоль).
    public void employeesMoreThanSalary(int paramNum) {
        for (Map.Entry<String, Employee> employeeEntry : employeeBook.entrySet()) {
            if (employeeEntry.getValue().getSalary() >= paramNum) {
                System.out.println("Сотрудники с ЗП выше, чем число - ID " +
                        employeeEntry.getValue().getId() + " " +
                        employeeEntry.getValue().getName() + " " +
                        employeeEntry.getValue().getSalary());
            }
        }
    }

    //обновление даных о сотруднике
    public void updateEmployee(String name, String surname, int salary, int department) {
        for (Map.Entry<String, Employee> employeeEntry : employeeBook.entrySet()) {
            if (employeeEntry.getValue().getName().equalsIgnoreCase(name) &&
                    employeeEntry.getValue().getSurname().equalsIgnoreCase(surname)) {
                employeeEntry.getValue().setSalary(salary);
                employeeEntry.getValue().setDepartment(department);
            }
        }
    }

    //напечатать список отделов и их сотрудников
    public void printAllDepartmentsAndNames() {
        int department = 1;
        while (department <= idDepartment) {
            for (Map.Entry<String, Employee> employeeEntry : employeeBook.entrySet()) {
                if (employeeEntry.getValue().getDepartment() == department) {
                    System.out.println("Отдел - " + department + " ");
                    System.out.println("ФИО " + employeeEntry.getValue().getName() + " " +
                            employeeEntry.getValue().getSurname());
                }
            }
            department++;
        }
    }
}



