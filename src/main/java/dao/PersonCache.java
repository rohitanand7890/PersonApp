package dao;

import exception.NameNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class PersonCache {

    private Map<String,Integer> nameAgeCache =new HashMap();

    public boolean putPerson(String name, int age ){
        nameAgeCache.put(name,age);
        System.out.println("Caching data having name as "+name+" and age as "+age);
        return true;
    }

    public /*Optional< */ Integer /*>*/ getAgeByName(String name) throws NameNotFoundException {
        if (nameAgeCache.containsKey(name)) {
            System.out.println("Returning age from dao.PersonCache");
            return /*Optional.of(*/nameAgeCache.get(name)/*)*/;
        }
        else {
            //return Optional.empty();
            throw new NameNotFoundException(name+ " name not present in personCache record");
        }
    }
}
