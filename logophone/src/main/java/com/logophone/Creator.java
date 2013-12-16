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
        {0,0,1,0,1,0,2,2,0,0},
        {0,1,0,1,0,1,0,0,0,0},
        {0,0,1,0,1,0,2,2,0,0},
        {0,1,0,1,0,1,0,0,0,0},
        {0,0,2,0,2,0,1,2,0,0},
        {0,0,2,0,2,0,2,1,0,0},
        {0,0,0,0,0,0,0,0,1,1},
        {0,0,0,0,0,0,0,0,1,1}};


    public boolean checkForStrict(int number[]){
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

    public boolean checkForGlasses(int number[]){
        boolean result = false;

        if( number[5] == number[9] ||				// 6 and 10 are equal
                elements[number[5]][number[9]] == 1 )
        {
            result = true;	// we need glasses in logotype
        }
        return result;
    }

    public int chechForOverlap(int number[]){
        if(elements[number[5]][number[7]] == 2)
        {
            if(number[5] > number[7])
            {
                return 1;	// second cloth must be unbuttoned
            }
            else
            {
                return 2;	// second cloth must be unbuttoned
            }
        }
        return 0;
    }


}
