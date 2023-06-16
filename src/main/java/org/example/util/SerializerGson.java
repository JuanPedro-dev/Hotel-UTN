package org.example.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.entity.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SerializerGson <T>{
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public SerializerGson() {
    }

    public <T> void serializer(T obj, String filePath) {

        File file = new File(filePath);

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false));

            gson.toJson(obj, bufferedWriter);

            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T deserializer(String filePath, Class<T> tclass) {

        File file = new File(filePath);

        T obj = null;

        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            Type listType = null;


            //Averiguar que texto pone en cada clase =>
//            System.out.println(String.valueOf(tclass));

            // Lo paso a String pues el switch no toma valores Class
            switch (String.valueOf(tclass)){
                case "class org.example.entity.Admin" -> listType = new TypeToken<ArrayList<Admin>>(){}.getType();
                case "class org.example.entity.Booking" -> listType = new TypeToken<ArrayList<Booking>>(){}.getType();
                case "class org.example.entity.Employee" -> listType = new TypeToken<ArrayList<Employee>>(){}.getType();
                case "class org.example.entity.Guest" -> listType = new TypeToken<ArrayList<Guest>>(){}.getType();
                case "class org.example.entity.Hotel" -> listType = new TypeToken<ArrayList<Hotel>>(){}.getType();
                case "class org.example.entity.Room" -> listType = new TypeToken<ArrayList<Room>>(){}.getType();

            }

            obj = (T) gson.fromJson(bufferedReader, listType);

            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error en la deserialización!!!!");
            throw new RuntimeException(e);
        }
        return obj;
    }

}
