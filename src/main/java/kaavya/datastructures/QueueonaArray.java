package kaavya.datastructures;

public class QueueonaArray {
	int[] arr;
	int endIndex = -1;
	int capacity;
	
	QueueonaArray(int size){
		arr = new int[size];
		capacity = size; //total capacity initial value
	}
	
	public void add(int num) { //add to the end of the array
		if ( endIndex == capacity-1) {//time to increase the capacity of the array
			//create a new array and copy all the old elements to the new array
			capacity = capacity + 5;
			int[] newarr = new int[capacity];
			for (int i=0; i <= endIndex; i++ ) {
				newarr[i] = arr[i];
			}
			arr = newarr;
		}
		//add the new int as last element
		arr[++endIndex] = num;
		
		System.out.println("Added num:" + arr[endIndex] + " to position " + endIndex);
	}
	
	public int remove() {
		int num = arr[0];
		
		//remove the first element and move the rest of the elements to the top
		//int sizeminus1 = arr.length - 1;
		for (int i = 0; i < endIndex; i++ ) {
			arr[i] = arr[i+1];
		}
		
		--endIndex;
		//DEBUG: System.out.println("removed num " + num + " and endIndex is at: " + endIndex);
		return num;
	}
	
	public int get() {
		return arr[0];
	}
	
	public static void main(String[] args) {
		QueueonaArray queueArr = new QueueonaArray(5);
		
		
		//add to array
		queueArr.add(10);queueArr.add(20);queueArr.add(30);
		queueArr.add(40);queueArr.add(50);
		queueArr.add(60);queueArr.add(70);queueArr.add(80);
		
		System.out.println("Removing all elements from the Queue: ");
		int size = queueArr.endIndex;
		for (int i=0; i<=size; i++)
			System.out.println("Removing " + queueArr.remove() + ", ");
//		queueArr.remove();
//		queueArr.remove();
//		queueArr.remove();
//		queueArr.remove();
//		queueArr.remove();

	}

}
