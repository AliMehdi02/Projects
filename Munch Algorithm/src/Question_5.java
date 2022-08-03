import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Question_5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Object creation for calling methods
		Q_1to4_Methods methods = new Q_1to4_Methods();
		Question_5 methods5 = new Question_5();


		//		String F78 = "C:\\Users\\Asus\\Desktop\\cs2004\\78.txt";
		//		String F55 = "C:\\Users\\Asus\\Desktop\\cs2004\\55.txt";
		String F36 = "C:\\Users\\Asus\\Desktop\\cs2004\\36.txt";
		String sep = " ";

		//		ArrayList<Integer> A78 = methods5.Munch(5000, methods.ReadArrayFile(F78, sep));
		//		ArrayList<Integer> A55 = methods5.Munch(30000, methods.ReadArrayFile(F55, sep));
		ArrayList<Integer> A36 = methods5.Munch(800, methods.ReadArrayFile(F36, sep));

	}

	public static ArrayList <Integer> Munch(int ITER, int[][] M) {

		Q_1to4_Methods methods = new Q_1to4_Methods();


		ArrayList<Integer> C = methods.initialStart(M.length);
		int F = methods.Fitness(C, M);

		System.out.println("Initial F = " + F);

		for(int i = 1; i<=ITER; i++) {

			ArrayList <Integer> x = (ArrayList<Integer>) C.clone();
			x =  methods.MimicMovement(C);


			int F1 = methods.Fitness(x, M);

			if (F1 < F)
			{
				x = (ArrayList<Integer>) C.clone();
				//F1 = F;
			}
			else 
			{	
				C = (ArrayList<Integer>) x.clone();
				F = F1;
			}
			//F = methods.Fitness(C, M);
			System.out.println("fitness = " + F);
		}
		return C;
	}
	
	
	
	
	
	
	
	
	public static ArrayList<Integer> initialStart (int x) 
	{
		ArrayList<Integer> ClusteringArrangement = new ArrayList<Integer>();
		int i = 1;
		for ( i = 1; i <= x ; i++)
		{
			ClusteringArrangement.add(i);
		}
		return ClusteringArrangement;

	}

	public static ArrayList<Integer> MimicMovement (ArrayList<Integer> ClusteringArrangement)
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

	
	public static int Fitness(ArrayList C, int[][] mdg)
	{
		int evm = 0;
		int c1=0;
		int c2=0;
		for (int j = 0; j < mdg.length - 1; j++)
		{
			for(int k=j+1;k<mdg.length;k++) 
			{
				c1 = (int) C.get(j);
				c2 = (int) C.get(k);
				if(c1 == c2) 
				{
					evm = evm + (2*(mdg[j][k])-1);
				}
			}
		}
		return evm;
	}

}


