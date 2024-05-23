package com.jk.test;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        //需求；
        //把一个一维数组中的数据：0~15打乱
        //然后再按照4个一组的方式添加到二维数组当中

        //1、定义一个一维数组
        int[] arr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        //打乱数组中数据的顺序

        //遍历数组，得到每一个元素，然后那着每一个元素跟随机生成的索引上的数据进行交换
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            //得到随机索引
            int index = r.nextInt(arr.length);
            int t = arr[i];
            arr[i] = arr[index];
            arr[index] = t;
        }
        //3、遍历数组
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        //4、创建一个二维数组
        int[][] data = new int[4][4];

        //5、给二维数组添加数据
        //解法一：
//        for (int i = 0; i < arr.length; i++) {
//            data[i / 4][i % 4] = arr[i];
//        }

        //解法二：
        //遍历二维数组，给里面的每一个数据赋值
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = arr[i * 4 + j];
            }
        }

        //遍历二维数组
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }
}
