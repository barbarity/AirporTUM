

package de.tum.in.dbpra.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;

import de.tum.in.dbpra.model.bean.FlightBean;
import de.tum.in.dbpra.model.bean.CityBean;
import de.tum.in.dbpra.model.bean.AirportBean;


public class FlightDAO extends AbstractDAO {

	
	
	public FlightBean getFlightById(FlightBean fb) throws FlightNotFoundException, SQLException {
		String query = "select f.*, r.*, r.departureTime+cstart.timezone, r.departureTime+r.duration+cend.timezone, cstart.*, cend.*, astart.*, aend.* from Flight f, Route r, City cstart, City cend, Airport astart, Airport aend "
 +"where astart.iata=r.departure_iata and aend.iata=arrival_iata and aend.city_id=cend.city_id and astart.city_id=cstart.city_id and f.route_id=r.route_id and f.flight_id=? ";
				
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			 preparedStatement.setInt(1, fb.getFlightId());
			
			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				if (resultSet.next()) {
					fb.setFlightId(resultSet.getInt(1));
                    fb.setRouteId(resultSet.getInt(2));
                    fb.setDate(resultSet.getDate(4));
                    fb.setLocalDepartureTime(resultSet.getTime(18));
                    fb.setLocalArrivalTime(resultSet.getTime(19));
                    fb.setDuration(resultSet.getTime(15));
                    fb.setGateNr(resultSet.getString(7));
                    fb.setOperatingAirline(resultSet.getString(13));
                    AirportBean ap=new AirportBean();
                    AirportBean ap2=new AirportBean();
                    ap.setIATA(resultSet.getString(30));
                    ap.setName(resultSet.getString(31));
                    ap2.setIATA(resultSet.getString(33));
                    ap2.setName(resultSet.getString(34));
                    fb.setDepartureAirport(ap);
                    fb.setArrivalAirport(ap2);
                    CityBean ct=new CityBean();
                    CityBean ct2=new CityBean();
                    ct.setId(resultSet.getInt(20));
                    ct.setName(resultSet.getString(23));
                    ct.setCountryName(resultSet.getString(21));
                    ct2.setId(resultSet.getInt(25));
                    ct2.setName(resultSet.getString(28));
                    ct2.setCountryName(resultSet.getString(26));
                    fb.setDepartureCity(ct);
                    fb.setArrivalCity(ct2);
                    fb.setDistance(resultSet.getInt(17));
                    fb.setOperatingFlightNumber(resultSet.getString(12));
				} 
				else{
					throw new FlightNotFoundException("Database found no flight for the given id!");
				}
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return fb;
	}
	
	

	//free seats!!!!!!!!!!!!!
    
