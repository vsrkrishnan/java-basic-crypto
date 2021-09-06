package com.vshiva.leetcode.problems;

/**
 * Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to
 * search target in nums. If target exists, then return its index. Otherwise, return -1.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 *
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * Example 2:
 *
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 *
 * Constraints:
 *  * 1 <= nums.length <= 104
 *  * -104 < nums[i], target < 104
 *  * All the integers in nums are unique.
 *  * nums is sorted in ascending order.
 */

/**
 * @author sviswanathan
 * Sep 06, 2021
 */
public class BinarySearchInsert
{
    public static int searchInsert(int[] nums, int target) {
        int start = 0;
        int mid = 0;
        int end = nums.length - 1;

        while (start <= end) {
            System.out.println(start + ", " + mid + ", " + end);
            mid = (start + end) /2;
            if (nums[mid] == target) {
                return mid;
            }

            if (start == 0 && mid == 0) {
                return start;
            }

            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return mid + 1;
    }

    public static void main(String[] args) {
        int nums[] = { 1,3,5,6,10,15,25,33,50 };
        System.out.println(searchInsert(nums, 2));
    }
}
