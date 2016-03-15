package Innlevering2;
import java.io.*;

public class Innlevering2{

    private static BufferedReader cin = null;
    private static String output = null;

    private static int welcomeText() throws IOException{
        int ret = -2;
        try {
            output = "Welcome to your workoutdiary!\n" +
                    "If at anytime you want to repeat the last message type repeat.\n" +
                    "If you want to quit at anytime type quit.\n" +
                    "Type in one of the following numbers to use the program, they are" +
                    "meant to mirror the use cases presented in the assignment:\n" +
                    "1 - Register a new workout and how it went\n" +
                    "2 - Register a new workoutgoal, view old goals or view known exercises\n" +
                    "3 - View progression for a specific exercise over a preiod and current and old goals\n" +
                    "4 - Check the difference between a specific result and the best result reached during a" +
                        "given period and the difference between the result and the current goal\n" +
                    "5 - Copy a specific workout as a template\n" +
                    "6 - Check the correlation between results and daily form\n" +
                    "7 - Print all workoutnotes in a log\n" +
                    "8 - Add, reorganize and delete exercises, groups and subgroups\n";
            String inp;
            boolean run = true;
            System.out.print(output);
            while(run){
                inp = cin.readLine();
                switch (inp){
                    case "repeat":
                        System.out.print(output);
                        break;
                    case "quit":
                        ret = -1;
                    default:
                        break;
                }
                if( 0< Integer.valueOf(inp) && Integer.valueOf(inp) <9){
                    ret = Integer.valueOf(inp);
                } else {
                    System.out.print("Need a number between 1 and 8, quit or repeat");
                }
            }
        } finally {
            if (cin != null){
                cin.close();
            }
            return ret;
        }
    }

    private static void userStory1(){
        System.out.print("userStory1");
    }

    private static void userStory2(){
        System.out.print("userStory2");
    }

    private static void userStory3(){
        System.out.print("userStory3");
    }

    private static void userStory4(){
        System.out.print("userStory4");
    }

    private static void userStory5(){
        System.out.print("userStory5");
    }

    private static void userStory6(){
        System.out.print("userStory6");
    }

    private static void userStory7(){
        System.out.print("userStory7");
    }

    private static void userStory8(){
        System.out.print("userStory8");
    }

    public static void main(String[] args){
        cin = new BufferedReader(new InputStreamReader(System.in));
        try {
            switch (welcomeText()){
                case 1: userStory1();
                    break;
                case 2: userStory2();
                    break;
                case 3: userStory3();
                    break;
                case 4: userStory4();
                    break;
                case 5: userStory5();
                    break;
                case 6: userStory6();
                    break;
                case 7: userStory7();
                    break;
                case 8: userStory8();
                    break;
                case -1: return;
            }
        } catch (IOException e){
            System.out.print("Catched a IOException from the userCasePicker");
        }
    }
}