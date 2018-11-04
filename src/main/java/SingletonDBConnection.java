import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonDBConnection {
    private  static Connection singleInstance= null;
    private SingletonDBConnection(){
    }
    public static Connection getSingleInstance(){
        if (singleInstance == null){
            synchronized (SingletonDBConnection.class){
                try {
                    Config conf =  ConfigFactory.load();
                    Config postgresConf = conf.getConfig("postgres");
                    int port = postgresConf.getInt("port");
                    String host = postgresConf.getString("host");
                    String username = postgresConf.getString("username");
                    String password = postgresConf.getString("password");
                    Class.forName("org.postgresql.Driver");
                    singleInstance =  DriverManager.getConnection(
                            "jdbc:postgresql://"+host+":"+port+"/postgres", username, password);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return singleInstance;
    }
}
