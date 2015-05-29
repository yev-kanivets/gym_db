/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author апа
 */
public class Trainers {
    
    public void addTrainer() throws SQLException {
        Connection conn = GymConnection.getConnection();
        
        Statement stmt = conn.createStatement();
        String sqlInsertTrainer1 = "INSERT INTO Trainers VALUES(1, 'Ivanov Ivan', 3000, '099-999-99-22')";
        String sqlInsertTrainer2 = "INSERT INTO Trainers VALUES(2, 'Petrov Ivan', 5000, '099-888-99-33')";
        String sqlInsertTrainer3 = "INSERT INTO Trainers VALUES(3, 'Sidorov Petro', 3500, '099-777-99-44')";
        
        stmt.addBatch(sqlInsertTrainer1);
        stmt.addBatch(sqlInsertTrainer2);
        stmt.addBatch(sqlInsertTrainer3);
        
        stmt.executeBatch();
        stmt.close();
    }
    
    public void removeTrainer() throws SQLException {
        Connection conn = GymConnection.getConnection();
        
        Statement stmt = conn.createStatement();
        String sqlRemoveTrainer = "DELETE FROM Trainers WHERE id=2";
        
        stmt.execute(sqlRemoveTrainer);
        stmt.close();
    }
    
    public void showTrainers() throws SQLException {
        final String format = "%-8s  %-20s  %-10s  %-20s";
        ConsoleWriter cw = ConsoleWriter.getInstance();
        Connection conn = GymConnection.getConnection();
        
        Statement stmt = conn.createStatement();
        String sqlSelectAll = "SELECT * FROM Trainers";
        
        ResultSet rs = stmt.executeQuery(sqlSelectAll);
        
        String header = String.format(format, "№", "Name", "Salary", "Phone");
        cw.write(header);
        
        while(rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int salary = rs.getInt("salary");
            String phone = rs.getString("phone");
            
            String row = String.format(format, id, name, salary, phone);
            cw.write(row);
        }
        rs.close();
        stmt.close();
    }
}
        
