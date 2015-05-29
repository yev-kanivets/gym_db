
package gymapp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
    
    public DBManager() {   
    }
    
    public void createTables() throws SQLException {
        Connection conn = GymConnection.getConnection();
        Statement stmt = conn.createStatement();
        
        String sqlCreateTrainers = "CREATE TABLE Trainers" +
                "(id INT PRIMARY KEY NOT NULL," +
                " name CHAR(30) NOT NULL," +
                " salary INT NOT NULL," +
                " phone CHAR(20))";
        
        String sqlCreateGyms = "CREATE TABLE Gyms" +
                "(id INT PRIMARY KEY NOT NULL," +
                " name CHAR(20) NOT NULL)";
        
        String sqlCreateClients = "CREATE TABLE Clients" +
                "(id INT PRIMARY KEY NOT NULL," +
                " second_name CHAR(20) NOT NULL," +
                " first_name CHAR(10) NOT NULL," +
                " phone CHAR(20)," +
                " trainer_id INT NOT NULL," +
                " FOREIGN KEY(trainer_id) REFERENCES Trainers(id))";
        
        String sqlCreateSubscriptions = "CREATE TABLE Subscriptions" +
                "(id INT PRIMARY KEY NOT NULL," +
                " description CHAR(30) NOT NULL," +
                " price INT NOT NULL," +
                " gym_id INT NOT NULL," +
                " FOREIGN KEY(gym_id) REFERENCES Gyms(id))";
        
        String sqlCreateReports = "CREATE TABLE Reports" +
                "(id INT PRIMARY KEY NOT NULL," +
                " date DATE NOT NULL," +
                " paid INT NOT NULL," +
                " client_id INT NOT NULL," +
                " subscription_id INT NOT NULL," +
                " FOREIGN KEY(client_id) REFERENCES Clients(id)," +
                " FOREIGN KEY(subscription_id) REFERENCES Subscriptions(id))";
        
        stmt.addBatch(sqlCreateTrainers);
        stmt.addBatch(sqlCreateGyms);
        stmt.addBatch(sqlCreateClients);
        stmt.addBatch(sqlCreateSubscriptions);
        stmt.addBatch(sqlCreateReports);
        
        stmt.executeBatch();
        stmt.close();
    }
    
    public void deleteTables() throws SQLException {
        Connection conn = GymConnection.getConnection();
        Statement stmt = conn.createStatement();
        
        String sqlDropTrainers = "DROP TABLE IF EXISTS Trainers";
        String sqlDropGyms = "DROP TABLE IF EXISTS Gyms";
        String sqlDropClients = "DROP TABLE IF EXISTS Clients";
        String sqlDropSubscriptions = "DROP TABLE IF EXISTS Subscriptions";
        String sqlDropReports = "DROP TABLE IF EXISTS Reports";
        
        stmt.addBatch(sqlDropReports);
        stmt.addBatch(sqlDropClients);
        stmt.addBatch(sqlDropTrainers);
        stmt.addBatch(sqlDropSubscriptions);
        stmt.addBatch(sqlDropGyms);
        
        stmt.executeBatch();
        stmt.close();     
    }
}
