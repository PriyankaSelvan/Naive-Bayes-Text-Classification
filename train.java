import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;


public class Train {
	public static void main(String args[])
	{
		int row=8124;
		int col=23;
		int numP=3916;
	    int numE=4208;
	    double pP=Math.log((double)numP/(double)row);
	    double pE=Math.log((double)numE/(double)row);
		//System.out.println("Probability P "+pP);
		//System.out.println("Probability E "+pE);
		String[][] data=new String[row][col];
		
		
		try{
			BufferedReader fr = new BufferedReader(new FileReader("TrainData.txt"));
			Scanner sc=new Scanner(System.in);
			
		for(int i=0;i<row;i++)
		{
			 String line = fr.readLine();
			 for(int j=0;j<col;j++)
			 {
				 String[] temp=line.split(",");
				 data[i][j]=temp[j];
				 //System.out.print(data[i][j]+" ");
				 
				 
			 }
			 //System.out.println("");
			 
		}fr.close();
		String[] query=new String[col];
		System.out.println("Enter query data:");
		for(int i=1;i<col;i++)
		{
			query[i]=sc.nextLine();
		}
		double countP[]=new double[col];
		double countE[]=new double[col];
		for(int i=1;i<col;i++)
		{
			for(int j=0;j<row;j++)
			{
				if(query[i].equals("?"))
				{
					countP[i]+=0.5;
					countE[i]+=0.5;
				}
				if((query[i].equals(data[j][i]))&&(data[j][0].equals("p")))
				{
					countP[i]++;
				}
				if((query[i].equals(data[j][i]))&&(data[j][0].equals("e")))
				{
					countE[i]++;
				}
						
					
			}
		}
		//System.out.println("Done0");
		/*System.out.println("Count P col 1 "+countP[1]);
		System.out.println("Count P col 2 "+countP[2]);
		System.out.println("Count E col 1 "+countE[1]);
		System.out.println("Count E col 2 "+countE[2]);*/
		for(int i=1;i<col;i++)
		{
			countP[i]=(double)countP[i]/(double)numP;
			countE[i]=(double)countE[i]/(double)numE;
		}
		//System.out.println("Done1");
		double[] probP=new double[col];
		double[] probE=new double[col];
		//System.out.println("Done2");
		double sumP=0.0;
		double sumE=0.0;
		for(int i=1;i<col;i++)
		{
			probP[i]=Math.log(countP[i]);
			probE[i]=Math.log(countE[i]);	 
		}
		//System.out.println("Done3");
		
		for(int i=1;i<col;i++)
		{
			sumP+=probP[i];
			sumE+=probE[i];
		}
		//System.out.println("Done4");
		sumP+=pP;
		sumE+=pE;
		//System.out.println("Poisonous "+sumP);
		//System.out.println("Edible "+sumE);
		if(sumP>sumE)
		{
			System.out.println("Poisonous!!!!");
		}
		else if(sumE>sumP)
		{
			System.out.println("Edible!!");
		}
		else
			System.out.println("Cannot predict");
		
		}
		catch(Exception e)
		{
			System.out.println("Exception found "+e);
		}
	}

}
