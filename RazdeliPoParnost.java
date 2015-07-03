import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RazdeliPoParnost {

public static void main(String[] args) throws IOException {
		
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String s = stdin.readLine();
		int N = Integer.parseInt(s);
		s = stdin.readLine();
		String[] pomniza = s.split(" ");
        SLL<Integer> lista = new SLL<>();
        
		for (int i = 0; i < N; i++) {
			lista.insertLast(Integer.parseInt(pomniza[i]));
        }
		
		
		SLL<Integer> neparni = new SLL<Integer>(); // genericka integer instanca od klasata SLL 
		SLL<Integer> parni = new SLL<Integer>();
		
	
		
		SLLNode<Integer> first = lista.getFirst();
		SLLNode<Integer> last = lista.getFirst();
		
		
		while (last.succ!=null) // naoganje na posledniot element
		{
			last=last.succ;
		}
		
		
		if (lista.length()>0)
		{
			
			while(first.succ!=null){ // za da odi do posledniot
			if (first.element%2==0)
				
			{
				parni.insertLast(first.element);
			}
			
			else 
			{
				neparni.insertLast(first.element);
			}
			first=first.succ; //isto kako i++
			}
			
		}
		
		
		if(neparni.length()>0) // ako ne e prazna lista
		{
			first=neparni.getFirst(); // i=0;
			
			while(first!=null) // i <n
			{
				if (first.succ!=null)
				{
					System.out.print(first.element + " ");
				}
				
				else {
					System.out.println(first.element);
				}
				
			first=first.succ; // i++
			}
		}
		
		if(parni.length()>0)
		{
			first=parni.getFirst();
			
			while(first!=null)
			{
				if (first.succ!=null)
				{
					System.out.print(first.element + " ");
				}
				
				else {
					System.out.println(first.element);
				}
				
			first=first.succ;
			}
		}
		
		
}}

