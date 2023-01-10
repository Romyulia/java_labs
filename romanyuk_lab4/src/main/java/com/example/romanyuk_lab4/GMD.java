package com.example.romanyuk_lab4;

public class GMD {

    public static int calculate(int first, int second, String ans){
        int counter = 1;
        if(first == 0 || second == 0){
            return 1;
        }
        if(first<0){
            first = first * (-1);
        }
        if(second<0){
            second = second * (-1);
        }
        do{
            if(first > second){
                if(ans.equals("y")){
                    int helper = first-second;
                    System.out.println(counter + ") " + first + "-" + second + "=" + helper);
                    System.out.println();
                }
                first = first-second;
            }
            else if(first < second){
                if(ans.equals("y")){
                    int helper = second-first;
                    System.out.println(counter + ") " + second + "-" + first + "=" + helper);
                    System.out.println();
                }
                second = second - first;
            }
            counter++;
        }while(first!=second);

        if(ans.equals("y")){
            System.out.println(counter + ") " + second + "=" + first);
            System.out.println();
        }
        return first;
    }
}
