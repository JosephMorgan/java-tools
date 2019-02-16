package com.dengbin.tools;
/**
 * Description: 冒泡排序
 * n个数字要排序完成，总共进行n-1趟排序，每i趟的排序次数为(n-i)次，所以可以用双重循环语句，
 * 外层控制循环多少趟，内层控制每一趟的循环次数
 * @author dengbin
 * @date 2018年11月29日
 * @time 下午11:03:34
 */
public class BubbleSort {
	public static void main(String[] args) {		
		int[]  a = {6,5,4,3,2,1};
		if (null !=a && 0 != a.length) {
			a = BubbleSort.sortBubble(a);
		}
		
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]+" ");
		}
		
	}
	
	public static int[] sortBubble(int[] numberArray) {
		if(null != numberArray && 0 !=numberArray.length ){
			for (int i = 0; i < numberArray.length-1; i++) {
				for (int j = 0; j < numberArray.length-1-i; j++) {
					if(numberArray[j]>numberArray[j+1]) {
						int temp = numberArray[j];
						numberArray[j] = numberArray[j+1];
						numberArray[j+1] = temp;
					}
				}
			}
		}
		return numberArray;
		
	}

}