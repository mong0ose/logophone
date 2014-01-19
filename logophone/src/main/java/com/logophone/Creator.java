package com.logophone;

/**
 * Created by mongOose on 21.11.13.
 */
public class Creator {
    public Creator(){

    }

    int elements[][] = {
        {1,0,0,0,0,0,0,0,0,0},
        {0,1,0,1,0,1,0,0,0,0},
        {0,0,1,0,1,0,2,2,2,2},
        {0,1,0,1,0,1,0,0,0,0},
        {0,0,1,0,1,0,2,2,2,2},
        {0,1,0,1,0,1,2,0,0,0},
        {0,0,2,0,2,2,1,2,2,2},
        {0,0,2,0,2,0,2,1,2,2},
        {0,0,2,0,2,0,2,2,1,1},
        {0,0,2,0,2,0,2,2,1,1}};


    public boolean checkForStrict(Integer number[]){
        boolean result = false;

        if( number[5] == number[7] || 				    //
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
