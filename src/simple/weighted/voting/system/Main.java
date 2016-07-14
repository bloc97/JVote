/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// Note to add system preventing repeating choices in close votes. (such as 30/20/30/10 or 50/50 votes, etc.)
package simple.weighted.voting.system;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author bowen
 */
public class Main {
    
    static int CHOICES;
    static float[] choiceList;
    static float percentage = 0;
    static float choicefloat;
    static float total;
    static int chosenmap;
    static boolean isDebug = true;
    static boolean isAdvDebug = false;
    
    
    public static float[] getVotes(){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of choices: ");Main.CHOICES=input.nextInt();
        
        
        choiceList = new float[Main.CHOICES];
        for (int i=0;i<Main.CHOICES;i++){
            if (i<Main.CHOICES-1 && Main.percentage<100F){
                System.out.print("Enter vote percent for map "+(i+1)+": ");choicefloat=input.nextFloat();
                if (choicefloat<=100-Main.percentage){
                    choiceList[i]=choicefloat;
                    Main.percentage = Main.percentage+choicefloat;
                }
                else
                {
                    choiceList[i]=100-Main.percentage;
                    Main.percentage=100F;
                }
            }
            else if (i==Main.CHOICES-1){
                choiceList[i]=100-Main.percentage;
                Main.percentage=100F;
            }
            else{
                choiceList[i]=0;
            }
        }
        return choiceList;
    }
    
    public static float randomGen(){
        Random random = new Random();
        Float value = random.nextFloat()*100;
        return value;
    }
    
    public static void getHighTableValue(float[] table){
        for (int i=1;i<table.length;i++){
            table[i]=table[i]+table[i-1];
        }
    }
    
    public static int getMap(float[] table, float value){
        for (int i=0;i<table.length;i++){
            if (value>table[i]){
                chosenmap=i+1;
            }
        }
        return chosenmap;
    }
    
    public static void printVotes(float[] table){
        for (int i=0;i<table.length;i++){
            System.out.println("Vote "+(i+1)+": "+choiceList[i]+"%");
            total = total+choiceList[i];
            
        }
        
        System.out.println("Total: "+total+"%");
    }
    
    public static void printHigh(float[] table){
        for (int i=0;i<table.length;i++){
            System.out.println("Vote "+(i+1)+": "+choiceList[i]+"%");
            total = total+choiceList[i];
            
        }
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        choiceList=getVotes();
        if (Main.isDebug){printVotes(choiceList);}
        
        getHighTableValue(choiceList);
        if (Main.isDebug){printHigh(choiceList);}
        
        Scanner input = new Scanner(System.in);
        
        if (Main.isDebug){
            
            System.out.print("Enter the number of rounds: ");int rounds=input.nextInt();
            for (int i=0;i<rounds;i++){
                chosenmap=0;
                float random=randomGen();
                if (Main.isAdvDebug){System.out.println(random+"%");}
                chosenmap=getMap(choiceList, random);
                System.out.println("The Chosen Map is: "+(chosenmap+1));
            }
            
            
        }
        else{
            
            float random=randomGen();
            chosenmap=getMap(choiceList, random);
            System.out.println("The Chosen Map is: "+(chosenmap+1));
        }
        
        
    }
    
}
