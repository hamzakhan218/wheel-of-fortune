import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;   

public class main {
    public void displayMenu(){
        System.out.println("1-Spin the wheel");
        System.out.println("2-Guess a letter");
        System.out.println("3-Buy a vowel");
        System.out.println("4-Solve the puzzle");
        System.out.println("5-Goto Next Round");
    }
    public static void main(String[] args) {
        main object=new main();
        String[] spiningWheel={"TURN","800","350","450","700","300","600","5000","300","600","300","500","800","550","400","300","900","500","SPIN","900","BANKRUPT","600","400","300"};
        Scanner input= new Scanner(System.in);
        String Players[] = new String[3];
        for(int i=0;i<3;i++)
        {
            System.out.print("\nEnter Player "+(i+1)+" name: ");
            Players[i]=input.nextLine();
        }
        int[] playerBanks={0,0,0};
        String content="";
        // System.out.print("\nPlease Provide the filename: ");
        // String filename=input.nextLine();
        try {
            // File file = new File(filename);
            File file = new File("text.txt");
            Scanner fileHandler=new Scanner(file);
            while(fileHandler.hasNextLine())
            {
                content=fileHandler.nextLine();
            }
            fileHandler.close();
        } catch (FileNotFoundException e) {
            System.out.println("Wrong filename was entered");
            e.printStackTrace();
        }
        int playerNumber=0;
        while(playerNumber<3){
            int drawer=0;String valuesGuessed=" ";boolean flag=true;
            while(flag){
                System.out.println("\n\nCURRENT PLAYER: "+Players[playerNumber]+" and their amount: $"+playerBanks[playerNumber]);
                object.displayMenu();
                int value=input.nextInt();
                if(value==1){
                    Random random = new Random();
                    int x = random.nextInt(24);   
                    if(spiningWheel[x]!="SPIN" && spiningWheel[x]!="TURN" && spiningWheel[x]!="BANKRUPT"){
                        System.out.println("\nYou got: $"+spiningWheel[x]);
                        drawer=Integer.parseInt(spiningWheel[x]);
                    }
                    else{
                        if(spiningWheel[x]=="BANKRUPT"){
                            System.out.println("\nYou are Bankrupt!!\n");
                            playerBanks[playerNumber]=0;
                            flag=false;
                        }
                        else if(spiningWheel[x]=="TURN"){
                            System.out.println("\n\nYou have Lose your turn!!!\n\n");
                            flag=false;
                        }
                    }
                }
                else if(value==2){
                    System.out.println("Enter your letter: ");
                    Scanner obj=new Scanner(System.in);
                    String letter =obj.nextLine();
                    valuesGuessed +=letter;
                    int count=0;
                    for (char a : content.toCharArray()){
                        if (valuesGuessed.indexOf(a)== -1){
                            System.out.print('*');
                        }
                        else{
                            if(a!=' '){
                                count+=1;
                            }
                            System.out.print(a);
                        }
                    }
                    playerBanks[playerNumber]+=count*drawer;

                }
                else if(value==3){
                    if(playerBanks[playerNumber]>=100){
                        System.out.println("Please enter a vowel to borrow: ");
                        Scanner obj=new Scanner(System.in);
                        String vowel= obj.nextLine();
                        if(vowel=="a" || vowel=="e" || vowel=="i" || vowel=="o" || vowel=="u" )
                        {
                            valuesGuessed+=vowel;
                            for (char a : content.toCharArray()){
                                if (valuesGuessed.indexOf(a)== -1){
                                    System.out.print('*');
                                }
                                else{
                                    System.out.print(a);
                                }
                            }
                            playerBanks[playerNumber]-=100;
                        }
                        else
                        {
                            System.out.println("\nNot a vowel...!");
                            flag=false;
                        }
                    }
                    else{
                        System.out.println("\n\nCannot borrow vowel, you have insufficient money!!...");;
                    }

                }
                else if(value==4){
                    int count=0;
                    for (char a : content.toCharArray()){
                        if (valuesGuessed.indexOf(a)== -1){
                            System.out.print('*');
                        }
                        else{
                            count+=1;
                            System.out.print(a);
                        }
                    }
                    int tempCount=0;
                    while(tempCount!=count){
                        count=tempCount;
                        tempCount=0;
                        Scanner obj=new Scanner(System.in);
                        String temp= obj.nextLine();
                        valuesGuessed+=temp;
                        for (char a : content.toCharArray()){
                            if (valuesGuessed.indexOf(a)== -1){
                                System.out.print('*');
                            }
                            else{
                                tempCount+=1;
                                System.out.print(a);
                            }
                        }
                        playerBanks[playerNumber]+=(tempCount-count)*drawer;
                    }
                    flag=false;
                    
                }
                else if(value==5){flag=false;}
            }
            playerNumber+=1;
        }
        if(playerBanks[0]>playerBanks[1] && playerBanks[0]>playerBanks[2]){
            System.out.println("Player 1 Wins, Player name: "+Players[0]+" and amount: "+playerBanks[0]);
        }
        else if(playerBanks[1]>playerBanks[0] && playerBanks[1]>playerBanks[2]){
            System.out.println("Player 2 Wins, Player name: "+Players[1]+" and amount: "+playerBanks[1]);
        }
        if(playerBanks[2]>playerBanks[1] && playerBanks[2]>playerBanks[0]){
            System.out.println("Player 3 Wins, Player name: "+Players[2]+" and amount: "+playerBanks[2]);
        }


    }
}
