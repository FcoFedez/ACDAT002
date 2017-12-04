/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad02_apdo02_fernandezfrancisco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author asus410
 */
public class AD02_Apdo02_FernandezFrancisco {
    
    private static final String url = "jdbc:mysql://localhost/empresaz?useSSL=false";
    private static final String user = "root";
    private static final String password = "admin";
    private static  ResultSet result = null;
    private static PreparedStatement pst = null;
    private static Connection conexion = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

        

    }
    
    public static void Ejercicio1(){
                //Empleados que trabajan en un determinado departamento indicado por su nombre.
       
        try{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conexion = DriverManager.getConnection(url,user,password);
        String comando = "SELECT e.emp_no, e.apellido, d.dnombre as departamento "+
                "FROM empleados e " +
                "JOIN departamentos d " +
                "ON e.dept_no = d.dept_no "+
                "WHERE d.dnombre = ?; ";
        pst = (PreparedStatement) conexion.prepareStatement(comando);
        
        pst.setString(1, "CONTABILIDAD");
        
        result =  pst.executeQuery();
        
        while(result.next()){
           
            System.out.printf("%2d  %-15s  %s\n",result.getInt(1), result.getString(2), result.getString(3));
        }
            
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {

                }

            }

            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {

                }
            }

            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {

                }
            }
        }
    }
    
}
