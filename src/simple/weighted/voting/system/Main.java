/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.weighted.voting.system;

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
    
    
    public static void getVotes(){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of choices: ");CHOICES=input.nextInt();
        
        
        choiceList = new float[CHOICES];
        for (int i=0;i<CHOICES;i++){
            if (i<CHOICES-1 && Main.percentage<100F){
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
            else if (i==CHOICES-1){
                choiceList[i]=100-Main.percentage;
                Main.percentage=100F;
            }
            else{
                choiceList[i]=0;
            }
        }
        
    }
    
    public static void printVotes(){
        for (int i=0;i<CHOICES;i++){
            System.out.println(choiceList[i]+"%");
            total = total+choiceList[i];
            
        }
        
        System.out.println("Total: "+total+"%");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        getVotes();
        
        printVotes();
        
    }
    
}
