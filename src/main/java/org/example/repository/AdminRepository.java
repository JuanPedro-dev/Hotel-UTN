package org.example.repository;

import org.example.entity.Admin;
import org.example.exceptions.AdminExceptions;
import org.example.util.SerializerGson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository implements IRepository<Admin, Integer>{

    private final File file = new File("src/main/java/org/example/file/AdminFile.json");
    private final SerializerGson gson =new SerializerGson<>();
    private List<Admin> admins;

    public AdminRepository() {
        readFromFile();
    }

    @Override
    public void readFromFile() {
        admins = (List<Admin>) gson.deserializer(file.getPath(), Admin.class);
        if(admins == null) admins = new ArrayList<>();
    }

    @Override
    public void saveToFile() {
        gson.serializer(this.admins, file.getPath());
    }

    @Override
    public void add(Admin obj) {
        Admin toAdd = getById(obj.getDni());

        if ( toAdd == null ) {
            this.admins.add(obj);
            saveToFile();
        } else{
            throw new AdminExceptions("El administrador ya existe!");
        }

    }

    @Override
    public List<Admin> list() {
        return this.admins;
    }

    @Override
    public Admin getById( Integer dni ) {

        for ( Admin user: this.admins ) {
            if(user.getDni().equals(dni)) return user;
        }

        return null;
    }

    @Override
    public void update(Admin obj) {

        Admin toUpdate = getById(obj.getDni());

        if ( toUpdate == null ) {
            throw new AdminExceptions("El Administrador no Existe!");
        } else{
            for (Admin admin : this.admins) {
                if(admin.getDni().equals(obj.getDni())){
                    int index = admins.indexOf(admin);
                    admins.set( index, obj );
                }
            }
        }

        saveToFile();
    }

    @Override
    public void delete(Integer dni) {
        if(this.admins.removeIf(admin -> admin.getDni().equals(dni))){
            saveToFile();
            System.out.println("Eliminado exitosamente");
        }
        else {
            throw new AdminExceptions("No se pudo eliminar el empleado");
        }

    }


}
