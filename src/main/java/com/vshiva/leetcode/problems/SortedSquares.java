package com.vshiva.leetcode.problems;

import java.util.Arrays;

/**
 * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted
 * in non-decreasing order.

 * Example 1:

 * Input: nums = [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * Explanation: After squaring, the array becomes [16,1,0,9,100].
 * After sorting, it becomes [0,1,9,16,100].
 *
 * Example 2:
 *
 * Input: nums = [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 *
 * Constraints:
 *  - 1 <= nums.length <= 104
 *  - -104 <= nums[i] <= 104
 *  - nums is sorted in non-decreasing order.
 */

/**
 * @author sviswanathan
 * Sep 06, 2021
 */
public class SortedSquares
{
    public static int[] sortedSquaresWithArraysSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] *= nums[i];
        }

        Arrays.sort(nums);
        return nums;
    }

    public static int[] sortedSquaresWithOofN(int[] nums) {
        int left = 0, right = nums.length-1;
        int[] output = new int[nums.length];
        int k = nums.length -1;
        while (left <= right) {
            int leftsq =  nums[left] * nums[left];
            int rightsq =  nums[right] * nums[right];

            if (rightsq > leftsq) {
                output[k] = rightsq;
                right--;
            } else {
                output[k] = leftsq;
                left++;
            }
            k--;
        }

        return output;
    }

    public static void main(String[] args) {
        int[] nums = {-4,-1,0,3,10};
        int[] sortedNums = sortedSquaresWithArraysSort(nums);
        for (int sortedNum : sortedNums) {
            System.out.print(sortedNum + " ");
        }
    }
}
