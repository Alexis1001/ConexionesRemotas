/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionesremotas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;
import java.sql.*;


/**
 *
 * @author PC
 */
public class ConexionesRemotas {

    
    public static void main(String[] args) throws ClassNotFoundException {
     
        int opcion=0;
        Conexiones conexion = new Conexiones();
        Scanner entrada=new Scanner(System.in);
        
        
        
   do {
            System.out.println("1...MySql");
            System.out.println("2...SqlServer");
            System.out.println("3...PostgreSql");
            System.out.println("4...DB2");
            System.out.println("5...TODOS");
            System.out.println("ingrese la opcion");
            opcion=entrada.nextInt();
   
         switch(opcion){
             case 1:
             conexion.MySql();
             break;
             
             case 2:
             conexion.SqlServer();
             break;
             
             case 3:
             conexion.PostgreSql();  
             break;
             
             case 4:
             conexion.DB2();
             break;
             
             case 5:
             conexion.MySql();
             conexion.SqlServer();
             conexion.PostgreSql(); 
             conexion.DB2();
             break; 
             
             default :
                 System.out.println("opcion invalida");
         }   
        
          
        }while(opcion>0&&opcion<5);
        
    
        
           
       
}
  
   }

class Conexiones {


 public Conexiones(){
   
  
   }
 
 
public void MySql(){
   
System.out.println("MySQL");

try { 
    String Comando = "Select * from alumnos";
    String url = "jdbc:mysql://192.168.1.107:3306/equipox";
    Class.forName("com.mysql.jdbc.Driver").newInstance();

    Date date = new Date();
    Connection conn = DriverManager.getConnection(url,"salvador","12345"); 
    Statement st = conn.createStatement();
   //System.out.println(date.toString());

    ResultSet rs = st.executeQuery(Comando);
                     
    while(rs.next()){
          System.out.println("id Alumno : "+rs.getInt (1) +"Nombre Usuario : " + rs.getString (2)+"id ciudad :"+ rs.getInt(3));
          }
                     
   date = new Date();
   //System.out.println(date.toString());
    conn.close(); 
	
  
        
}catch (Exception e) { 
 System.err.println("Got an exception! "); 
 System.err.println("el erro es "+e); 
 }
    

   }


public void SqlServer() throws ClassNotFoundException{

System.out.println("SQL Server");

ResultSet res=null;

 try {
                        
    Connection Conexion;
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    String url = "jdbc:sqlserver://192.168.43.246:1433;databaseName=ciudades";
    Connection con = (Connection) DriverManager.getConnection(url,"salvador","123456");
    
    String sql = "SELECT * FROM ciudad";
    java.sql.PreparedStatement stm = con.prepareStatement(sql);
    res = stm.executeQuery();

    while(res.next()) {
      System.out.println("id Alumno :"+res.getString(1)+" Nombre de usuario: "+res.getString(2)+" id ciudad :"+res.getString(3));
          }
			
			
    }catch(SQLException e) {
	   System.out.println("error al conectar "+e);
	   }    
    
   }


public void PostgreSql(){
    
System.out.println("Postgres");    
String SQL = "SELECT * FROM estados";

    try {
        String url = "jdbc:postgresql://192.168.43.97/prueba";
        Class.forName("org.postgresql.Driver").newInstance();
        Connection conn = DriverManager.getConnection(url, "postgres", "postgres");
           
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
      //  int cont =0;
             
        while(rs.next()){
             // cont = cont+1;
             // System.out.println("datos"+cont);
              System.out.println(rs.getString(1) + " "+ rs.getString(2) + " "+ rs.getString(3));
              }
            
    }catch (Exception e) {
     System.err.println("Got an exception! ");
     System.err.println(e);
        }

}


public void DB2(){

String driver="com.ibm.db2.jcc.DB2Driver";
String user="E552283HB";
String password="laptopdiego";
String url="jdbc:db2://192.168.43.205:50000/equipox";
int idciudad, idalumno;
    
     
try {
      
  int codigo=0;
  Class.forName(driver).newInstance();
  Connection conn = DriverManager.getConnection(url,user,password); 
  Statement stmt = conn.createStatement();
  ResultSet rs = stmt.executeQuery("SELECT * FROM ciudades");
            
  while(rs.next()){
        codigo++;
        System.out.println("No de Datos: "+codigo);
        System.out.println(rs.getInt(1) + "\t"+ rs.getString(2));
        }
       
      conn.close(); 
     // System.out.println("Insertado");  
     }catch(Exception e) { 
        System.err.println("Got an exception! "); 
        System.err.println(e); 
        }
  
 }



}




