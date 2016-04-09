import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Sort {

  public long quickSort(int[] nums) {
    return quickSortHelper(nums, 0, nums.length - 1);
  }

  private long quickSortHelper(int[] nums, int lo, int hi) {
    if (lo >= hi)
      return 0;
    //int pivot = partitionFirst(nums, lo, hi);
    //int pivot = partitionLast(nums, lo, hi);
    int pivot = partitionMedian(nums, lo, hi);
    long thisCompare = hi - lo;
    return quickSortHelper(nums, lo, pivot - 1) + quickSortHelper(nums, pivot + 1, hi)
        + thisCompare;
  }

  private int partitionFirst(int[] nums, int lo, int hi) {
    swap(nums, lo, lo);
    int i = lo + 1;
    int p = nums[lo];
    for (int j = i; j <= hi; j++) {
      if (nums[j] < p)
        swap(nums, i++, j);
    }
    swap(nums,i-1,lo);
    return i - 1;
  }

  private int partitionLast(int[] nums, int lo, int hi) {
    swap(nums, lo, hi);
    int i = lo + 1;
    int p = nums[lo];
    for (int j = i; j <= hi; j++) {
      if (nums[j] < p)
        swap(nums, i++, j);
    }
    swap(nums,i-1,lo);
    return i - 1;
  }

  private int partitionMedian(int[] nums, int lo, int hi) {
    int lovalue = nums[lo];
    int hivalue = nums[hi];
    int median = nums[(lo + hi) / 2];
    int minVal = Math.min(lovalue, Math.min(median, hivalue));
    int maxVal = Math.max(lovalue, Math.max(median, hivalue));
    int pivot = lo;
    if (median != minVal && median != maxVal)
      pivot = (lo + hi) / 2;
    else if (hivalue != minVal && hivalue != maxVal)
      pivot = hi;
    swap(nums, lo, pivot);
    int i = lo + 1;
    int p = nums[lo];
    for (int j = i; j <= hi; j++) {
      if (nums[j] < p)
        swap(nums, i++, j);
    }
    swap(nums,i-1,lo);
    return i - 1;
  }

  private void swap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
  }
}


public class QuickSort {
  public static void main(String[] args) {
      int []nums = new int[10000];
      Scanner scanner = null;
      try {
        scanner = new Scanner(new File("QuickSort.txt"));
      } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      int i = 0;
      while(scanner.hasNextInt()){
        nums[i++] = scanner.nextInt();
      }
      Sort sort = new Sort();
      System.out.println(sort.quickSort(nums));
  }
}
