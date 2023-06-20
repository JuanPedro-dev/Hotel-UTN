package org.example.repository;

import org.example.entity.Employee;
import org.example.exceptions.EmployeeExceptions;
import org.example.util.SerializerGson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements IRepository<Employee, Integer>{
    private final File file = new File("src/main/java/org/example/file/EmployeeFile.json");
    private final SerializerGson gson =new SerializerGson<>();
    private List<Employee> employees;

    public EmployeeRepository() {
        readFromFile();
    }

    @Override
    public void readFromFile() {
        employees = (List<Employee>) gson.deserializer(file.getPath(), Employee.class);
        if(employees == null) employees = new ArrayList<>();
    }

    @Override
    public void saveToFile() {
        gson.serializer(this.employees, file.getPath());
    }

    @Override
    public void add(Employee obj) {
        Employee toAdd = getById(obj.getDni());

        if ( toAdd == null ) {
            this.employees.add(obj);
            saveToFile();
        } else{
            throw new EmployeeExceptions("El empleado ya existe!");
        }
    }

    @Override
    public List<Employee> list() {
        return this.employees;
    }

    @Override
    public Employee getById( Integer dni ) {

        for ( Employee employee: this.employees ) {
            if(employee.getDni().equals(dni)) return employee;
        }

        return null;
    }

    @Override
    public void update(Employee obj) {

        Employee toUpdate = getById(obj.getDni());

        if ( toUpdate == null ) {
            throw new EmployeeExceptions("El empleado no Existe!");
        } else{
            for (Employee employee : this.employees) {
                if(employee.getDni().equals(obj.getDni())){
                    int index = employees.indexOf(employee);
                    employees.set( index, obj );
                }
            }
        }

        saveToFile();
    }

    @Override
    public void delete(Integer dni) {
        if (this.employees.removeIf(employee -> employee.getDni().equals(dni))) {
            saveToFile();
            System.out.println("Eliminado exitosamente");
        }
        else {
            throw new EmployeeExceptions("No se pudo eliminar el empleado");
        }

    }


}
