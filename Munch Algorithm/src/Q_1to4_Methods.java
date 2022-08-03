import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Q_1to4_Methods {

	public static void main(String[] args)
	{
		// TODO Auto-generated method stut

		//Object creation for calling methods
		Q_1to4_Methods methods = new Q_1to4_Methods();
		CS2004 cs2004 = new CS2004();


		//Question 1
		System.out.println("Question_1");

		int mdg[][] = { 
				{0,0,0,0,1},
				{0,0,0,1,0},
				{0,0,0,0,0},
				{0,1,0,0,0},
				{1,0,0,0,0},
		};

		System.out.println(methods.validMDGcheck(mdg));

		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");






		//Question 2
		System.out.println("Question_2");

		System.out.println(methods.initialStart(10));

		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");







		//Question 3
		System.out.println("Question_3");

		String F78 = "C:\\Users\\Asus\\Desktop\\cs2004\\78.txt";
		String F55 = "C:\\Users\\Asus\\Desktop\\cs2004\\55.txt";
		String F36 = "C:\\Users\\Asus\\Desktop\\cs2004\\36.txt";
		String sep = " ";

		ArrayList<Integer> P78 = new ArrayList<Integer> (Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12));
		ArrayList<Integer> P55 = new ArrayList<Integer> (Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10));
		ArrayList<Integer> P36 = new ArrayList<Integer> (Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8));

		System.out.println("Perfect 78 = " + methods.Fitness(P78, methods.ReadArrayFile(F78, sep)));
		System.out.println("Perfect 55 = " + methods.Fitness(P55, methods.ReadArrayFile(F55, sep)));
		System.out.println("Perfect 36 = " + methods.Fitness(P36, methods.ReadArrayFile(F36, sep)));

		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");






		//Question 4
		System.out.println("Question_4");

		ArrayList <Integer> ArrayList = new ArrayList<Integer> (Arrays.asList(1,2,3,4,5,6,7,8,9,10));

		System.out.println("ArrayList Original = " + ArrayList);
		System.out.println("ArrayList Changed =  " + methods.MimicMovement(ArrayList));

		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");




		System.out.println("                                                             THE END");
	}







	//Question 1
	public static boolean validMDGcheck(int mdg[][]) 
	{
		boolean validity = true;

		if(mdg.length<=0 || mdg == null) 
		{
			return false;
		}

		for(int i = 0; i < mdg.length; i++){
			for(int j = 0; j < mdg[i].length; j++)
				if(mdg.length != mdg[i].length)
				{
					return false;
				}

		}

		for(int i=0; i<mdg.length; i++)
		{
			int j = i;
			if (mdg[i][j] !=0) 
			{
				return false;
			}
		}
		for(int i = 0; i<mdg.length -1; i++)									
		{
			for(int j = 0; j <= mdg[i].length - 1; j++)
			{
				if(mdg[i][j] != mdg[j][i])
					return false;
			}
		}
		for(int i=0; i<mdg.length; i++) {

			for(int j=0; j<mdg[i].length; j++)
			{

				if(mdg[i][j]>1 || mdg[i][j]<0) 
				{
					return false;
				}
			}
		}
		return validity;
	}












	//Question 2
	public ArrayList<Integer> initialStart (int x) 
	{
		if (x <= 0)
		{
			return null;
		}
		ArrayList<Integer> ClusteringArrangement = new ArrayList<Integer>();
		int i = 1;
		for ( i = 1; i <= x ; i++)
		{
			ClusteringArrangement.add(i);
		}
		return ClusteringArrangement;

	}









	//Question 3
	public static int Fitness(ArrayList<Integer> cluster, int[][] MDG)
	{
 
	  if (cluster == null || cluster.isEmpty())
	  {
		  return Integer.MAX_VALUE;
	  }
	  for (int i=0; i < cluster.size(); i ++ )
	  {
		  if(cluster.get(i) >= 1 || cluster.get(i) <= cluster.size())
		  {
			 return Integer.MAX_VALUE; 
		  }
	  }
	  
	  if (!validMDGcheck(MDG)) {
		  return Integer.MIN_VALUE;
	  }
		int evm = 0;
		int c1=0;
		int c2=0;
		for (int j = 0; j < MDG.length - 1; j++)
		{
			for(int k=j+1;k<MDG.length;k++) 
			{
				c1 = (int) cluster.get(j);
				c2 = (int) cluster.get(k);
				if(c1 == c2) 
				{
					evm = evm + (2*(MDG[j][k])-1);
				}
			}
		}
		return evm;
	}

	//For testing only
	public int [][] ReadArrayFile(String filename ,String sep)
	{
		int res[][] = null;
		try
		{
			BufferedReader input = null;
			input = new BufferedReader(new FileReader(filename));
			String line = null;
			int ncol = 0;
			int nrow = 0;

			while ((line = input.readLine()) != null) 
			{
				++nrow;
				String[] columns = line.split(sep);
				ncol = Math.max(ncol,columns.length);
			}
			res = new int[nrow][ncol];
			input = new BufferedReader(new FileReader(filename));
			int i=0,j=0;
			while ((line = input.readLine()) != null) 
			{

				String[] columns = line.split(sep);
				for(j=0;j<columns.length;++j)
				{
					res[i][j] = Integer.parseInt(columns[j]);
				}
				++i;
			}
		}
		catch(Exception E)
		{
			System.out.println("+++ReadArrayFile: "+E.getMessage());
		}
		return(res);
	}













	//Question 4
	public ArrayList<Integer> MimicMovement (ArrayList<Integer> ClusteringArrangement)
	{
		int x = 1; 
		int y = ClusteringArrangement.size(); 
		Random Rand_Generator = new Random();
		boolean inserted = false;

		ArrayList <Integer> SingleValueChanged  = new ArrayList<Integer>();
		for(int i =0; i<ClusteringArrangement.size();i++) 
		{
			SingleValueChanged.add(ClusteringArrangement.get(i));
		}

		while(inserted == false)
		{
			int Index = Rand_Generator.nextInt((ClusteringArrangement.size()));
			int number = CS2004.UI(x, y-1);
			if(number!=ClusteringArrangement.get(Index))
			{
				SingleValueChanged.set(Index, number);
				inserted = true;
			}
			else
			{
				inserted = false;
			}
		}
		return SingleValueChanged;
	}









	//	//Question 5
	//	public static ArrayList <Integer> Munch(int ITER, int[][] M) {
	//		Question_3 method3 = new Question_3();
	//		Question_2 method2 = new Question_2();
	//		Question_4 method4 = new Question_4();
	//
	//		ArrayList<Integer> C = method2.initialStart(M.length) ;
	//		int F = method3.Fitness(C, M);
	//		System.out.println(F);
	//
	//		for(int i = 1; i<=ITER; i++) 
	//		{
	//
	//			ArrayList <Integer> x= (ArrayList<Integer>) C.clone();
	//			C = method4.MimicMovement(C);
	//
	//			int F1 = method3.Fitness(C, M);
	//
	//
	//			if (F1 < F)
	//			{
	//				C = (ArrayList<Integer>) x.clone();
	//				F1 = F;
	//
	//			}
	//			else 
	//			{	
	//				x = (ArrayList<Integer>) C.clone();
	//				F = F1;
	//			}
	//			F = method3.Fitness(C, M);
	//			System.out.println("new fitness = " + F);
	//		}
	//		return C;
	//	}
}


