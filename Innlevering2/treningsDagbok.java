package treningsDagbok;
import db_javaimplementation;
import java.io.*;
import java.System.*;

public class treningsDagbok{

    private InputStreamReader cin = null;
    private String output = null;

    private int welcomeText() throws IOException{
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
            System.out.print(output)
            while(run){
                inp = (String) cin.read();
                switch (inp){ // trenger ikke switch, men skrev den før e tenkte...
                    case "repeat":
                        System.out.print(output);
                        break;
                    case "quit":
                        return -1;
                    case default:
                        break;
                }
                if( 0<(int) inp && (int) inp<9){
                    return (int) inp;
                } else {
                    System.out.print("Need a number between 1 and 8, quit or repeat");
                }
            }
        } finally {
            if (cin != null){
                cin.close();
            }
        }
    }

    private void userStory1(){
        System.out.print("userStory1");
    }

    private void userStory2(){
        System.out.print("userStory2");
    }

    private void userStory3(){
        System.out.print("userStory3");
    }

    private void userStory4(){
        System.out.print("userStory4");
    }

    private void userStory5(){
        System.out.print("userStory5");
    }

    private void userStory6(){
        System.out.print("userStory6");
    }

    private void userStory7(){
        System.out.print("userStory7");
    }

    private void userStory8(){
        System.out.print("userStory8");
    }

    public void main(Strings[] args){
        cin = new InputStreamReader(System.in);
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
    }
}