package de.tum.in.dbpra.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import de.tum.in.dbpra.model.bean.CityBean;
import de.tum.in.dbpra.model.bean.AirportBean;
import de.tum.in.dbpra.model.bean.AirportListBean;




public class AirportDAO extends AbstractDAO {
		
	
	
		AirportListBean getAirportsInCity(CityBean city){
		ArrayList<AirportBean> ab=new ArrayList<AirportBean>();
		
		String query="select * from Airport a where a.city_id=?";
    
        
        try{
            Connection con = getConnection();
            con.setAutoCommit(true);//read only
            PreparedStatement prep = con.prepareStatement(query);
            prep.setInt(1, city.getId());
            
       
    
                ResultSet rs=prep.executeQuery();
                while(rs.next()){
                	AirportBean Ap=new AirportBean();
                	Ap.setIATA(rs.getString(1));
                	Ap.setName(rs.getString(2));
                    ab.add(Ap);
                }

                AirportListBean alb=new AirportListBean();
                alb.setAirportList(ab);
                return alb;
            }catch(SQLException e){
            	System.out.println(e.getMessage());
            	System.exit(0);
            }
        return new AirportListBean();
	}
	int getNumberOfAirports(){
		String query="select count(*) from Airport";
  
        
        try{
            Connection con = getConnection();
            con.setAutoCommit(true);//read only
            PreparedStatement prep = con.prepareStatement(query);
            
            
       
            	int counter=-1;
                ResultSet rs=prep.executeQuery();
                if(rs.next()){
                	counter=rs.getInt(1);
                }


                return counter;
            }catch(SQLException e){
            	System.out.println(e.getMessage());
            	System.exit(0);
            }
        return -1;
		
		
	}
	
	ArrayList<String> getAirports(){
		String query="select iata from Airport";
        try{
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            System.out.println("An error occured. This may likely happen, because the postgres driver is not available. Please make sure, it is available and then retry.\n The exact error, that occured is the following:");
            System.out.println(e.getMessage());
            System.exit(0);
        }   
        
        try{
            Connection con = getConnection();
            con.setAutoCommit(true);//read only
            PreparedStatement prep = con.prepareStatement(query);
            
            
       
            	
                ResultSet rs=prep.executeQuery();
                ArrayList<String> airps=new ArrayList<String>();
                while(rs.next()){
                	airps.add(rs.getString(1));
                }


                return airps;
            }catch(SQLException e){
            	System.out.println(e.getMessage());
            	System.exit(0);
            }
        return null;
		
		
	}
		
	
	
	
}
