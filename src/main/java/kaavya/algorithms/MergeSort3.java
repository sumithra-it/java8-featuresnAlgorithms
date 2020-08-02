package kaavya.algorithms;

public class MergeSort3 {

public void mergeSort(int array[], int n){
	//split into 2 arrays
    if(n<2) return; 
    int m=n/2;
    int left[]=new int[m];
    int right[]=new int[n-m];
    int i;

    for(i=0; i<m;i++){
        left[i]=array[i];
    }
    for( i=m; i<n;i++){
        right[i-m]=array[i];
    }
    printArray(left);
    printArray(right);
    
    //sort left array
    mergeSort(left, m);
    
    //sort right array
    mergeSort(right, n-m);
    
    System.out.println("Calling merge: ");
    merge(array, left, right);
}

private void merge(int[] array, int[] left, int[] right) {
    int i=0,j=0,k=0;
    int leftCount = left.length;
    int rightCount = right.length;
    while(i<leftCount && j< rightCount){
        if(left[i]<=right[j]){
            array[k]=left[i];
            i++;
            k++;
        }else{
            array[k]=right[j];
            j++;
            k++;
        }
    }

    while(i<leftCount){
        array[k]=left[i];
        i++;
        k++;
    }
    while(j<rightCount){
        array[k]=right[j];
        j++;
        k++;
    }

}
	static void printArray(int arr[]){
	        int n = arr.length;
	        for (int i=0; i<n; ++i)
	            System.out.print(arr[i] + " ");
	        System.out.println();
    }

	public static void main(String[] args){
	    int a[]={3,2,1,7,9,8};
	    printArray(a);
	
	
	    MergeSort3 m=new MergeSort3();
	    m.mergeSort(a, a.length);
	
	    printArray(a);
	}
}