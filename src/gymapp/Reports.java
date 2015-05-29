/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymapp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Reports {
    
    public void filterData(Date startDate, Date endDate) throws SQLException, ParseException {
        if(tablesIsEmpty()) {
            fillTables();
        }
        
        final String format = "%-3s  %-10s  %-11s  %-10s %-10s";
        ConsoleWriter cw = ConsoleWriter.getInstance();
        
        Connection conn = GymConnection.getConnection();
        Statement stmt = conn.createStatement();
        
        String sqlSelect = "SELECT Reports.id, date, paid, second_name, first_name FROM Reports, Clients WHERE (date BETWEEN '" +
                startDate + "' AND '" + endDate + "') AND (client_id = Clients.id)";
        
        String header = String.format(format, "№", "Date", "Second name", "First name", "Paid");
        cw.write(header);
        
        ResultSet rs = stmt.executeQuery(sqlSelect);
        while(rs.next()) {
            int id = rs.getInt("id");
            String date = rs.getString("date");
            int paid = rs.getInt("paid");
            String secondName = rs.getString("second_name");
            String firstName = rs.getString("first_name");
            
            String row = String.format(format, id, date, secondName, firstName, (paid == 1)?"paid":"not paid");
            cw.write(row);
        }
        
        rs.close();
        stmt.close();
    }
    
    private void fillTables() throws SQLException {
        Connection conn = GymConnection.getConnection();
        Statement stmt = conn.createStatement();
        
        String sqlInsertGym1 = "INSERT INTO Gyms VALUES(1, 'Fiyness gym')";
        String sqlInsertGym2 = "INSERT INTO Gyms VALUES(2, 'Swimming-pool')";
        
        String sqlInsertSubscription1 = "INSERT INTO Subscriptions VALUES(1, '2 times per week', 300, 1)";
        String sqlInsertSubscription2 = "INSERT INTO Subscriptions VALUES(2, '4 times per week', 500, 1)";
        String sqlInsertSubscription3 = "INSERT INTO Subscriptions VALUES(3, '2 times per week', 350, 2)";
        String sqlInsertSubscription4 = "INSERT INTO Subscriptions VALUES(4, '4 times per week', 600, 2)";
        
        String sqlInsertReports1 = "INSERT INTO Reports VALUES(1, '2015-05-01', 0, 1, 1)";
        String sqlInsertReports2 = "INSERT INTO Reports VALUES(2, '2015-05-01', 1, 2, 1)";
        String sqlInsertReports3 = "INSERT INTO Reports VALUES(3, '2015-05-02', 1, 2, 4)";
        String sqlInsertReports4 = "INSERT INTO Reports VALUES(4, '2015-05-03', 1, 1, 3)";
        String sqlInsertReports5 = "INSERT INTO Reports VALUES(5, '2014-06-01', 1, 1, 2)";
        String sqlInsertReports6 = "INSERT INTO Reports VALUES(6, '2014-06-01', 0, 2, 1)";
        String sqlInsertReports7 = "INSERT INTO Reports VALUES(7, '2014-07-02', 1, 2, 4)";
        String sqlInsertReports8 = "INSERT INTO Reports VALUES(8, '2014-08-03', 0, 1, 3)";
        
        stmt.addBatch(sqlInsertGym1);
        stmt.addBatch(sqlInsertGym2);
        stmt.addBatch(sqlInsertSubscription1);
        stmt.addBatch(sqlInsertSubscription2);
        stmt.addBatch(sqlInsertSubscription3);
        stmt.addBatch(sqlInsertSubscription4);
        stmt.addBatch(sqlInsertReports1);
        stmt.addBatch(sqlInsertReports2);
        stmt.addBatch(sqlInsertReports3);
        stmt.addBatch(sqlInsertReports4);
        stmt.addBatch(sqlInsertReports5);
        stmt.addBatch(sqlInsertReports6);
        stmt.addBatch(sqlInsertReports7);
        stmt.addBatch(sqlInsertReports8);
        
        stmt.executeBatch();
        stmt.close();
    }

    private boolean tablesIsEmpty() throws SQLException {
        boolean result = false;
        Connection conn = GymConnection.getConnection();
        Statement stmt = conn.createStatement();
        
        String sqlCountOfRows = "SELECT COUNT(id) from Reports";
        
        ResultSet rs = stmt.executeQuery(sqlCountOfRows);
        if(rs != null) {
            result = (rs.getInt(1) == 0);
        }
        
        rs.close();
        stmt.close();
        return result;
    }
}
