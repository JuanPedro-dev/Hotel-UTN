package org.example.Controller;

import org.example.entity.Admin;
import org.example.repository.AdminRepository;

import java.util.List;

public class AdminController {

    AdminRepository adminRepository = new AdminRepository();

    public AdminController() {
    }

    /**
     * Lists all the admins in the admin repository and prints them to the console.
     */
    public void list() {
        adminRepository.list().forEach(System.out::println);
    }

    /**
     * Retrieves a list of all admins from the admin repository.
     *
     * @return A list of all admins.
     */
    public List<Admin> getAllAdmins(){
        return adminRepository.list();
    }

    /**
     * Adds a new admin to the admin repository.
     *
     * @param newAdmin The admin object to be added.
     */
    public void add(Admin newAdmin) {
        adminRepository.add(newAdmin);
    }

    /**
     * Deletes an admin from the admin repository based on the provided DNI (Document Number Identifier).
     *
     * @param dni The DNI of the admin to delete.
     */
    public void delete(Integer dni){
        adminRepository.delete(dni);
    }

    /**
     * Updates an existing admin in the admin repository with the provided newAdmin object.
     *
     * @param newAdmin The updated admin object.
     */
    public void update(Admin newAdmin){
        adminRepository.update(newAdmin);
    }

    /**
     * Retrieves an admin from the admin repository based on the provided DNI (Document Number Identifier).
     *
     * @param dni The DNI of the admin to retrieve.
     * @return The admin object associated with the provided DNI, or null if not found.
     */
    public Admin getById(Integer dni) {
       return adminRepository.getById(dni);
    }
}
