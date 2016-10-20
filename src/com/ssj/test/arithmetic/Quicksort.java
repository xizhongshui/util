package com.ssj.test.arithmetic;

public class Quicksort {

	private int getPivot(int begin, int end) {

		return (begin + end) >> 1;
	}

	// 一次排序
	private int partition(int[] array, int begin, int end) {
		int pivot = getPivot(begin, end);
		int tmp = array[pivot];
		array[pivot]=array[end];
		while (begin != end) {
			while (array[begin] < tmp && begin < end)
				begin++;
			if (/* array[begin] >= tmp && */begin < end) {
				array[end] = array[begin];
				end--;
			}
			while (array[end] >= tmp && end > begin)
				end--;
			if (/* array[end] < tmp && */end > begin) {
				array[begin] = array[end];
				begin++;
			}
		}
		// 此时两个下标指向同一个元素.以这个位置左右分治(分治点)
		array[begin] = tmp;
		return begin;
	}

	private void qsort(int[] array, int begin, int end) {

		if (end - begin < 1)
			return;
		int pivot = partition(array, begin, end);
		qsort(array, begin, pivot);
		qsort(array, pivot + 1, end);
	}

	public void sort(int[] array) {
		qsort(array, 0, array.length - 1);
	}

	public static void main(String[] args) {
//		int[] array = {9,8,7,6,3,2 };
//		int[] array = {9};
//		int[] array = {9,8 };
//		int[] array = {9,8,7 };
		int [] array={7,1,7,6,5,8,8,0,4,9};
		//int [] array={25,11,5,9,7,72,8,1,13,6};
		new Quicksort().sort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ", ");
		}
	}
}
