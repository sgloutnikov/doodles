package N136_SingleNumber;

/**
 Given an array of integers, every element appears twice except for one. Find that single one.
 Note:
 Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public class Solution {

    // XOR is commutative. XORing same two numbers = 0. XORing everything together will yield the single number.
    public int singleNumber(int[] nums) {
        int number = 0;
        for (int i = 0; i < nums.length; i++) {
            number = number ^ nums[i];
        }
        return number;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 3, 2, 3};
        System.out.println(solution.singleNumber(nums));
    }
}
