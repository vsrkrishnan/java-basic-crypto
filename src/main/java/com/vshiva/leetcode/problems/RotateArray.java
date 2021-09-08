package com.vshiva.leetcode.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
 *
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 *
 * Constraints:
 *
 * * 1 <= nums.length <= 105
 * * -231 <= nums[i] <= 231 - 1
 * * 0 <= k <= 105
 *
 * Follow up:
 *
 * Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 */

/**
 * @author sviswanathan
 * Sep 08, 2021
 */
public class RotateArray
{
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        k = n - k;
        reverse(nums, 0, k-1);
        //System.out.println(Arrays.toString(nums));
        reverse(nums, k, n-1);
        //System.out.println(Arrays.toString(nums));
        reverse(nums, 0, n-1);
        //System.out.println(Arrays.toString(nums));
    }

    public static void reverse(int[] nums, int start, int end) {
        int tmp = 0;
        while (start < end) {
            tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }

    public static int[] arrayCopyMethod(int[] nums, int k) {
        int[] numsCopy = new int[nums.length];
        int index = 0;
        for (int i = k + 1; i<nums.length; i++) {
            numsCopy[index] = nums[i];
            index++;
        }

        for (int i = 0; i<=k; i++) {
            numsCopy[index] = nums[i];
            index++;
        }

        System.out.println(Arrays.toString(numsCopy));

        return numsCopy;
    }

    public static void main(String[] args) {
        Map<Integer, int[]> valueToListMap = new HashMap<>();
        valueToListMap.put(3, new int[] { 1, 2, 3, 4, 5, 6, 7 });
        valueToListMap.put(2, new int[] { -1, -100, 3, 99 });
        valueToListMap.put(5, new int[] { 1, 2 });
        valueToListMap.put(4, new int[] { 5 });

        for (var rotateValue : valueToListMap.keySet()) {
            int[] nums = valueToListMap.get(rotateValue);
            rotate(nums, rotateValue);
            System.out.println(Arrays.toString(nums));
        }
    }
}
