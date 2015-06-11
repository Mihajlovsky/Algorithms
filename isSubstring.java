import java.util.Scanner;

/** @author Mihajlovsky
 *
 * Дадени се две листи, треба да се направи трета листа која ке ги содржи елементите на првата листа - елементите на втората.
 * Значи C=A-B . Задачата треба да се реши со користење на листи, DLL (или SLL ,сеедно )
 *
 *  Тест Примери: А= аbcd B=ab C=cd // А=abcd B=ad C=abcd // A=aaaa B=a C=празна листа // A=abcabcd B=abc C=d
 *
 */

public class isSubstring {

	public static void main(String[] args) {
	
		StringBuilder sb = new StringBuilder();
		
		boolean substring=false;
		int Count=0;
		
		Scanner firstString=new Scanner(System.in);
		String A=firstString.nextLine();
		
		
		Scanner secondString=new Scanner(System.in);
		String B=secondString.nextLine();
		
		String finalWord="";
		
		DLL<Character> listA= new DLL<Character>();
		DLL<Character> listB = new DLL<Character>();
		DLL<Character> finalList = new DLL<Character>();
		
		//popolnuvanje na listite
		for (int i=0; i<A.length();i++)
		{
			listA.insertLast(A.charAt(i));
		}
		for (int i=0; i<B.length();i++)
		{
			listB.insertLast(B.charAt(i));
		}
		
		DLLNode<Character> firstA = listA.getFirst();
		DLLNode<Character> firstB = listB.getFirst();
		DLLNode<Character> pom;
		
		while (firstA!=null)
		{
			if (firstB!=null){
			//ako prvite elementi od listite se isti
			if (firstA.element==firstB.element)
			{
				substring=true;
				
				// se definira pomosen element za da se pamti pozicijata na prvata lista
				pom=firstA;
				//se dodeka ima elementi vo vtorata lista proveruvame dali site se isti so prvata
				while(firstB!=null)
				{
					if(firstB.element==pom.element)
					{
						//se brojat kolku elementi se isti
						Count++;
						substring=true;
					}
					else{
						//ako ne se site isti znaci vtorata lista ne e podstring
						substring=false;
					}
					firstB=firstB.succ;
					pom=pom.succ;
				}
				
				//ako e podstring togas gi izminuvame mestata koi se izbroeni
				// i ne vnesuvame nisto vo konecnata lista
				if(substring){
				for(int i=0;i<Count;i++)
				{
					if (firstA!=null){
					firstA=firstA.succ;
					}
				}
				}
				
			}
			}
			
			//koga ke izlezeme od izminuvanjeto ako vtorata lista ne e podstring
			// se vnesuva samo prvata bukva i prodolzuvame da proveruvame za ostanatite
			if (!substring)
			{
				finalList.insertLast(firstA.element);
				firstA=firstA.succ;
			}
			
			// se resetiraat vrednostite
			Count=0;
			firstB=listB.getFirst();
			
			
			if (firstA!=null)
			{
				//ako prvite bukvi od dvete listi ne se isti
				//se vnesuva bukvata i se prodolzuva od narednata
				if (firstA.element!=firstB.element)
				{
					finalList.insertLast(firstA.element);
					firstA=firstA.succ;
				}
				
			}
			
		}
		
		//vnesuvanje na bukvite od konecnata lista v ostring
		DLLNode<Character> firstFinalList = finalList.getFirst(); 
		while(firstFinalList!=null)
		{
			sb.append(firstFinalList.element);
			firstFinalList=firstFinalList.succ;
		}
		
		finalWord=sb.toString();		
		System.out.println(finalWord);
		
		if (firstString!=null && secondString!=null){
		firstString.close();
		secondString.close();
		}
	}

}
