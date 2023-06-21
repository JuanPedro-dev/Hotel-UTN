package org.example.Controller;

import org.example.entity.Employee;
import org.example.exceptions.AdminExceptions;
import org.example.exceptions.EmployeeExceptions;
import org.example.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class EmployeeController {
    private static final Scanner scanner = new Scanner(System.in);
    EmployeeRepository employeeRepository = new EmployeeRepository();

    public EmployeeController() {
    }


    /**
     * Lists all the employees in the employees repository and prints them to the console.
     */
    public void list() { employeeRepository.list().forEach(System.out::println);}

    /**
     * Retrieves a list of all employees from the employees repository.
     *
     * @return A list of all employees.
     */
    public List<Employee> getAllEmployees() {return employeeRepository.list();}


    /**
     * Adds a new employee to the employee repository.
     *
     * @param newEmployee The employee object to be added.
     */
    public void add(Employee newEmployee) {employeeRepository.add(newEmployee);}


    /**
     * Deletes an employee from the employee repository based on the provided DNI (Document Number Identifier).
     *
     * @param dni The DNI of the employee to delete.
     */
    public void delete(Integer dni){employeeRepository.delete(dni);}


    /**
     * Updates an employee with new attribute values based on user input.
     *
     * @param updateEmployee The Employee object to be updated.
     */
    public void update(Employee updateEmployee){
        String flag ;
        String option;
        do {
            if (updateEmployee != null) {
                System.out.println("Seleccione atributo a cambiar");
                System.out.println("1. Nombre");
                System.out.println("2. Apellido");
                System.out.println("3. Teléfono");
                System.out.println("4. Email");
                System.out.println("5. Usuario");
                System.out.println("5. Password");
                option = scanner.nextLine();
                switch (option) {
                    case "1" -> {
                        System.out.println("Ingrese nuevo nombre");
                        updateEmployee.setName(scanner.nextLine());
                        System.out.println("Modificación exitosa");
                    }
                    case "2" -> {
                        System.out.println("Ingrese nuevo apellido");
                        updateEmployee.setLastName(scanner.nextLine());
                        System.out.println("Modificación exitosa");
                    }
                    case "3" -> {
                        System.out.println("Ingrese nuevo teléfono");
                        updateEmployee.setPhoneNumber(scanner.nextInt());
                        System.out.println("Modificación exitosa");
                    }
                    case "4" -> {
                        System.out.println("Ingrese nuevo email");
                        updateEmployee.setEmail(scanner.nextLine());
                        System.out.println("Modificación exitosa");
                    }
                    case "5" -> {
                        System.out.println("Ingrese nuevo user");
                        updateEmployee.setUser(scanner.nextLine());
                        System.out.println("Modificación exitosa");
                    }
                    case "6" -> {
                        System.out.println("Ingrese nuevo password");
                        updateEmployee.setPassword(scanner.nextLine());
                        System.out.println("Modificación exitosa");
                    }
                }
            }

            System.out.println("Quiere cambiar otro atributo? S/N");
            flag = scanner.nextLine().toUpperCase();
        } while (flag.equals("S"));

        employeeRepository.update(updateEmployee);
    }


    /**
     * Retrieves an employee from the employee repository based on the provided DNI (Document Number Identifier).
     *
     * @param dni The DNI (Identification Document Number) of the employee to retrieve.
     * @return The employee object associated with the provided DNI, or null if not found.
     */
    public Employee getById(Integer dni) {
        try {
            return Optional.ofNullable(
                    employeeRepository.getById(dni)).orElseThrow(
                    ()-> new EmployeeExceptions("No se encontro el empleado buscado."));
        } catch (EmployeeExceptions e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static void viewEmployeeMenu(){
        System.out.println("*-*-*-*-*-*-*-***Bates Motel****-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-***Menu Empleado****-*-*-*-*-*");
        System.out.println("1. Reserva");
        System.out.println("2. Huesped");
        System.out.println("3. Habitaciones");
        System.out.println("0. Salir");
    }

    public static void employeeMenu(Scanner scanner){
        boolean flag = true;
        do {
            viewEmployeeMenu();
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    // ToDo llamar al menu de reservas
                    BookingController.bookingMenu(scanner);
                    break;
                case "2":
                    // ToDo llamar al menu de huespedes
                    GuestController.guestMenu(scanner);
                    break;
                case "3":
                    // ToDo llamar al menu de habitaciones
                    RoomController.roomMenu(scanner);
                    break;
                case "0":
                    HotelController.login();
                    break;
                default:
                    System.out.println("Ingreso incorrectamente.");
            }
        }while(flag);
    }
}
