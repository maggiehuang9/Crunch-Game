

/**
 * Crunch Game!
 * 
 * @author Julia Gonik and Maggie Huang
 * @version 11/29/2017
 */
import java.util.*;
public class Crunch
{
    static int input;
    static int sticksUser;
    static int sticksChosen;
    static int sticksComputer;
    static boolean gameDone;
    
    public static void playCrunch()
    {
        Scanner scan = new Scanner(System.in);
        Random generator = new Random();
        String answer = "";
        do
        {
            sticksChosen = 0;
            boolean computerWin = false; 
            gameDone = false;
            System.out.println("Welcome to the Game of Crunch!");
            do
            {
                System.out.print("Do you want to go first or second? Enter 1 or 2: ");
                input = scan.nextInt();
                scan.nextLine();
            }while(input != 1 && input != 2);
            if (input == 2)
            {
                sticksComputer = 2;
                System.out.println("Computer chooses " + sticksComputer + " stick(s).");
                sticksChosen = 2;
                printSticks(21 - sticksChosen);
                answer = goWin(scan);
            }
            else 
            {
                input = chooseSticks(scan);
                System.out.print("\f");
                if (input == 1)
                {
                    sticksUser = 1;
                    sticksChosen++;
                    printSticks(21 - sticksChosen);
                    sticksComputer = 1;
                    System.out.println("Computer chooses " + sticksComputer + " stick(s).");
                    sticksChosen ++;
                    printSticks(21 - sticksChosen);
                    answer = goWin(scan);
                }
                else
                {
                    sticksUser = 2;
                    sticksChosen += 2;
                    printSticks(21 - sticksChosen);
                    sticksComputer = generator.nextInt(2) + 1;
                    System.out.println("Computer chooses " + sticksComputer + " stick(s).");
                    sticksChosen += sticksComputer;
                    printSticks (21 - sticksChosen);
                    input = chooseSticks(scan);
                    System.out.print("\f");
                    sticksUser = input;
                    sticksChosen += input;
                    printSticks(21 - sticksChosen);
                }
                while (sticksChosen < 20)
                {
                    if (sticksChosen == 3 || sticksChosen == 6 || sticksChosen == 9 || sticksChosen == 12 || sticksChosen == 15 || sticksChosen == 18)
                    {
                        sticksComputer = 2;
                        System.out.println("Computer chooses " + sticksComputer + " stick(s).");
                        sticksChosen += 2;
                        printSticks(21 - sticksChosen);
                        computerWin = true;
                    }

                    else if (sticksChosen == 4 || sticksChosen == 7 || sticksChosen == 10 || sticksChosen == 13 || sticksChosen == 16 || sticksChosen == 19)
                    {
                        sticksComputer = 1;
                        System.out.println("Computer chooses " + sticksComputer + " stick(s).");
                        sticksChosen++;
                        printSticks(21 - sticksChosen);
                        computerWin = true;
                    }
                    else if ((sticksChosen == 2 || sticksChosen == 5 || sticksChosen == 8 || sticksChosen == 11 || sticksChosen == 14 || sticksChosen == 17) && computerWin == true)
                    {
                        sticksComputer = 1;
                    }
                    else
                    {
                        sticksComputer = generator.nextInt(2) + 1;
                        System.out.println("Computer chooses " + sticksComputer + " stick(s).");
                        sticksChosen += sticksComputer;
                        printSticks (21 - sticksChosen);
                        if (sticksChosen == 20)
                        {
                            computerWin = true;
                            break;
                        }
                        computerWin = false;
                    }
                    if (sticksChosen != 20)
                    {
                        input = chooseSticks(scan);
                        System.out.print("\f");
                        if (input == 2 && computerWin == true)
                        {
                            sticksUser = 2;
                            sticksChosen += 2;
                            printSticks(21 - sticksChosen);
                            sticksComputer = 1;
                            System.out.println("Computer chooses " + sticksComputer + " stick(s).");
                            sticksChosen++;
                            printSticks(21 - sticksChosen);
                        }
                        else if (input == 1 && computerWin == true)
                        {
                            sticksUser = 1;
                            sticksChosen++;
                            printSticks (21 - sticksChosen);
                            sticksComputer = 2;
                            System.out.println("Computer chooses " + sticksComputer + " stick(s).");
                            sticksChosen += 2;
                            printSticks(21 - sticksChosen);
                        }
                        else
                        {
                            sticksUser = input;
                            sticksChosen += input;
                            printSticks (21 - sticksChosen);
                        }
                    }
                }

                if (computerWin == true && gameDone == false)
                {
                    do 
                    {
                        System.out.print("How many sticks do you want to choose? Enter 1 or 2: ");
                        input = scan.nextInt();
                        scan.nextLine();
                    }while(input != 1);
                    System.out.print("\f");
                    sticksUser = 1;
                    sticksChosen++;
                    printSticks(21 - sticksChosen);
                    gameDone = true;
                    System.out.println("You lose!");
                    answer = playAgain(scan);
                    System.out.print("\f");
                }
                else if (sticksChosen == 21 && gameDone == false)
                {
                    gameDone = true;
                    System.out.println("You lose!");
                }
                else
                {
                    if (gameDone == false)
                    {
                        sticksComputer = 1;
                        System.out.println("Computer chooses " + sticksComputer + " stick(s).");
                        sticksChosen++;
                        printSticks(21 - sticksChosen);
                        gameDone = true;
                        System.out.println("You win!");
                        answer = playAgain(scan);
                        System.out.print("\f");
                    }
                }
            }
        }while(answer.equalsIgnoreCase("y"));
        System.out.println("Thanks for playing! :)");
    }

    public static void printSticks(int num)
    {
        System.out.println("Sticks remaining: " + num);
        for(int count = 1; count <= num; count++)
        {
            System.out.print("| ");
        }
        System.out.println();
    }

    public static int chooseSticks(Scanner in)
    {
        int input;
        do
        {
            System.out.print("How many sticks do you want to choose? Enter 1 or 2: ");
            input = in.nextInt();
            in.nextLine();
        }while(input != 1 && input != 2);
        return input;
    }

    public static String playAgain(Scanner in)
    {
        String answer;
        do
        {
            System.out.print("Do you want to play again? (y/n): ");
            answer = in.nextLine();
        }while(!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n"));
        return answer;
    }

    public static String goWin(Scanner in)
    {
        String ans;
        while (sticksChosen < 20)
        {
            
            input = chooseSticks(in);
            System.out.print("\f");

            if (input == 2)
            {
                sticksUser = 2;
                sticksChosen += 2;
                printSticks(21 - sticksChosen);
                sticksComputer = 1;
                System.out.println("Computer chooses " + sticksComputer + " stick(s).");
                sticksChosen++;
                printSticks(21 - sticksChosen);
            }
            else
            {
                sticksUser = 1;
                sticksChosen++;
                printSticks(21 - sticksChosen);
                sticksComputer = 2;
                System.out.println("Computer chooses " + sticksComputer + " stick(s).");
                sticksChosen += 2;
                printSticks(21 - sticksChosen);
            }
        }
        do 
        {
            System.out.print("How many sticks do you want to choose? Enter 1 or 2: ");
            input = in.nextInt();
            in.nextLine();
        }while(input != 1);
        System.out.print("\f");
        sticksUser = 1;
        sticksChosen++;
        printSticks(21 - sticksChosen);
        gameDone = true;
        System.out.println("You lose!");
        ans = playAgain(in);
        System.out.print("\f");
        return ans;
    }
}

