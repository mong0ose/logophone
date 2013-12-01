package com.logophone;

/**
 * Created by mongOose on 21.11.13.
 */
public class Creator {
    public Creator(){

    }

    private int elements[][] = {
        {1,0,1,0,1,0,0,0,0,0},
        {0,1,0,0,0,0,0,0,0,0},
        {1,0,1,0,1,0,0,0,0,0},
        {0,0,0,1,0,0,0,0,0,0},
        {1,0,1,0,1,0,0,0,0,0},
        {0,0,0,0,0,1,0,0,0,0},
        {0,0,0,0,0,0,1,0,0,0},
        {0,0,0,0,0,0,0,1,1,0},
        {0,0,0,0,0,0,0,1,1,0},
        {0,0,0,0,0,0,0,0,0,1} };

    public boolean checkForStrict(int number[])
    {
        boolean result = false;

        if( number[6] == number[8] || 				    //
                number[8] == number[10] ||				//  6, 8, 10 are equal
                number[6] == number[10] ||				//
                elements[number[6]][number[8]] == 1 ||
                elements[number[8]][number[10]] == 1 ||
                elements[number[6]][number[10]] == 1 )
        {
            result =  true; // only strict logotype
        }
        return result;
    }

    public boolean checkForGlasses(int number[])
    {
        boolean result = false;

        if( number[6] == number[10] ||				// 6 and 10 are equal
                elements[number[6]][number[10]] == 1 )
        {
            result = true;	// we need glasses in logotype
        }
        return result;
    }

}
