package com.logophone;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by mongOose on 21.11.13.
 */
public class Creator {
    private Integer phone_number[];

    public Creator(){}

    public Creator(Integer phone[]){
        phone_number = phone;
    }

    private Integer[] collision_chars = {1, 2, 4, 5};

    private Integer[] aZipper = {6, 7};
//
//    private Integer[] aUnderShirt = {2, 4};
//
//    private Integer[] alower = {1, 3, 5};
//
//    private Integer[] aUpperLo = {2, 4, 6};
//
//    private Integer[] aUpperUp = {7, 8, 9};

    private int elements[][] = {
        {1,0,0,0,0,0,2,0,0,0},
        {0,1,0,1,0,1,2,0,0,0},
        {0,0,1,0,1,0,2,2,2,2},
        {0,1,0,1,0,1,2,0,0,0},
        {0,0,1,0,1,0,2,2,2,2},
        {0,1,0,1,0,1,2,0,0,0},
        {2,2,2,2,2,2,1,2,2,2},
        {0,0,2,0,2,0,2,1,2,2},
        {0,0,2,0,2,0,2,2,1,1},
        {0,0,2,0,2,0,2,2,1,1}};


    public boolean newCheckStrict(){
        boolean result = false;
        if(elements[phone_number[5]][phone_number[9]] == 1)
            result = true;
        return result;
    }

    public boolean newCheckPleasure(){
        boolean result = false;
        if(elements[phone_number[5]][phone_number[9]] == 2
                || elements[phone_number[5]][phone_number[9]] == 0)
            result = true;
        return result;
    }

    public boolean newCheckNoStrict() {
        boolean result = false;
        if(elements[phone_number[7]][phone_number[9]] != 1
                && elements[phone_number[5]][phone_number[7]] != 1
                && elements[phone_number[5]][phone_number[9]] != 1)
            result = true;
        return result;
    }

    public int indexOfIntArray(int[] array, int key) {
        int returnvalue = -1;
        for (int i = 0; i < array.length; i++) {
            if (key == array[i]) {
                returnvalue = i;
                break;
            }
        }
        return returnvalue;
    }

    public int[] GetZipperType(){
        if(phone_number[3] == 0) phone_number[3] = 10;
        Integer check_array[] = {phone_number[5], phone_number[7], phone_number[9]};
        int clothes[] = {phone_number[3]*1000 + phone_number[5]*100 + phone_number[4],
                phone_number[3]*1000 + phone_number[7]*100 + phone_number[6],
                phone_number[3]*1000 + phone_number[9]*100 + phone_number[8]};
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!! " + Arrays.toString(clothes));                                                                      //<<======
        if(Arrays.asList(check_array).contains(6) || Arrays.asList(check_array).contains(7)){
            System.out.println("There is 6 or 7 in array! " + Arrays.toString(check_array));                                                                      //<<======
            if((Arrays.asList(check_array).contains(8) || Arrays.asList(check_array).contains(9))
                    && Arrays.asList(check_array).contains(7) && Arrays.asList(collision_chars).contains(phone_number[3])){
                for(int i=0; i < clothes.length; i++){
                    System.out.println("1: " + (clothes[i] - phone_number[4 + i*2] - phone_number[3]*1000)/100);                                                                      //<<======
                    clothes[i] = ((clothes[i] - phone_number[4 + i*2] - phone_number[3]*1000)/100) == 6
                            || ((clothes[i] - phone_number[4 + i*2] - phone_number[3]*1000)/100) == 7 ? clothes[i] + 10 : clothes[i];
                }
            } else if(Arrays.asList(check_array).contains(6) && Arrays.asList(check_array).contains(7)
                    && (Arrays.asList(check_array).contains(4) || Arrays.asList(check_array).contains(2))){
                // do nothing
            } else if(Arrays.asList(check_array).contains(6) && Arrays.asList(check_array).contains(7)){
                for(int i=0; i < clothes.length; i++){
                    System.out.println("2: " + (clothes[i] - phone_number[4 + i*2] - phone_number[3]*1000)/100);                                                                      //<<======
                    clothes[i] = ((clothes[i] - phone_number[4 + i*2] - phone_number[3]*1000)/100) == 6 ? clothes[i] + 10 : clothes[i];
                }
            } else if(Arrays.asList(check_array).contains(6) && Arrays.asList(check_array).contains(0) &&
                    !Arrays.asList(check_array).contains(2) && !Arrays.asList(check_array).contains(4)){
                for(int i=0; i < clothes.length; i++){
                    System.out.println("3: " + (clothes[i] - phone_number[4 + i*2] - phone_number[3]*1000)/100);                                                                      //<<======
                    clothes[i] = ((clothes[i] - phone_number[4 + i*2] - phone_number[3]*1000)/100) == 6 ? clothes[i] + 10 : clothes[i];
                }
            } else if((Arrays.asList(check_array).contains(6) || Arrays.asList(check_array).contains(7))
                    && !Arrays.asList(check_array).contains(2) && !Arrays.asList(check_array).contains(4) && !Arrays.asList(check_array).contains(0)){
                for(int i=0; i < clothes.length; i++){
                    System.out.println("4: " + (clothes[i] - phone_number[4 + i*2] - phone_number[3]*1000)/100);                                                                      //<<======
                    clothes[i] = ((clothes[i] - phone_number[4 + i*2] - phone_number[3]*1000)/100) == 6
                            || ((clothes[i] - phone_number[4 + i*2] - phone_number[3]*1000)/100) == 7 ? clothes[i] + 10 : clothes[i];
                }
            }
        }
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!! " + Arrays.toString(clothes));                                                                      //<<======
        return clothes;
    }

    public int[] SortClothes(int input[]){
        Integer check_array[] = {phone_number[5], phone_number[7], phone_number[9]};
        int clothes[] = new int[3];
        System.arraycopy(input, 0, clothes, 0, input.length);
//        if(check_array[1] != 0 && check_array[2] != 0)
            Arrays.sort(clothes);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!! " + Arrays.toString(clothes));                                                                      //<<======
        if(Arrays.asList(check_array).contains(0)){
            System.out.println("There is 0 in array!");                                                                      //<<======
            if((Arrays.asList(check_array).contains(7)
                    && Arrays.asList(check_array).contains(8))
                    || (Arrays.asList(check_array).contains(7)
                    && Arrays.asList(check_array).contains(9))){
                System.out.println("Do nothing with sort");
                //do nothing
            } else if(check_array[1] == 0 && check_array[2] == 0) {
                moveLastup(clothes, 1);
                moveLastup(clothes, 2);
            } else if((Arrays.asList(check_array).contains(7)
                    || Arrays.asList(check_array).contains(8)
                    || Arrays.asList(check_array).contains(9))) {
                moveLastup(clothes, 1);
            } else {
                System.out.println("Sorting: move to upper(2");
                moveLastup(clothes, 2);
            }
        }
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!! " + Arrays.toString(clothes));                                                                      //<<======
        return clothes;
    }

    public static void moveLastup(int[] stuff, int position)
    {
        int y = stuff[0];

        for (int x = 0; x < position; x++)
            stuff[x] = stuff[x+1];

        stuff[position] = y;
    }
}
