package test;

import org.junit.*;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import model.ListOfRequest;
import model.Map;
import model.Node;
import model.Stop;
import model.Tour;
import model.TypeOfStop;

public class updateStopTimesTest {

	@Test
	public void test() {
		Tour tour = new Tour();
		double[][] graphe = {{0,10,20},
							{8,0,12},
							{4,15,0}};
		HashMap<Long,HashMap<Long, Float>> distanceOf = new HashMap<Long,HashMap<Long, Float>>();
		HashMap<Long, Float> distance0 = new HashMap<Long, Float>();
		HashMap<Long, Float> distance1 = new HashMap<Long, Float>();
		HashMap<Long, Float> distance2 = new HashMap<Long, Float>();
		distance0.put((long)145, (float)0);
		distance0.put((long)687, (float)10);
		distance0.put((long)963, (float)20);
		distance1.put((long)145, (float)4);
		distance1.put((long)687, (float)0);
		distance1.put((long)963, (float)8);
		distance2.put((long)145, (float)2);
		distance2.put((long)687, (float)13);
		distance2.put((long)963, (float)0);
		distanceOf.put((long) 145, distance0);
		distanceOf.put((long) 687, distance1);
		distanceOf.put((long) 963, distance2);
		tour.setDistanceOf(distanceOf);
		Node node0 = new Node(40,40,145);
		Node node1=new Node(40,40,687);
		Node node2=new Node(40,40,963);
		Stop depot=new Stop(node0,TypeOfStop.DEPOT,0);
		depot.setDepartureTime(LocalTime.of(8,0,0));
		depot.setArrivalTime(LocalTime.of(8,0,0));
		Stop pickup1=new Stop(node1,TypeOfStop.PICKUP,4);
		Stop delivery1=new Stop(node2,TypeOfStop.DELIVERY,2);
		ArrayList<Stop> orderedStops = new ArrayList<Stop>();
		orderedStops.add(depot);
		orderedStops.add(pickup1);
		orderedStops.add(delivery1);
		orderedStops.add(depot);
		
		tour.updateStopTimes(orderedStops);
		LocalTime[] expectedarrivaltimes = {LocalTime.of(8,0,26),LocalTime.of(8,0,10),LocalTime.of(8,0,22)};
		LocalTime[] expecteddeparturtimes= {LocalTime.of(8,0,0),LocalTime.of(8,0,14),LocalTime.of(8,0,24)};
		for(int i=0;i<expectedarrivaltimes.length;i++)
		{
			assertEquals(expectedarrivaltimes[i],orderedStops.get(i).getArrivalTime());
			assertEquals(expecteddeparturtimes[i],orderedStops.get(i).getDepartureTime());
		}
	}
	@Test
	public void test2() {
		Tour tour = new Tour();
		double[][] graphe = {{0,6,8,23,1},
							{12,0,34,71,25},
							{14,65,0,18,10},
							{1,32,3,0,5},
							{135,120,186,121,0}};
		HashMap<Long,HashMap<Long, Float>> distanceOf = new HashMap<Long,HashMap<Long, Float>>();
		HashMap<Long, Float> distance0 = new HashMap<Long, Float>();
		HashMap<Long, Float> distance1 = new HashMap<Long, Float>();
		HashMap<Long, Float> distance2 = new HashMap<Long, Float>();
		HashMap<Long, Float> distance3 = new HashMap<Long, Float>();
		HashMap<Long, Float> distance4 = new HashMap<Long, Float>();
		distance0.put((long)963, (float)0);
		distance0.put((long)666, (float)6);
		distance0.put((long)12586, (float)8);
		distance0.put((long)87536, (float)23);
		distance0.put((long)77777, (float)0);
		distance1.put((long)963, (float)0);
		distance1.put((long)666, (float)0);
		distance1.put((long)12586, (float)22);
		distance1.put((long)87536, (float)59);
		distance1.put((long)77777, (float)13);
		distance2.put((long)666, (float)4);
		distance2.put((long)12586, (float)55);
		distance2.put((long)87536, (float)0);
		distance2.put((long)77777, (float)8);
		distance3.put((long)666, (float)0);
		distance3.put((long)12586, (float)20);
		distance3.put((long)87536, (float)8);
		distance3.put((long)77777, (float)0);
		distance4.put((long)963, (float)15);
		distance4.put((long)666, (float)0);
		distance4.put((long)12586, (float)66);
		distance4.put((long)87536, (float)1);
		distance4.put((long)77777, (float)0);
		distanceOf.put((long) 963, distance0);
		distanceOf.put((long) 666, distance1);
		distanceOf.put((long) 12586, distance2);
		distanceOf.put((long) 87536, distance3);
		distanceOf.put((long) 77777, distance4);
		tour.setDistanceOf(distanceOf);
		Node node0 = new Node(40,40,963);
		Node node1=new Node(40,40,666);
		Node node2=new Node(40,40,12586);
		Node node3 = new Node(40,40,87536);
		Node node4=new Node(40,40,77777);
		Stop depot=new Stop(node0,TypeOfStop.DEPOT,0);
		depot.setDepartureTime(LocalTime.of(8,0,0));
		depot.setArrivalTime(LocalTime.of(8,0,0));
		Stop pickup1=new Stop(node1,TypeOfStop.PICKUP,12);
		Stop delivery1=new Stop(node2,TypeOfStop.DELIVERY,10);
		Stop pickup2=new Stop(node3,TypeOfStop.PICKUP,30);
		Stop delivery2=new Stop(node4,TypeOfStop.DELIVERY,120);
		ArrayList<Stop> orderedStops = new ArrayList<Stop>();
		orderedStops.add(depot);
		orderedStops.add(pickup2);
		orderedStops.add(pickup1);
		orderedStops.add(delivery1);
		orderedStops.add(delivery2);
		orderedStops.add(depot);
		HashMap<Long, Integer> simple_Id_Of = new HashMap<Long, Integer>();
		simple_Id_Of.put((long)963, 0);
		simple_Id_Of.put((long)666, 1);
		simple_Id_Of.put((long)12586, 2);
		simple_Id_Of.put((long)87536, 3);
		simple_Id_Of.put((long)77777, 4);
		tour.updateStopTimes( orderedStops);
		LocalTime[] expectedarrivaltimes = {LocalTime.of(8,04,00),LocalTime.of(8,0,23),LocalTime.of(8,0,53),LocalTime.of(8,01,27),LocalTime.of(8,01,45)};
		LocalTime[] expecteddeparturtimes= {LocalTime.of(8,0,0),LocalTime.of(8,0,53),LocalTime.of(8,01,05),LocalTime.of(8,01,37),LocalTime.of(8,03,45)};
		for(int i=0;i<expectedarrivaltimes.length;i++)
		{
			assertEquals(expectedarrivaltimes[i],orderedStops.get(i).getArrivalTime());
			assertEquals(expecteddeparturtimes[i],orderedStops.get(i).getDepartureTime());
		}
	}
	
