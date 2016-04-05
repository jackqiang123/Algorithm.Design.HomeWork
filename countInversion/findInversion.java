/**
 * HW1
 * Divide and conquer
 * find inversion pair is a typical interview question
 * Achieve O(nlogn)
 * @author lianlu
 *
 */
class findInversion{
	public long findInversion(int []nums){
		if (nums.length <= 1) return 0;
		return findInversion(nums,0,nums.length-1);		
	}

	private long findInversion(int []nums, int lo, int hi)
	{
		if (lo >= hi) return 0;
		int mid = (lo + hi)/2;
		return findInversion(nums, lo, mid) + findInversion(nums, mid + 1, hi) + quickCombine(nums, lo, hi, mid);
	}

	private long quickCombine(int []nums, int lo, int hi, int mid){
		long res = 0;
		int temp[] = new int[hi - lo + 1];
		int i = lo; 
		int j = mid + 1;
		int pos = 0;
		while(pos < temp.length){
			if (i <= mid && j <= hi)
			{
				if (nums[i] < nums[j])
					temp[pos++] = nums[i++];
				else {
					res += (mid - i + 1);
					temp[pos++] = nums[j++];
				} 
			}
			else if (i <= mid)
			{
				temp[pos++] = nums[i++];
			}
			else {
				temp[pos++] = nums[j++];
			}
		}
		for (i = lo; i <= hi; i++)
			nums[i] = temp[i-lo];
		return res;
	}

}