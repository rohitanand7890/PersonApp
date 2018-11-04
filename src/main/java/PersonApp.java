import dao.PersonCache;
import dao.PostgresPersonDAO;
import service.PersonService;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;

import java.lang.String;
import java.util.Optional;

public class PersonApp {
    public static void main(String[] args) {
        Connection conn= SingletonDBConnection.getSingleInstance();
        PersonCache personCache =new PersonCache();
        File file = new File("C:\\Users\\rohit_anand_\\Desktop\\text.txt");
        PersonService personService = new PersonService(new PostgresPersonDAO(conn),file, personCache);
        try {
            personService.readPersonsFromFile();
            printAge(personService,"Ram");
            printAge(personService,"Ram");
            printAge(personService,"abbas");
            printAge(personService,"jaadu");
            personService.clearDB();
        }
        catch (Exception e){
            System.out.println("Got some error in connection");
        }
    }
    private static void printAge(PersonService personService, String name) {
        Optional<Integer> optionalAge = personService.getAgeByName(name);
        if( optionalAge.isPresent()) {
            System.out.println(optionalAge.get());
        } else {
            System.out.println(name + " not found");
        }
    }
}