    ArrayList<FlightBean> getFlightsFromTo(Timestamp timeFrom, Timestamp timeTo,
    AirportBean start, AirportBean end, String flightClass,int numberPersons) throws SQLException{
    	System.out.println("In the getFlightsFromTo-Method:");
    	System.out.println("Arguments: ");
    	System.out.println("    TimeFrom:     "+timeFrom.toString());
    	System.out.println("    TimeTo:       "+timeTo.toString());
    	System.out.println("    start_airport:"+start.getIATA());
    	System.out.println("    end_airport:  "+end.getIATA());
    	System.out.println("    flightClass:  "+flightClass);
    	System.out.println("    persons:      "+numberPersons);
    	
    	
        String query;
        if(flightClass.compareTo("economy")==0){
        	query="with takenseats as (select fl.flight_id as fl,  coalesce(count(fst.fst_id),0) as seats from Airplane ap, Flight fl left outer join (select * from FlightSegmentTicket where class='economy') fst on (fst.flight_id=fl.flight_id) where ap.registration_num=fl.airplane_registration_num  group by fl.flight_id)"
        			+

        		"select *,f.date+r.departureTime+r.duration-cend.timezone as locArrTime,f.date+r.departureTime-cstart.timezone as locDepTime  from Flight f, Route r, City cstart, City cend, Airport astart, Airport aend, takenseats fs, Airplane ap where departure_iata=? "
        		        +"        and f.date+r.departureTime-cstart.timezone<=? and f.date+r.departureTime-cstart.timezone>=? and astart.iata=r.departure_iata and aend.iata=arrival_iata and" 
        		          +"      aend.city_id=cend.city_id and astart.city_id=cstart.city_id and aend.iata=? and f.route_id=r.route_id and fs.fl=f.flight_id and ap.economyClassSeats-seats>=? and ap.registration_num=f.airplane_registration_num";

        }
        else if(flightClass.compareTo("business")==0){
        	query="with takenseats as (select fl.flight_id as fl,  coalesce(count(fst.fst_id),0) as seats from Airplane ap, Flight fl left outer join (select * from FlightSegmentTicket where class='business') fst on (fst.flight_id=fl.flight_id) where ap.registration_num=fl.airplane_registration_num  group by fl.flight_id)"
        			+

        		"select *,f.date+r.departureTime+r.duration-cend.timezone as locArrTime,f.date+r.departureTime-cstart.timezone as locDepTime  from Flight f, Route r, City cstart, City cend, Airport astart, Airport aend, takenseats fs, Airplane ap where departure_iata=? "
        		        +"        and f.date+r.departureTime-cstart.timezone<=? and f.date+r.departureTime-cstart.timezone>=? and astart.iata=r.departure_iata and aend.iata=arrival_iata and" 
        		          +"      aend.city_id=cend.city_id and astart.city_id=cstart.city_id and aend.iata=? and f.route_id=r.route_id and fs.fl=f.flight_id and ap.businessClassSeats-seats>=? and ap.registration_num=f.airplane_registration_num";

        }
        else{
        	query="with takenseats as (select fl.flight_id as fl,  coalesce(count(fst.fst_id),0) as seats from Airplane ap, Flight fl left outer join (select * from FlightSegmentTicket where class='first') fst on (fst.flight_id=fl.flight_id) where ap.registration_num=fl.airplane_registration_num  group by fl.flight_id)"
        			+

        		"select *,f.date+r.departureTime+r.duration-cend.timezone as locArrTime,f.date+r.departureTime-cstart.timezone as locDepTime from Flight f, Route r, City cstart, City cend, Airport astart, Airport aend, takenseats fs, Airplane ap where departure_iata=? "
        		        +"        and f.date+r.departureTime-cstart.timezone<=? and f.date+r.departureTime-cstart.timezone>=? and astart.iata=r.departure_iata and aend.iata=arrival_iata and" 
        		          +"      aend.city_id=cend.city_id and astart.city_id=cstart.city_id and aend.iata=? and f.route_id=r.route_id and fs.fl=f.flight_id and ap.firstClassSeats-seats>=? and ap.registration_num=f.airplane_registration_num";

  
        }
        ArrayList<FlightBean> al=new ArrayList<FlightBean>();
        
        try{
            Connection con = getConnection();
            con.setAutoCommit(true);//read only
            PreparedStatement prep = con.prepareStatement(query);
            prep.setString(1, start.getIATA());
            prep.setTimestamp(2, timeTo);
            prep.setTimestamp(3, timeFrom);
            prep.setString(4, end.getIATA());
            prep.setInt(5, numberPersons);
            try{
                ResultSet rs = prep.executeQuery();
                while(rs.next()){
                    FlightBean fb=new FlightBean();
                    fb.setFlightId(rs.getInt(1));
                    fb.setRouteId(rs.getInt(2));
                    fb.setDate(rs.getDate(4));
                    fb.setLocalDepartureTime(rs.getTime("locDepTime"));
                    fb.setLocalArrivalTime(rs.getTime("locArrTime"));
                    fb.setDuration(rs.getTime(15));
                    fb.setGateNr(rs.getString(7));
                    fb.setOperatingAirline(rs.getString(13));
                    AirportBean ap=new AirportBean();
                    AirportBean ap2=new AirportBean();
                    ap.setIATA(rs.getString(28));
                    ap.setName(rs.getString(29));
                    ap2.setIATA(rs.getString(31));
                    ap2.setName(rs.getString(32));
                    fb.setDepartureAirport(ap);
                    fb.setArrivalAirport(ap2);
                    CityBean ct=new CityBean();
                    CityBean ct2=new CityBean();
                    ct.setId(rs.getInt(18));
                    ct.setName(rs.getString(21));
                    ct.setCountryName(rs.getString(19));
                    ct2.setId(rs.getInt(23));
                    ct2.setName(rs.getString(26));
                    ct2.setCountryName(rs.getString(24));
                    fb.setDepartureCity(ct);
                    fb.setArrivalCity(ct2);
                    fb.setDistance(rs.getInt(17));
                    fb.setOperatingFlightNumber(rs.getString(12));
                    fb.setTravelClass(flightClass);
                    al.add(fb);
                    
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
                
                throw e;
            }
            
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            throw e;
        }
        System.out.println("setAirplaneModel:");
        setAirplaneModels(al);
        System.out.println("setSharedFlightNumber:");
        setSharedFlightNumbers(al);
        System.out.println("setPrice:");
        setPrice(al,flightClass);
        System.out.println("FlightDAO exited");
        return al;
    }
    private void setAirplaneModels(ArrayList<FlightBean> al)throws SQLException{
        String query="select apm.name from Flight f, airplane ap, airplanemodel apm where f.flight_id=? and f.airplane_registration_num=ap.registration_num"
                + " and ap.model_id=apm.model_id ";
       
        
        try{
            Connection con = getConnection();
            con.setAutoCommit(true);//read only
            PreparedStatement prep = con.prepareStatement(query);
            
       
            for(int i=0;i<al.size();i++){
            
                FlightBean fb=al.get(i);
                prep.setInt(1,fb.getFlightId());
                ResultSet rs=prep.executeQuery();
                if(rs.next()){
                    fb.setAirplaneModel(rs.getString(1));
                }
                al.set(i, fb);
            
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw e;
        }
    }
    private void setSharedFlightNumbers(ArrayList<FlightBean> al)throws SQLException{
        String query="select s.flight_number, s.airline_code from sharedFlightNumbers s where s.route_id=?";
    
        
        try{
            Connection con = getConnection();
            con.setAutoCommit(true);//read only
            PreparedStatement prep = con.prepareStatement(query);
            
       
            for(int i=0;i<al.size();i++){
            
                FlightBean fb=al.get(i);
                ArrayList<String> sfnl=new ArrayList<String>(); //sharedFlightNumbersList
                prep.setInt(1,fb.getRouteId());
                ResultSet rs=prep.executeQuery();
                while(rs.next()){
                    sfnl.add(rs.getString(1)+rs.getString(2));
                }
                fb.setSharedFlightNumbers(sfnl);
                al.set(i, fb);
            
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw e;
        }
    }
    private void setPrice(ArrayList<FlightBean> al,String fc)throws SQLException{
        String query="select price_in_dollar from city c, country co, currency cu where c.country_code=co.country_code and co.currency_code=cu.currency_code and c.city_id=?";
    
        
        try{
            Connection con = getConnection();
            con.setAutoCommit(true);//read only
            PreparedStatement prep = con.prepareStatement(query);
            
       
            for(int i=0;i<al.size();i++){
            
                FlightBean fb=al.get(i);
                
                prep.setInt(1,fb.getDepartureCity().getId());
                ResultSet rs=prep.executeQuery();
                if(rs.next()){
                    //double helper=rs.getBigDecimal(1).doubleValue();
                    double helper=1;
                    helper=helper*((double)fb.getDistance());
                    helper=helper+0.1*(double)obscureHash(fb.getAirplaneModel());
                    helper=helper+0.3*(double)obscureHash(fb.getOperatingAirline());
                    if(fc.compareTo("business")==0){
                    	helper=helper*1.5;
                    }
                    if(fc.compareTo("first")==0){
                    	helper=helper*2;
                    }
                    BigDecimal bd=new BigDecimal(helper);
                    bd.setScale(2,BigDecimal.ROUND_UP);
                    fb.setPriceInDollar(bd);
                    bd.multiply(rs.getBigDecimal(1));
                    bd.setScale(2,BigDecimal.ROUND_UP);
                    fb.setPrice(bd);
                }
               
            
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw e;
        }
        
    }
    private int obscureHash(String s){
        if(0==s.compareTo("")){
            return 0;
        }
        return (obscureHash(s.substring(0,s.length()-1))+641* ((int) s.charAt(s.length()-1))) % 1023;
    }
    
    public ArrayList<String> getRoutesFrom(String start){
    	String query="select distinct arrival_iata from Route where departure_iata=?";

        
        try{
            Connection con = getConnection();
            con.setAutoCommit(true);//read only
            PreparedStatement prep = con.prepareStatement(query);
            
       
            
                ArrayList<String> abc=new ArrayList<String>();
                prep.setString(1,start);
                ResultSet rs=prep.executeQuery();
                while(rs.next()){
                	abc.add(rs.getString(1));
                     
                }
                return abc;
               
            
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return null;
    }
    
    
	
	@SuppressWarnings("serial")
	public static class FlightNotFoundException extends Throwable {
		FlightNotFoundException(String message){
			super(message);
		}
	}
}