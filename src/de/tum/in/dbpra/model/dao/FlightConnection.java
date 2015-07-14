

package de.tum.in.dbpra.model.dao;



import de.tum.in.dbpra.model.bean.CityBean;
import de.tum.in.dbpra.model.bean.AirportBean;
import de.tum.in.dbpra.model.bean.ConnectionListBean;
import de.tum.in.dbpra.model.bean.ConnectionBean;
import de.tum.in.dbpra.model.bean.CurrencyBean;
import de.tum.in.dbpra.model.bean.FlightBean;
import de.tum.in.dbpra.model.dao.FlightDAO;
import java.sql.Timestamp;


import java.sql.Time;
import java.util.ArrayList;

import java.sql.SQLException;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class FlightConnection {
		ArrayList<ArrayList<String>> conns;
	
	
	public ConnectionListBean getConnections(Time dateFrom, Time dateTo, CityBean departureCity,
			CityBean arrivalCity, String flightClass, CurrencyBean currency, int seats) throws SQLException{
		System.out.println("In the flightConnection");
		
		
		
		conns=new ArrayList<ArrayList<String>>();
		FlightDAO fd=new FlightDAO();
		AirportDAO ad=new AirportDAO();
		ArrayList<AirportBean> ab=ad.getAirportsInCity(departureCity).getAirportList();
		ArrayList<AirportBean> ab2=ad.getAirportsInCity(arrivalCity).getAirportList();
		int num=ad.getNumberOfAirports();//Overall number of airports
		ArrayList<String> airports=ad.getAirports();
		ArrayList<ArrayList<FlightBean>> conz=new ArrayList<ArrayList<FlightBean>>();
		
		ArrayList<ArrayList<String>> conn=new ArrayList<ArrayList<String>>();
		for(int i=0;i<num;i++){
			conn.add(new ArrayList<String>());
		}
		for(int j=0;j<ab.size();j++){
			System.out.println("");
			
			//Pre-search:
			
			
			ArrayList<ArrayList<String>> op=new ArrayList<ArrayList<String>>();  //old precessor
			ArrayList<ArrayList<String>> newp=new ArrayList<ArrayList<String>>();//new precessor
			
			
			for(int i=0;i<num;i++){				//initialise precessors
				op.add(new ArrayList<String>());
				newp.add(new ArrayList<String>());
				
				
				
				if(airports.get(i).compareTo(ab.get(j).getIATA())==0){
					ArrayList<String> def=op.get(i);
					def.add(ab.get(j).getIATA());
					op.set(i,def);
				}
				
			}
			
			for (int z=0; z<15; z++){
				for(int i=0;i<num;i++){
					if(op.get(i).size()!=0){
						ArrayList<String> helper=fd.getRoutesFrom(airports.get(i));
						
						
						
						
						for(int k=0;k<helper.size();k++){
							int index=findPosInStringList(airports,helper.get(k));
							
							
							if(op.get(index).size()==0){
								ArrayList<String> helper2;
								helper2=newp.get(index);
								helper2.add(airports.get(i));
								newp.set(index, helper2);
							}
						}
					
					
					}
				}
				//merge the lists:
				for(int i=0;i<num;i++){
					if(op.get(i).size()==0){
						op.set(i,newp.get(i));
					}
					newp.set(i,new ArrayList<String>());
				}
				
				
				
				//break condition:
				boolean breaker=false;
				for(int m=0;m<ab2.size();m++){
					String helper3=ab2.get(m).getIATA();
					int helper4=findPosInStringList(airports,helper3);
					if(op.get(helper4).size()>0){
						breaker=true;
					}
				
				}
				if(breaker){
					break;
				}
		
			}
			
			
			
			for(int m=0;m<ab2.size();m++){				
				String arrivalAirp=ab2.get(m).getIATA();
				System.out.println("arrivalAirport: "+arrivalAirp);
				int pos=findPosInStringList(airports,arrivalAirp);
				if(op.get(pos).size()>0){
					ArrayList<String> ghi=new ArrayList<String>();
					ghi.add(arrivalAirp);
					addConnections(arrivalAirp,ab.get(j).getIATA(),new ArrayList<String>(),airports,op);
				}
			}
			
			
			
		}
		
		
		
		
		//output conns
		System.out.println("conns.size: "+conns.size());
		for(int i=0;i<conns.size();i++){
			System.out.println("conns.size: list number"+conns.get(i).size()+" size: "+conns.get(i).size());
			System.out.println("Content of this list: ");
			for(int j=0;j<conns.get(i).size();j++){
				System.out.print("  "+conns.get(i).get(j)+"->");
			}
		}
		
		//Compute the actual Connection:
		
		ArrayList<ArrayList<FlightBean>> tmp;
		for(int i=0;i<conns.size();i++){
			
			tmp=new ArrayList<ArrayList<FlightBean>>();
			for(int j=0;j<conns.get(i).size()-1;j++){
				
				if(j==0){
			
					AirportBean starter=new AirportBean();
					starter.setIATA(conns.get(i).get(j));
					AirportBean ender=new AirportBean();
					ender.setIATA(conns.get(i).get(j+1));
					ArrayList<FlightBean> tmp2=fd.getFlightsFromTo(new Timestamp(dateFrom.getTime()), new Timestamp(dateTo.getTime()), starter, ender, flightClass,seats);
					for(int k=0;k<tmp2.size();k++){
						ArrayList<FlightBean> tmp3=new ArrayList<FlightBean>();
						tmp3.add(tmp2.get(k));
						System.out.println("tmp gets added the following item:");
						for(int wc=0;wc<tmp3.size();wc++){
							System.out.println("  "+tmp3.get(wc).getDepartureAirport().getIATA()+"-->"+tmp3.get(wc).getArrivalAirport().getIATA());
						}
						tmp.add(tmp3);
						
					}					
				}else{
					//Compute TimeFrom/TimeTo
					long minTime=Long.MAX_VALUE;
					long maxTime=0;
					for(int k=0;k<tmp.size();k++){
						if(tmp.get(k).size()!=j){
							System.out.println("tmp gets deleted!!");
							tmp.remove(k);
						}
						else{
							ArrayList<FlightBean>tmp_conn=tmp.get(k);
							FlightBean fbb=tmp_conn.get(j-1);
							if(fbb.getLocalArrivalTime().getTime()>maxTime){
								maxTime=fbb.getLocalArrivalTime().getTime();
							}
							if(fbb.getLocalArrivalTime().getTime()<minTime){
								minTime=fbb.getLocalArrivalTime().getTime();
							}
						}
					}
					if(minTime==Long.MAX_VALUE){
						System.out.println("Something got ignored!!");
						continue;
					}
					else{
						minTime=minTime+7200000;	//2h
						maxTime=maxTime+18000000;	//5h
						Timestamp timeFrom=new Timestamp(minTime);
						Timestamp timeTo=new Timestamp(maxTime);
						//Compute the flights:
						AirportBean starter=new AirportBean();
						AirportBean ender=new AirportBean();
						starter.setIATA(conns.get(i).get(j));
						ender.setIATA(conns.get(i).get(j+1));
						ArrayList<FlightBean> tmp2=fd.getFlightsFromTo(timeFrom, timeTo, starter, ender, flightClass,seats);
					
					
					
						//merge the flights to the current connections:

						for(int m=0;m<tmp.size();m++){
							for(int n=0;n<tmp2.size();n++){
								FlightBean lastFlight=tmp.get(m).get(j-1);
								if(tmp.get(m).size()!=j){
									System.out.println("The following flight has not been considered in if labeled Pikachu:");
									System.out.println("    j:"+j);
									for(int wc=0;wc<tmp.get(m).size();wc++){
										System.out.print("    "+tmp.get(m).get(wc).getDepartureAirport().getIATA()+"-->"+tmp.get(m).get(wc).getArrivalAirport().getIATA()+"   ");
									}
									System.out.println("\n");
									continue;
								}
								ArrayList<FlightBean> consFlight=tmp.get(m);
								if(lastFlight.getLocalArrivalTime().getTime()+7200000<=tmp2.get(n).getLocalDepartureTime().getTime()&&
										lastFlight.getLocalArrivalTime().getTime()+18000000>=tmp2.get(n).getLocalDepartureTime().getTime()){
									consFlight.add(tmp2.get(n));

									System.out.println("tmp gets added the following item:");
									for(int wc=0;wc<consFlight.size();wc++){
										System.out.println("  "+consFlight.get(wc).getDepartureAirport().getIATA()+"-->"+consFlight.get(wc).getArrivalAirport().getIATA());
									
									tmp.add(consFlight);//add, because there might be other possible flights, that have the same first flights
									}
								}
							}
						}
					}
					//Delete unused flights
					for(int p=0;p<tmp.size();p++){
						if(tmp.get(p).size()!=j+1){
							System.out.println("something gets removed from tmp, element:");
							for(int wc=0;wc<tmp.get(p).size();wc++){
								System.out.println("  "+tmp.get(p).get(wc).getDepartureAirport().getIATA()+"-->"+tmp.get(p).get(wc).getArrivalAirport().getIATA());
							}
							tmp.remove(p);
						}
					}
					
				}//end else
			}//end inner for(j)
			
			//print out tmp:
			System.out.println("tmp:  ");
			for(int wc=0;wc<tmp.size();wc++){
				System.out.println("line: "+wc);
				for(int klo=0;klo<tmp.get(wc).size();klo++){
					System.out.print("  "+tmp.get(wc).get(klo).getDepartureAirport().getIATA()+"-->"+tmp.get(wc).get(klo).getArrivalAirport().getIATA());
				}
				System.out.println("");
			}
			conz.addAll(tmp);
			
		}//end outer for(i)
			
		
		
		//transform conz (ArrayList<ArrayList<FlightBean>>) to ConnectionListBean
		ArrayList<ConnectionBean> result=new ArrayList<ConnectionBean>();
		for(int i=0;i<conz.size();i++){
			ConnectionBean cb=trafoToConn(conz.get(i),currency);
			result.add(cb);
		}
		ConnectionListBean clb=new ConnectionListBean();
		clb.setConnectionList(result);
		return clb;
		
	}
	
	
	
	//transforms List of Flights to a connection (namely computes overall price/duration)
	private ConnectionBean trafoToConn(ArrayList<FlightBean> fbs, CurrencyBean currency){
		ConnectionBean cb=new ConnectionBean();
		
		cb.setFlightList(fbs);
		cb.setCurrency(currency);
		
		
		//Overall price
		BigDecimal bd=new BigDecimal("0.00");
		for(int i=0;i<fbs.size();i++){
			bd.add(fbs.get(i).getPriceInDollar());//price in dollar
			System.out.println("PriceInDolar:"+fbs.get(i).getPriceInDollar());
		}
		System.out.println("bd in betwenn:"+bd.toString());
		bd.multiply(currency.getPriceInDollar());
		System.out.println("factor: "+currency.getPriceInDollar());
		bd=bd.setScale(2, RoundingMode.HALF_UP);
		System.out.println("Price: "+bd.toString());
		cb.setOverallPrice(bd);
		
		
		//Overall duration:
		
		long overall=0;
		for(int i=0;i<fbs.size();i++){
			overall+=fbs.get(i).getDuration().getTime();
		}
		for(int i=1;i<fbs.size()-1;i++){
			overall+=fbs.get(i+1).getLocalDepartureTime().getTime()-fbs.get(i).getLocalArrivalTime().getTime();
		}
		cb.setOverallDuration(new Time(overall));
		
		return cb;
	}
	
	
	//adds an element to conns, only used at a single point. If conns are wrong--> test it
	private void addConnections(String end,String start, ArrayList<String> after,ArrayList<String> airports, ArrayList<ArrayList<String>> dag){
		/*System.out.println("addConnections:");
		System.out.println("  start: "+start);
		System.out.println("  end: "+end);
		System.out.println("after: ");
		for(int wc=0;wc<after.size();wc++){
			System.out.print("    "+after.get(wc));
		}
		System.out.println("");*/
		
		
		
		if(end.compareTo(start)==0){
			//System.out.println("case1:");
			after.add(0,end);
			conns.add(after);
			return;
		}
		//System.out.println("case2");
		int index=findPosInStringList(airports,end);
		ArrayList<String> precessors=dag.get(index);
		for(int i=0;i<precessors.size();i++){
			//System.out.println(" precessor:  "+precessors.get(i));
			ArrayList<String> after1=new ArrayList<String>();
			for(int j=0;j<after.size();j++){
				after1.add(new String(after.get(j)));
			}
			after1.add(0,end);
			addConnections(precessors.get(i),start,after1,airports,dag);
		}
		
		
	}
	
	
	
	//self-explaining
	private int findPosInStringList(ArrayList<String> strl,String str){
		for(int i=0;i<strl.size();i++){
			if(strl.get(i).compareTo(str)==0){
				return i;
			}
		}
		return -1;
	}
	
	
	
	
}

