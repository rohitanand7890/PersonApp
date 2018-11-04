package service;

import dao.PersonCache;
import dao.PostgresPersonDAO;
import dao.PersonDAO;
import exception.NameNotFoundException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.Optional;

public class PersonService {
    private PersonDAO db ;
    private File file;
    private PersonCache personCache;
    public PersonService(PersonDAO personDAO, File file, PersonCache personCache) {
        this.db = personDAO ;
        this.file=file;
        this.personCache = personCache;
    }

    public  void readPersonsFromFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(" ");
                String name = tokens[0];
                int age = Integer.parseInt(tokens[1]);
                boolean result=db.upsertPerson(name,age);
                System.out.println(name+" and "+age+" added to database: "+result);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    public Optional<Integer> getAgeByName(String name) {
        try {
            /*Optional<Integer> optionalAge =  personCache.getAgeByName(name);
            if(  personCache.getAgeByName(name).isPresent()) {
                return optionalAge.get();
            } else {
                db.getAgeByName(name);
            } */
            return Optional.of(personCache.getAgeByName(name));
        } catch (NameNotFoundException e1){
            try {
                int age = db.getAgeByName(name);
                boolean result=personCache.putPerson(name, age);
                System.out.println(name + " and " + age + " cached");
                return Optional.of(age);
            } catch (NameNotFoundException e) {
                System.out.println(e.getMessage());
            } catch ( SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return Optional.empty();

    }
    public String clearDB(){
        try {
            boolean result =db.deleteAllPersonRecord();
            return "Database table delete successful "+result;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return "delete failed";
    }

}

