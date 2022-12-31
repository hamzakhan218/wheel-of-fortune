import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors

public class WheelOfFortune{
    
    public static void main(String[] args) {
        String[] Wheel={"TURN","800","350","450","700","300","600","5000","300","600","300","500","800","550","400","300","900","500","SPIN","900","BANKRUPT","600","400","300"};
        Scanner scr= new Scanner(System.in);
        String names[] =new String[3];
        System.out.println("Enter player 1 name: ");
        names[0]=scr.nextLine();
        System.out.println("Enter player 2 name: ");
        names[1]=scr.nextLine();
        System.out.println("Enter player 3 name: ");
        names[2]=scr.nextLine();
        int bank[]=new int[3];
        bank[0]=0;
        bank[1]=0;
        bank[2]=0;
        String data="";
        System.out.print("Enter filename: ");
        String filename= scr.nextLine();
        try {
            File myObj = new File(filename);
            // File myObj = new File("text.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              data = myReader.nextLine();
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
          }
        for(int i=0;i<3;i++)
        {
            boolean token=true;
            int spin_amount=0;
            String guesses= " ";
            while(token){
                
                
                System.out.println("\nPlayer Number: "+(i+1)+"\t"+"Player Name: "+names[i]+"\t"+"Players bank: "+bank[i]);
                int total=bank[0]+bank[1]+bank[2];
                System.out.println("Grand Total bank: "+Integer.toString(total)+"\n\n");
                System.out.println("1-Spin the wheel");
                System.out.println("2-Guess a letter");
                System.out.println("3-Buy a vowel");
                System.out.println("4-Solve the puzzle");
                System.out.println("5-Next round");
                int choice=0;
                choice=scr.nextInt();
                switch(choice){
                    case 1:{
                        int random_int = (int)Math.floor(Math.random()*(23-0+1)+0);
                        if(Wheel[random_int]!="SPIN" && Wheel[random_int]!="TURN" && Wheel[random_int]!="BANKRUPT"){
                            System.out.println("\nYou got: $"+Wheel[random_int]);
                            spin_amount=Integer.parseInt(Wheel[random_int]);
                        }
                        else{
                            if(Wheel[random_int]=="BANKRUPT"){
                                System.out.println("\nYou are Bankrupt!!\n");
                                bank[i]=0;
                                token=false;
                                break;
                            }
                            else if(Wheel[random_int]=="TURN"){
                                System.out.println("\n\nYou have Lose your turn!!!\n\n");
                                token=false;
                            }
                        }
                        break;
                    }
                    case 2:{
                        System.out.println("Enter your letter: ");
                        Scanner obj=new Scanner(System.in);
                        String letter =obj.nextLine();
                        guesses +=letter;
                        System.out.println(data);
                        int count=0;
                        for (char a : data.toCharArray()){
                            if (guesses.indexOf(a)== -1){
                                System.out.print('*');
                            }
                            else{
                                if(a!=' '){
                                    count+=1;
                                }
                                System.out.print(a);
                            }
                        }
                        bank[i]+=count*spin_amount;
                        break;
                    }
                    case 3:{
                        if(bank[i]>=100){
                            System.out.println("Please enter a vowel to borrow: ");
                            Scanner obj=new Scanner(System.in);
                            String vowel= obj.nextLine();
                            if(vowel=="a" || vowel=="e" || vowel=="i" || vowel=="o" || vowel=="u" )
                            {
                                guesses+=vowel;
                                for (char a : data.toCharArray()){
                                    if (guesses.indexOf(a)== -1){
                                        System.out.print('*');
                                    }
                                    else{
                                        System.out.print(a);
                                    }
                                }
                                bank[i]-=100;
                            }
                            else
                            {
                                System.out.println("\nNot a vowel...!");
                                token=false;
                            }
                        }
                        else{
                            System.out.println("\n\nCannot borrow vowel, you have insufficient money!!...");;
                        }
                        break;
                    }
                    case 4:{
                        int count=0;
                        for (char a : data.toCharArray()){
                            if (guesses.indexOf(a)== -1){
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
                            guesses+=temp;
                            for (char a : data.toCharArray()){
                                if (guesses.indexOf(a)== -1){
                                    System.out.print('*');
                                }
                                else{
                                    tempCount+=1;
                                    System.out.print(a);
                                }
                            }
                            bank[i]+=(tempCount-count)*spin_amount;
                        }
                        token=false;
                        break;
                    }
                    case 5:{
                        token=false;
                        break;
                    }
                }
            }
            
        }        
        if(bank[0]>bank[1] && bank[0]>bank[2]){
            System.out.println("Player 1 Wins, Player name: "+names[0]+" and amount: "+bank[0]);
        }
        else if(bank[1]>bank[0] && bank[1]>bank[2]){
            System.out.println("Player 2 Wins, Player name: "+names[1]+" and amount: "+bank[1]);
        }
        if(bank[2]>bank[1] && bank[2]>bank[0]){
            System.out.println("Player 3 Wins, Player name: "+names[2]+" and amount: "+bank[2]);
        }
    }
};

