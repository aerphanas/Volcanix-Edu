class Employee {
    private String name;
    private String department;
    private int salary;
    public Employee(String name, String department, int salary) {
      this.name = name;
      this.department = department;
      this.salary = salary;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public int getSalary() {
        return salary;
    }
  }
  
  class Manager extends Employee {
    private String title;
    private int bonus;
    public Manager(String name, String department, int salary, String title, int bonus) {
      super(name, department, salary);
      this.title = title;
      this.bonus = bonus;
    }
    
    public String getTitle() {
      return title;
    }
    
    public int getBonus() {
      return bonus;
    }
  }
  
public class Main {
  public static void main(String[] args) {
      Employee employee = new Employee("John Doe", "IT", 45000);
      Manager manager = new Manager("Jane Doe", "HR", 50000, "Director", 10000);
      System.out.println("Employee: " + employee.getName() + ", Department: " + employee.getDepartment() + ", Salary: " + employee.getSalary());
      System.out.println("Manager: " + manager.getName() + ", Department: " + manager.getDepartment() + ", Salary: " + manager.getSalary() + ", Title: " + manager.getTitle() + ", Bonus: " + manager.getBonus());
    }
  }