	@Test
	public void test3() {
		System.out.println("---------test 3 ------------");
		Tour tour = new Tour();
		double[][] graphe = {{0,5,3,5,4},
							{20,0,18,12,14},
							{18,19,0,19,17},
							{38,30,36,0,32},
							{130,145,122,145,0}};
		HashMap<Long,HashMap<Long, Float>> distanceOf = new HashMap<Long,HashMap<Long, Float>>();
		HashMap<Long, Float> distance0 = new HashMap<Long, Float>();
		HashMap<Long, Float> distance1 = new HashMap<Long, Float>();
		HashMap<Long, Float> distance2 = new HashMap<Long, Float>();
		HashMap<Long, Float> distance3 = new HashMap<Long, Float>();
		HashMap<Long, Float> distance4 = new HashMap<Long, Float>();
		distance0.put((long)963, (float)0);
		distance0.put((long)666, (float)5);
		distance0.put((long)12586, (float)3);
		distance0.put((long)666, (float)5);
		distance0.put((long)77777, (float)4);
		
		distance1.put((long)963, (float)8);
		distance1.put((long)666, (float)0);
		distance1.put((long)12586, (float)6);
		distance1.put((long)666, (float)0);
		distance1.put((long)77777, (float)2);
		
		distance2.put((long)963, (float)8);
		distance2.put((long)666, (float)9);
		distance2.put((long)12586, (float)0);
		distance2.put((long)666, (float)9);
		distance2.put((long)77777, (float)7);
		
		distance3.put((long)963, (float)8);
		distance3.put((long)666, (float)0);
		distance3.put((long)12586, (float)6);
		distance3.put((long)666, (float)0);
		distance3.put((long)77777, (float)2);
		
		distance4.put((long)963, (float)10);
		distance4.put((long)666, (float)25);
		distance4.put((long)12586, (float)2);
		distance4.put((long)666, (float)25);
		distance4.put((long)77777, (float)0);
		
		distanceOf.put((long) 963, distance0);
		distanceOf.put((long) 666, distance1);
		distanceOf.put((long) 12586, distance2);
		distanceOf.put((long) 666, distance3);
		distanceOf.put((long) 77777, distance4);
		tour.setDistanceOf(distanceOf);
		Node node0 = new Node(40,40,963);
		Node node1=new Node(40,40,666);
		Node node2=new Node(40,40,12586);
		Node node3 = new Node(40,40,87536);
		Node node4=new Node(40,40,77777);
		Stop depot=new Stop(node0,TypeOfStop.DEPOT,0);
		depot.setDepartureTime(LocalTime.of(8,0,0));
		depot.setArrivalTime(LocalTime.of(8,0,0));
		Stop pickup1=new Stop(node1,TypeOfStop.PICKUP,12);
		Stop delivery1=new Stop(node2,TypeOfStop.DELIVERY,10);
		Stop pickup2=new Stop(node1,TypeOfStop.PICKUP,30);
		Stop delivery2=new Stop(node4,TypeOfStop.DELIVERY,120);
		ArrayList<Stop> orderedStops = new ArrayList<Stop>();
		orderedStops.add(depot);
		orderedStops.add(pickup2);
		orderedStops.add(pickup1);
		orderedStops.add(delivery1);
		orderedStops.add(delivery2);
		orderedStops.add(depot);
	
		tour.updateStopTimes(orderedStops);
		LocalTime[] expectedarrivaltimes = {LocalTime.of(8,03,20),LocalTime.of(8,0,5),LocalTime.of(8,0,35),LocalTime.of(8,00,53), LocalTime.of(8,01,10)};
		LocalTime[] expecteddeparturtimes= {LocalTime.of(8,0,0),LocalTime.of(8,0,35),LocalTime.of(8,0,47),LocalTime.of(8,01,03),  LocalTime.of(8,03,10)};
		for(int i=1;i<expectedarrivaltimes.length;i++)
		{
			assertEquals(expectedarrivaltimes[i],orderedStops.get(i).getArrivalTime());
			assertEquals(expecteddeparturtimes[i],orderedStops.get(i).getDepartureTime());
		}
	}

