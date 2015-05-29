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
public class Clients {
    
    public void addClient() throws SQLException {
        Connection conn = GymConnection.getConnection();
        
        Statement stmt = conn.createStatement();
        String sqlInsertClient1 = "INSERT INTO Clients VALUES(1, 'Dubovska', 'Alla', '099-577-98-34', 3)";
        String sqlInsertClient2 = "INSERT INTO Clients VALUES(2, 'Kanivets', 'Eugine', '095-567-98-34', 1)";
        String sqlInsertClient3 = "INSERT INTO Clients VALUES(3, 'Kapusta', 'Julia', '066-577-78-35', 3)";
        
        stmt.addBatch(sqlInsertClient1);
        stmt.addBatch(sqlInsertClient2);
        stmt.addBatch(sqlInsertClient3);
        
        stmt.executeBatch();
        stmt.close();
    }
    
    public void removeClient() throws SQLException {
        Connection conn = GymConnection.getConnection();
        
        Statement stmt = conn.createStatement();
        String sqlRemoveClient = "DELETE FROM Clients WHERE second_name='Kapusta'";
        
        stmt.execute(sqlRemoveClient);
        stmt.close();
    }
    
    public void showClients() throws SQLException {
        final String format = "%-5s  %-11s  %-10s  %-15s %-20s";
        ConsoleWriter cw = ConsoleWriter.getInstance();
        Connection conn = GymConnection.getConnection();
        
        Statement stmt = conn.createStatement();
        String sqlSelectAll = "SELECT Clients.id, second_name, first_name, Clients.phone, name FROM Clients, Trainers WHERE Clients.trainer_id = Trainers.id";
        
        ResultSet rs = stmt.executeQuery(sqlSelectAll);
        
        String header = String.format(format, "№", "Second name", "First name", "Phone", "Trainer");
        cw.write(header);
        
        while(rs.next()) {
            int id = rs.getInt("id");
            String s_name = rs.getString("second_name");
            String f_name = rs.getString("first_name");
            String phone = rs.getString("phone");
            String trainer = rs.getString("name");
            
            String row = String.format(format, id, s_name, f_name, phone, trainer);
            cw.write(row);
        }
        rs.close();
        stmt.close();
    }
}
