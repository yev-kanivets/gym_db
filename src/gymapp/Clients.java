/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymapp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author апа
 */
public class Clients {
    
    private void addClient() throws SQLException {
        Connection conn = GymConnection.getConnection();
        
        Statement stmt = conn.createStatement();
        String sqlInsertClient = "INSERT INTO Clients VALUES(1, )";
    }
    
    private void deleteClient() {
        
    }
    
    private void showClients() {
        
    }
}
