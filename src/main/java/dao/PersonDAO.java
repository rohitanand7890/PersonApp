package dao;

import exception.NameNotFoundException;

import java.sql.SQLException;

public interface PersonDAO {
    Integer getAgeByName(String nam) throws SQLException, NameNotFoundException;
    boolean upsertPerson(String name, Integer age)throws SQLException;
    boolean deleteAllPersonRecord() throws SQLException;
}
