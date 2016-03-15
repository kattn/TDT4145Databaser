package Innlevering2;
import java.io.*;

public class Innlevering2{

    private static BufferedReader cin = null;
    private static String output = null;

    private static boolean quitRepeatCheck(String inp, String output){
        switch (inp){
            case "repeat":
                System.out.print(output);
                break;
            case "quit":
                return true;
            default:
                break;
        }
        return false;
    }

    private static int welcomeText() throws IOException{
        int ret = -2;
        try {
            output = "Welcome to your workout diary!\n" +
                    "If at anytime you want to repeat the last message type repeat.\n" +
                    "If you want to quit at anytime type quit.\n" +
                    "Type in one of the following numbers to use the program, they are" +
                    "meant to mirror the use cases presented in the assignment:\n" +
                    "1 - Register a new workout and how it went\n" +
                    "2 - Register a new workout goal, view old goals or view known exercises\n" +
                    "3 - View progression for a specific exercise over a period and current and old goals\n" +
                    "4 - Check the difference between a specific result and the best result reached during a" +
                        "given period and the difference between the result and the current goal\n" +
                    "5 - Copy a specific workout as a template\n" +
                    "6 - Check the correlation between results and daily form\n" +
                    "7 - Print all workout notes in a log\n" +
                    "8 - Add, reorganize and delete exercises, groups and subgroups\n";
            String inp;
            boolean run = true;
            System.out.print(output);
            while(run){
                inp = cin.readLine();
                if(quitRepeatCheck(inp,output)){
                    ret = -1;
                }
                if( 0< Integer.valueOf(inp) && Integer.valueOf(inp) <9){
                    ret = Integer.valueOf(inp);
                    run = false;
                } else {
                    System.out.print("Need a number between 1 and 8, quit or repeat\n");
                }
            }
        } finally {
            return ret;
        }
    }

    private static void userStory1(){
        output = "1 - Register a new workout and how it went";
        System.out.print(output);
        //tar inn input til å lage query
    }

    private static void userStory2(){
        output = "2 - Register a new workoutgoal, view old goals or view known exercises";
        System.out.print(output);
    }

    private static void userStory3(){
        output = "3 - View progression for a specific exercise over a preiod and current and old goals";
        System.out.print(output);
    }

    private static void userStory4(){
        output = "4 - Check the difference between a specific result and the best result reached during a" +
            "given period and the difference between the result and the current goal";
        System.out.print(output);
    }

    private static void userStory5(){
        output = "5 - Copy a specific workout as a template";
        System.out.print(output);
    }

    private static void userStory6(){
        output = "6 - Check the correlation between results and daily form";
        System.out.print(output);
    }

    private static void userStory7(){
        output = "7 - Print all workoutnotes in a log";
        System.out.print(output);
    }

    private static void userStory8(){
        output = "8 - Add, reorganize and delete exercises, groups and subgroups";
        System.out.print(output);
    }

    public static void main(String[] args){
        cin = new BufferedReader(new InputStreamReader(System.in));
        try {
            int temp = welcomeText();
            while(true) {
                switch (temp) {
                    case 1:
                        userStory1();
                        break;
                    case 2:
                        userStory2();
                        break;
                    case 3:
                        userStory3();
                        break;
                    case 4:
                        userStory4();
                        break;
                    case 5:
                        userStory5();
                        break;
                    case 6:
                        userStory6();
                        break;
                    case 7:
                        userStory7();
                        break;
                    case 8:
                        userStory8();
                        break;
                    case -1:
                        cin.close();
                        return;
                }
                temp = welcomeText();
            }
        } catch (IOException e){
            System.out.print("Catched a IOException from the userCasePicker");
        }
    }
}