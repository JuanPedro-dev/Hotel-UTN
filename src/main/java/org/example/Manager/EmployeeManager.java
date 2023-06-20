import java.util.ArrayList;

public class EmployeeManager {
    private ArrayList<Employee> employeeList;

    public EmployeeManager(ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employeeList.remove(employee);
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }
}
