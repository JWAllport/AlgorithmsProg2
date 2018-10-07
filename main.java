import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

public class main {
	   
	static TreeMap<Integer, ArrayList<String[]>> map = new TreeMap<>();
	
	static TreeMap<Integer,ArrayList<Integer>> completebyScore = new TreeMap<>(Collections.reverseOrder()); 
			
	static ArrayList<String[]> contestentData = new ArrayList<String[]>();

	
	public static void main (String[] args) throws IOException{
		 
	
		 String[] row = new String[4];

	        //open up the input file
	       Scanner input=null;
		
			input = new Scanner (new File("input.txt"));
		
		
	    	 	int i=0;
	    	 	
	    	 	// Consolidate all data of each contestent into one key, and an array of riddles(represented as array of data),
	    	
	    	 	while(i<4 && input.hasNextLine()) {
	    	 	
	    	 		row[i]=input.next();
	    		   
	    		   if(i==3) {
	    			   i=0;

	    			
	    			  // System.out.println(map.containsKey(row[i]));
	    			   int key=Integer.parseInt(row[i]);
	    			  
	    			   // if the contestent has already been put into the map
	    			   
	    			   if((map.containsKey(key)) && ((row[3].equals("C"))||(row[3].equals("I")))){
		    			   //System.out.println("Made it");

	    				   contestentData = new ArrayList<String[]>();
	    				   //add the row to contestentData ArrayList
	    				   contestentData.add(row);
	    				   
	    				   for(int k=0;k<(map.get(key)).size();k++) {
	    					   //add all the values associated with map key(contestent number) to contestendData
	    					   
	    					   contestentData.add(map.get(key).get(k));
	    				   }
	    				   key=Integer.parseInt(row[i]);
	    				   
	    				   map.put(key, contestentData);

	    			   }

	    			   // if the contestent has NOT already been put into the map
	    			   if(!(map.containsKey(key)) && ((row[3].equals("C"))||(row[3].equals("I")))){
		    			  // System.out.println("Made it");

	    				   contestentData = new ArrayList<String[]>();
	    				   contestentData.add(row);
	    				   key=Integer.parseInt(row[i]);

	    				   map.put(key, contestentData);
	    			   }
		    		   //reset the row for the next riddle
	    			   row=new String[4];
	    		   }
	    		  
	    		   //if i!=3 keep incrementing
	    		   else {
	    			   i++;
	    		   }
    		   	
	    	   }//end the while, all contestents and data has been added to "map"
	    	 	
	            Writer out=null;
				
				out = new FileWriter("output.txt");
				
				

	    	 	//System.out.print(map.keySet()+" ");
	            
	 			//System.out.print(map.get("1").get(0)[0]+" ");

	    	 		Iterator currentContestent=map.keySet().iterator();
	    	 		ArrayList<Integer> valsForComplete;

	    	 		int timePenalty;
	    	 		int totalCorrect;
	    	 		//iterate through map's contestents 
	    	 		//calculate the time penalty and the score of each.
	    	 		//Put this data into a new TreeMap with the data as such Contestent->{timePenalty,score}
	    	 		
	    	 		while(currentContestent.hasNext()) {
	    	 			//wipe array for next contestent
	    	 			valsForComplete = new ArrayList<Integer>();
	    	 			
	    	 			timePenalty=0;
	    	 			totalCorrect=0;
	    	 			int contestent=(int) currentContestent.next();
	    	 			
	    	 				    	 			
	    	 			for(int q=0;q<map.get(contestent).size();q++) {
	    	    	 		
    	    	 				//if and else if to get the total time penalty for each contestent 
	    	    	 			if(map.get(contestent).get(q)[3].equals("I")){
	    	    	 				
	    	    	 				timePenalty+=5;
	    	    	 				
	    	    	 				
	    	    	 			}
	    	    	 			
	    	    	 			
	    	    	 			else if(map.get(contestent).get(q)[3].equals("C")) {
	    	    	 				timePenalty+=Integer.parseInt(map.get(contestent).get(q)[2]);
	    	    	 				totalCorrect+=1;
	    	    	 			}
	    	    	 			
	    	    	 			
	    	    	 	}

	    	 			// add the computed timePenalty and totalCorrect(riddles) into an array
	    	 			valsForComplete.add(contestent); //[0]
	    	 			valsForComplete.add(timePenalty);//[1]
	    	 			valsForComplete.add(totalCorrect);//[2] total correct==the ranking unless ranking already exists then minus 1 until the timePenalty is higher then current

	    	 			int ranking=totalCorrect;
	    	 			System.out.println(ranking);
	    	 			
	    	 			aSort(ranking,totalCorrect,timePenalty,valsForComplete);
	    	 			
	    	 			//System.out.println(completebyScore.keySet());


	    	 			
	    	 				    	 	
	    	
	    	 	}//while 
	    	 		
	    		 	
	    	 	//System.out.print(completebyScore.keySet());

	    	 	//write to the output sorted 
	    	 	currentContestent=completebyScore.keySet().iterator();
	    	 	
	    	 	while(currentContestent.hasNext()) {
    	 			int ranking=(int) currentContestent.next();	 		
	    	 	
    	 				out.write(completebyScore.get(ranking).get(0)+" ");	//write contestent number;

						out.write(completebyScore.get(ranking).get(2) +" "); //write total correct
					
						out.write(completebyScore.get(ranking).get(1) +"\n"); // write total time penalty

	    	 		
	    	 	}
	    	 	
	    	 	
					out.close();
					input.close();
					

	 }//main



	private static Object aSort(int ranking, int totalCorrect, int timePenalty, ArrayList<Integer> valsForComplete) {

		if(completebyScore.containsKey(ranking)) {

			if(completebyScore.get(totalCorrect).get(1)<=(timePenalty)) { 

				ranking--;

				return aSort(ranking,totalCorrect,timePenalty,valsForComplete);
    	 //	System.out.println(totalCorrect);

			}
			else if(completebyScore.get(totalCorrect).get(1)>=(timePenalty)) {
				ranking+=1;
				
				return aSort(ranking,totalCorrect,timePenalty,valsForComplete);

			}
		
		}
		
		else {
			
		}

		return(completebyScore.put(ranking,valsForComplete));
		
		
	}

	
	 
}//class.;
