package dao;

import exception.NameNotFoundException;

import java.sql.*;

public class PostgresPersonDAO implements  PersonDAO{
    private Connection connection;
    public PostgresPersonDAO(Connection connection) {
        this.connection = connection;
    }
     public Integer getAgeByName(String name) throws SQLException, NameNotFoundException {
        int age;
        PreparedStatement fetchAge = connection.prepareStatement("select age from public.record where name='" + name + "'");
        ResultSet resultSet = fetchAge.executeQuery();
        while(resultSet.next()) {
            age = resultSet.getInt("age");
            System.out.println("Returning age from Database");
            fetchAge.close();
            return age;
        }
        throw new NameNotFoundException(name+" name not found in db");
    }
    public boolean upsertPerson(String name, Integer age)throws SQLException {
        CallableStatement executeProcedure = connection.prepareCall("{call public.add_person_details( ?, ? ) }");
        executeProcedure.setString(1, name);
        executeProcedure.setInt(2, age);
        boolean result=executeProcedure.execute();
        executeProcedure.close();
        return result;
    }
    public boolean deleteAllPersonRecord() throws SQLException {

        Statement statement = connection.createStatement();
        statement.executeUpdate("delete from public.record ");
        System.out.println("All records deleted from Database table");
        statement.close();
        return true;
    }
}
