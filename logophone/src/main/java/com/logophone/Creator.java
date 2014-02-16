package com.logophone;

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

    private int aZipper[] = {6, 7};

    private int aUnderShirt[] = {2, 4};

    private int alower[] = {1, 3, 5};

    private int aUpperLo[] = {2, 4, 6};

    private int aUpperUp[] = {7, 8, 9};

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

    public int[] GetZipperType(){
        if(phone_number[3] == 0) phone_number[3] = 10;
        Integer check_array[] = {phone_number[5], phone_number[7], phone_number[9]};
        int clothes[] = {phone_number[3]*1000 + phone_number[5]*100 + phone_number[4],
                phone_number[3]*1000 + phone_number[7]*100 + phone_number[6],
                phone_number[3]*1000 + phone_number[9]*100 + phone_number[8]};
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!! " + Arrays.toString(clothes));                                                                      //<<======
        if(Arrays.asList(check_array).contains(6) || Arrays.asList(check_array).contains(7)){
            System.out.println("There is 6 or 7 in array! " + Arrays.toString(check_array));                                                                      //<<======
            if(Arrays.asList(check_array).contains(6) && Arrays.asList(check_array).contains(7)){
                for(int i=0; i < clothes.length; i++){
                    System.out.println("1: " + (clothes[i] - phone_number[4 + i*2] - phone_number[3]*1000)/100);                                                                      //<<======
                    clothes[i] = ((clothes[i] - phone_number[4 + i*2] - phone_number[3]*1000)/100) == 6 ? clothes[i] + 10 : clothes[i];
                }
            } else if(Arrays.asList(check_array).contains(6) && Arrays.asList(check_array).contains(0) &&
                    !Arrays.asList(check_array).contains(2) && !Arrays.asList(check_array).contains(4)){
                for(int i=0; i < clothes.length; i++){
                    System.out.println("2: " + (clothes[i] - phone_number[4 + i*2] - phone_number[3]*1000)/100);                                                                      //<<======
                    clothes[i] = ((clothes[i] - phone_number[4 + i*2] - phone_number[3]*1000)/100) == 6 ? clothes[i] + 10 : clothes[i];
                }
            } else if((Arrays.asList(check_array).contains(6) || Arrays.asList(check_array).contains(7))
                    && !Arrays.asList(check_array).contains(2) && !Arrays.asList(check_array).contains(4) && !Arrays.asList(check_array).contains(0)){
                for(int i=0; i < clothes.length; i++){
                    System.out.println("3: " + (clothes[i] - phone_number[4 + i*2] - phone_number[3]*1000)/100);                                                                      //<<======
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
//        int clothes[] = {phone_number[3]*1000 + phone_number[5]*100 + phone_number[4],
//                phone_number[3]*1000 + phone_number[7]*100 + phone_number[6],
//                phone_number[3]*1000 + phone_number[9]*100 + phone_number[8]};
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!! " + Arrays.toString(clothes));
//        if(Arrays.asList(check_array).contains(6) || Arrays.asList(check_array).contains(7)){
//            System.out.println("There is 6 or 7 in array! " + Arrays.toString(check_array));
//            if(Arrays.asList(check_array).contains(6) && Arrays.asList(check_array).contains(7)){
//                for(int i=0; i < clothes.length; i++){
//                    System.out.println("1: " + (clothes[i] - phone_number[4 + i*2] - phone_number[3]*1000)/100);
//                    clothes[i] = ((clothes[i] - phone_number[4 + i*2] - phone_number[3]*1000)/100) == 6 ? clothes[i] + 10 : clothes[i];
//                }
//            } else if(Arrays.asList(check_array).contains(6) && Arrays.asList(check_array).contains(0) &&
//                    !Arrays.asList(check_array).contains(2) && !Arrays.asList(check_array).contains(4)){
//                for(int i=0; i < clothes.length; i++){
//                    System.out.println("2: " + (clothes[i] - phone_number[4 + i*2] - phone_number[3]*1000)/100);
//                    clothes[i] = ((clothes[i] - phone_number[4 + i*2] - phone_number[3]*1000)/100) == 6 ? clothes[i] + 10 : clothes[i];
//                }
//            } else if((Arrays.asList(check_array).contains(6) || Arrays.asList(check_array).contains(7))
//                    && !Arrays.asList(check_array).contains(2) && !Arrays.asList(check_array).contains(4) && !Arrays.asList(check_array).contains(0)){
//                for(int i=0; i < clothes.length; i++){
//                    System.out.println("3: " + (clothes[i] - phone_number[4 + i*2] - phone_number[3]*1000)/100);
//                    clothes[i] = ((clothes[i] - phone_number[4 + i*2] - phone_number[3]*1000)/100) == 6
//                            || ((clothes[i] - phone_number[4 + i*2] - phone_number[3]*1000)/100) == 7 ? clothes[i] + 10 : clothes[i];
//                }
//            }
//        }
        Arrays.sort(clothes);
        if(Arrays.asList(check_array).contains(0)){
            System.out.println("There is 0 in array!");                                                                      //<<======
//            if(Arrays.asList(check_array).containsAll(Arrays.asList(aUpperUp))
//                    && Arrays.asList(check_array).contains(Arrays.asList(aUpperLo))) {
            if((Arrays.asList(check_array).contains(7)
                    && Arrays.asList(check_array).contains(8))
                    || (Arrays.asList(check_array).contains(7)
                    && Arrays.asList(check_array).contains(9))){
                //do nothing
            } else if((Arrays.asList(check_array).contains(7)
                    || Arrays.asList(check_array).contains(8)
                    || Arrays.asList(check_array).contains(9))) {
                moveLastup(clothes, 1);
            } else {
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

//==================================================================================================

    public boolean checkForStrict(Integer number[]){
        boolean result = false;

        if( number[5] == number[7] || 				//
            number[7] == number[9] ||				//  6, 8, 10 are equal
            number[5] == number[9] ||				//
            elements[number[5]][number[7]] == 1 ||
            elements[number[7]][number[9]] == 1 ||
            elements[number[5]][number[9]] == 1 )
        {
            result =  true; // only strict logotype
        }
        return result;
    }

    public boolean checkForGlasses(Integer number[]){
        boolean result = false;

        if( number[5] == number[9] ||				// 6 and 10 are equal
                elements[number[5]][number[9]] == 1 )
        {
            result = true;	// we need glasses in logotype
        }
        return result;
    }

    public int chechForOverlap(Integer number[]){
        if(elements[number[5]][number[7]] == 2)
        {
            if(number[5] > number[7])
            {
                return 1;
            }
            else
            {
                return 2;
            }
        }
        return 0;
    }

    public int checkForOverlap_3rd(Integer number[]){
        if(elements[number[5]][number[9]] == 2 ||
            elements[number[7]][number[9]] == 2)
        {
            if(number[5] > number[9] && number[7] > number[9])
            {
                return 1;
            }
            else
            {
                return 2;
            }
        }
        return 0;
    }

    public int checkGalstuk(Integer number[]){
        if(number[5] == 0 || number[7] == 0 || number[9] == 0)
            return 1;
        return 0;
    }

    public int checkLower(Integer number[]){
        if(number[5] == 5 || number[5] == 3 || number[5] == 1
            || number[7] == 5 || number[7] == 3 || number[7] == 1
            || number[9] == 5 || number[9] == 3 || number[9] == 1)
            return 2;
        return 0;
    }


}
