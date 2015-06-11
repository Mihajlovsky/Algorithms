import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** @author Mihajlovsky
 * 
 * Na vlez se vnesuva N broj, koj pretstavuva kolku broevi+iminja ke se vnesat.
 * Sledno se vnesuvaat broj i ime odeleni so prazno mesto. 
 * Potoa se vnesuva brojot koj treba da se prebaruva
 * vo format (+389 x xxx xxx ) ili vo lokalen format (0xx xxx xxx).
 * Dokolku brojot e pronajden se printa sopstvenikot na brojot, dokolku ne e se printa "Nepoznat Broj".
 * 
 * 
 * Primer vlez:
 * 3
 * 072111222 MagdelenaKostoska
 * 070222333 IlinkaIlievska
 * 077444555 IgorKulev
 * +38972111222
 * 
 * Primer izlez:
 * MagdelenaKostoska
 * 
 * Primer vlez: 
 * 2 
 * 070245114 AnastasMishev
 * 075241555 MagdelenaKostoska
 * 077266111 
 * 
 * Primer izlez:
 * Nepoznat broj
 * 
 * Primer vlez:
 * 1
 * 070000000 JovanMihajlovski
 * +3867000000
 * 
 * Primer izlez:
 * Nepoznat broj
 * 
 */


public class KojMeBara {
	public static void main (String[] args) throws IOException {
		
		
		CBHT<Integer,String> mobileNumbers;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
	
		mobileNumbers = new CBHT<Integer,String>(2*N);
		
		//popolnuvanje na hash tabelata
		int number=0;
		for(int i=1;i<=N;i++){
			String imelozinka = br.readLine();
			String[] pom = imelozinka.split(" ");
			number=Integer.valueOf(pom[0]);
			mobileNumbers.insert(number, pom[1]);
		}
		
		//prvo se vnesuva string pa se pretvora vo soodvetniot broj
		String MobNumber = br.readLine();
		String transformedNumber="0";
		String name="";
		
		
		// ako brojot zapocnuva so '+'
		if (MobNumber.charAt(0)=='+')
		{
			if (MobNumber.charAt(1)=='3' && MobNumber.charAt(2)=='8' && MobNumber.charAt(3)=='9')
			{
				for (int i=4;i<MobNumber.length();i++)
				{
					transformedNumber+=MobNumber.charAt(i);
				}
			}
			else{
				transformedNumber=null;
			}
			
		}
		else{
			transformedNumber=MobNumber;
		}
		
		
		if (transformedNumber==null)
		{
			System.out.print("Nepoznat broj");
		}
		
		//baranje na brojot vo hash tabelata
		if(transformedNumber!=null){
			
			int finalNumber=Integer.valueOf(transformedNumber);
			
			SLLNode<MapEntry<Integer, String>> ex= mobileNumbers.search(finalNumber);
			
			//ako e pronajden brojot
			if (ex!=null)
			{
				name=ex.element.value;
			}
			else{
				System.out.print("Nepoznat broj");
			}
		}
		
		System.out.print(name);
		
	}
}


class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {

    K key;
    E value;

    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }
    
    public int compareTo (K that) {
        @SuppressWarnings("unchecked")
		MapEntry<K,E> other = (MapEntry<K,E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString () {
        return "(" + key + "," + value + ")";
    }
}

class SLLNode<E> {
	protected E element;
	protected SLLNode<E> succ;

	public SLLNode(E elem, SLLNode<E> succ) {
		this.element = elem;
		this.succ = succ;
	}

	@Override
	public String toString() {
		return element.toString();
	}
}

class CBHT<K extends Comparable<K>, E> {

	private SLLNode<MapEntry<K,E>>[] buckets;

	@SuppressWarnings("unchecked")
	public CBHT(int m) {
		buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
	}

	private int hash(K key) {
		return Math.abs(key.hashCode()) % buckets.length;
	}

	public SLLNode<MapEntry<K,E>> search(K targetKey) {
		int b = hash(targetKey);
		for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
			if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
				return curr;
		}
		return null;
	}

	public void insert(K key, E val) {		// Insert the entry <key, val> into this CBHT.
		MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
		int b = hash(key);
		for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
			if (key.equals(((MapEntry<K, E>) curr.element).key)) {
				curr.element = newEntry;
				return;
			}
		}
		buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
	}

	public void delete(K key) {
		int b = hash(key);
		for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
			if (key.equals(((MapEntry<K,E>) curr.element).key)) {
				if (pred == null)
					buckets[b] = curr.succ;
				else
					pred.succ = curr.succ;
				return;
			}
		}
	}

	public String toString() {
		String temp = "";
		for (int i = 0; i < buckets.length; i++) {
			temp += i + ":";
			for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
				temp += curr.element.toString() + " ";
			}
			temp += "\n";
		}
		return temp;
	}

}

