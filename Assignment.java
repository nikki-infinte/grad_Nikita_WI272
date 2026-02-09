
import java.util.*;
import java.util.stream.*;

class Assignment {

    public static String findHighestSalary(List<Empployee> empList) {
        IntSummaryStatistics stats = empList.stream().mapToInt(e -> e.salary).summaryStatistics();
        return "Highest Salary: " + stats.getMax();
    }

    public static void Countgender(List<Empployee> empList) {
        Map<gender, Long> genderCount
                = empList.stream()
                        .collect(Collectors.groupingBy(e -> e.g, Collectors.counting()));
        System.out.println(genderCount);
    }

    public static void main(String[] args) {

        List<Empployee> empList = new ArrayList<>();
        empList.add(new Empployee("Jetha", "Manager", "Sales", 50000, 45, gender.MALE));
        empList.add(new Empployee("Daya", "Developer", "IT", 40000, 35, gender.FEMALE));
        empList.add(new Empployee("Tipendra", "Analyst", "Finance", 45000, 25, gender.MALE));
        empList.add(new Empployee("Champaklal", "Designer", "Marketing", 50000, 70, gender.MALE));
        empList.add(new Empployee("Iyer", "Architect", "IT", 65000, 42, gender.MALE));
        empList.add(new Empployee("Babita", "HR", "HR", 48000, 38, gender.FEMALE));
        empList.add(new Empployee("Popatlal", "Reporter", "Media", 43000, 50, gender.MALE));
        empList.add(new Empployee("Madhvi", "Accountant", "Finance", 52000, 40, gender.FEMALE));

        // 1 find highest salary paid Employee using Stream API
        System.out.println(findHighestSalary(empList));

        // 2. Find how many male & female employees working in company
        Countgender(empList);

        //3 .Total expense for company department wise
        Map<String, Integer> deptExpense = empList.stream()
                .collect(Collectors.groupingBy(e -> e.department, Collectors.summingInt(e -> e.salary)));
        System.out.println(deptExpense);

        // 4 .Who is the top 5 senior employees in the company
        empList.stream()
       .sorted(Comparator.comparingInt((Empployee e) -> e.age).reversed())
       .limit(5)
       .forEach(System.out::println);


        // 5. Find only the names who all are managers
        System.out.println("Managers:");
        empList.stream()
                .filter(e -> e.designation.equals("Manager"))
                .map(e -> e.name)
                .forEach(System.out::println);

        // 6. Hike the salary by 20% for everyone except manager
        System.out.println("Employees after 20% hike (except Manager):");
        empList.forEach(System.out::println);

        empList.stream()
                .filter(e -> !e.designation.equals("Manager"))
                .forEach(e -> e.salary = (int) (e.salary * 1.2));

        // 7. Find the total number of employees
        long totalEmployees = empList.stream().count();
        System.out.println("Total Employees: " + totalEmployees);

    }
}

enum gender {
    MALE, FEMALE;
}

class Empployee {

    String name, designation, department;
    int salary, age;
    gender g;

    public Empployee(String name, String designation, String department, int salary, int age, gender g) {
        this.name = name;
        this.designation = designation;
        this.department = department;
        this.salary = salary;
        this.age = age;
        this.g = g;
    }

    public String toString() {
        return "Name: " + name + " Designation: " + designation + " Department: " + department + " Salary: " + salary + " Age: " + age + " Gender: " + g;
    }

}
