/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.algorithm.sort;

/**
 * @author abel created on 2017/8/25 下午3:15
 * @version $Id$
 */
public class QuickSort {

    /**
     * Implementation of QuickSort
     * 
     * <p>
     * Document
     * 
     * http://blog.csdn.net/morewindows/article/details/6684558
     *
     * </p>
     * 
     * @param nums
     */
    public void quickSort(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        sort(nums, 0, nums.length - 1);
    }

    public void sort(int[] nums, int begin, int end) {
        int i = begin, j = end;
        int pivot = nums[begin];
        if (begin < end) {
            while (i < j) {
                while (i < j && nums[j] >= pivot) {
                    j--;
                }
                if (i < j) {
                    swap(nums, i, j);
                    nums[j] = nums[i];
                    i++;
                }

                while (i < j && nums[i] < pivot) {
                    i++;
                }
                if (i < j) {
                    swap(nums,i,j);
                    nums[i] = nums[j];
                    j--;
                }
            }
            nums[i] = pivot;
            sort(nums, begin, i - 1);
            sort(nums, i + 1, end);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        int[] abc = new int[] { 3, 2, 1, 5, 4, 1 };
        sort.quickSort(abc);
        System.out.println(abc);
    }

}