	@Test
	public void test4() {
		System.out.println("---------test 4 ------------");
		Tour tour = new Tour();
		double[][] graphe = {{0,4,3,8,4},
							{18,0,19,17,12},
							{12,17,0,19,17},
							{38,36,36,0,36},
							{126,120,127,125,0}};
		
		Node node0 = new Node(40,40,963);
		Node node1=new Node(40,40,666);
		Node node2=new Node(40,40,12586);
		Node node3 = new Node(40,40,87536);
		Node node4=new Node(40,40,77777);
		
		HashMap<Long,HashMap<Long, Float>> distanceOf = new HashMap<Long,HashMap<Long, Float>>();
		HashMap<Long, Float> distance0 = new HashMap<Long, Float>();
		HashMap<Long, Float> distance1 = new HashMap<Long, Float>();
		HashMap<Long, Float> distance2 = new HashMap<Long, Float>();
		HashMap<Long, Float> distance3 = new HashMap<Long, Float>();
		HashMap<Long, Float> distance4 = new HashMap<Long, Float>();
		distance0.put((long)963, (float)0);
		distance0.put((long)666, (float)4);
		distance0.put((long)12586, (float)3);
		distance0.put((long)77777, (float)8);
		distance0.put((long)666, (float)4);
		distance1.put((long)963, (float)6);
		distance1.put((long)666, (float)0);
		distance1.put((long)12586, (float)7);
		distance1.put((long)77777, (float)5);
		distance1.put((long)666, (float)0);
		distance2.put((long)963, (float)2);
		distance2.put((long)666, (float)7);
		distance2.put((long)12586, (float)0);
		distance2.put((long)77777, (float)9);
		distance2.put((long)666, (float)7);
		distance3.put((long)963, (float)8);
		distance3.put((long)666, (float)6);
		distance3.put((long)12586, (float)6);
		distance3.put((long)77777, (float)0);
		distance3.put((long)666, (float)6);
		distance4.put((long)963, (float)6);
		distance4.put((long)666, (float)0);
		distance4.put((long)12586, (float)7);
		distance4.put((long)77777, (float)5);
		distance4.put((long)666, (float)0);
		distanceOf.put((long) 963, distance0);
		distanceOf.put((long) 666, distance1);
		distanceOf.put((long) 12586, distance2);
		distanceOf.put((long) 77777, distance3);
		distanceOf.put((long)666, distance4);
		tour.setDistanceOf(distanceOf);
		Stop depot=new Stop(node0,TypeOfStop.DEPOT,0);
		depot.setDepartureTime(LocalTime.of(8,0,0));
		depot.setArrivalTime(LocalTime.of(8,0,0));
		Stop pickup1=new Stop(node1,TypeOfStop.PICKUP,12);
		Stop delivery1=new Stop(node2,TypeOfStop.DELIVERY,10);
		Stop pickup2=new Stop(node4,TypeOfStop.PICKUP,30);
		Stop delivery2=new Stop(node1,TypeOfStop.DELIVERY,120);
		ArrayList<Stop> orderedStops = new ArrayList<Stop>();
		orderedStops.add(depot);
		orderedStops.add(pickup1);
		orderedStops.add(pickup2);
		orderedStops.add(delivery1);
		orderedStops.add(delivery2);
		orderedStops.add(depot);

		tour.updateStopTimes( orderedStops);
		LocalTime[] expectedarrivaltimes = {LocalTime.of(8,03,20),LocalTime.of(8,0,4),LocalTime.of(8,0,21),LocalTime.of(8,00,57), LocalTime.of(8,01,14)};
		LocalTime[] expecteddeparturtimes= {LocalTime.of(8,0,0),LocalTime.of(8,0,16),LocalTime.of(8,0,51),LocalTime.of(8,01,07),  LocalTime.of(8,03,14)};
		for(int i=0;i<expectedarrivaltimes.length;i++)
		{
			assertEquals(expectedarrivaltimes[i],orderedStops.get(i).getArrivalTime());
			assertEquals(expecteddeparturtimes[i],orderedStops.get(i).getDepartureTime());
		}
	}
	@Test
	public void test9() {
		System.out.println("---------test 4 ------------");
		Tour tour = new Tour();
		HashMap<Long,HashMap<Long, Float>> distanceOf = new HashMap<Long,HashMap<Long, Float>>();
		HashMap<Long, Float> distance0 = new HashMap<Long, Float>();
		distance0.put((long)963, (float)0);
		distanceOf.put((long) 963, distance0);
		tour.setDistanceOf(distanceOf);
		Node node0 = new Node(40,40,963);
		Stop depot=new Stop(node0,TypeOfStop.DEPOT,0);
		depot.setDepartureTime(LocalTime.of(8,0,0));
		depot.setArrivalTime(LocalTime.of(8,0,0));
		ArrayList<Stop> orderedStops = new ArrayList<Stop>();
		orderedStops.add(depot);
		orderedStops.add(depot);
		tour.updateStopTimes( orderedStops);
		LocalTime[] expectedarrivaltimes = {LocalTime.of(8,00,00),LocalTime.of(8,00,00)};
		LocalTime[] expecteddeparturtimes= {LocalTime.of(8,0,0),LocalTime.of(8,00,00)};
		for(int i=0;i<expectedarrivaltimes.length;i++)
		{
			assertEquals(expectedarrivaltimes[i],orderedStops.get(i).getArrivalTime());
			assertEquals(expecteddeparturtimes[i],orderedStops.get(i).getDepartureTime());
		}
	}
}
