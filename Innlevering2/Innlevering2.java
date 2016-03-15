package Innlevering2;
import java.io.*;

public class Innlevering2{

    private static BufferedReader cin = null;
    private static String output = null;

    private static int welcomeText() throws IOException{
        int ret = -2;
        try {
            output = "Welcome to your workout diary!\n" +
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
                if(inp.equals("quit")){
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

    /*Klar til å legges til query, har en query string array av lengde 11 bestående av føgende
    dd,mm,yyyy,hh,mm,varighetHH,varighetMM,type,vurdering,hvilepuls,vaer,tempertur */
    private static boolean userStory1(){
        System.out.print("1 - Register a new workout and how it went\n");
        //tar inn input til å lage query
        System.out.print("Type in date and time(dd/mm/yyyy,hh:mm):\n");
        try{
            String[] query = new String[12]; //dd,mm,yyyy,hh,mm,varighetHH,varighetMM,type,vurdering,hvilepuls,vaer,tempertur
            String tempInp = cin.readLine();
            if((tempInp).equals("quit"))
                return false;
            query[0] = tempInp.split(",")[0].split("/")[0];
            query[1] = tempInp.split(",")[0].split("/")[1];
            query[2] = tempInp.split(",")[0].split("/")[2];
            query[3] = tempInp.split(",")[1].split(":")[0];
            query[4] = tempInp.split(",")[1].split(":")[1];
            System.out.print("Type in the length of the workout(hh:mm):\n");
            tempInp = cin.readLine();
            if(tempInp.equals("quit"))
                return false;
            query[5] = tempInp.split(":")[0];
            query[6] = tempInp.split(":")[1];
            System.out.print("Type in type of workout:\n");
            tempInp = cin.readLine();
            if(tempInp.equals("quit"))
                return false;
            query[7] = tempInp;
            System.out.print("Type in evaluation of workout(on a line):\n");
            tempInp = cin.readLine();
            if(tempInp.equals("quit"))
                return false;
            query[8] = tempInp;
            System.out.print("Type in daily resting pulse:\n");
            tempInp = cin.readLine();
            if(tempInp.equals("quit"))
                return false;
            query[9] = tempInp;
            System.out.print("Type in type of weather:\n");
            tempInp = cin.readLine();
            if(tempInp.equals("quit"))
                return false;
            query[10] = tempInp;
            System.out.print("Type in temp of workout:\n");
            tempInp = cin.readLine();
            if(tempInp.equals("quit"))
                return false;
            query[11] = tempInp;

            /* INSERT query into the databse */
            for(String inp: query){
                System.out.print(inp+" ");
            }

        } catch(IOException e){
            System.out.print(e);
        }

        return true;
    }

    private static boolean userStory2(){
        output = "2 - Register a new workout goal, view old goals or view known exercises\n";
        System.out.print(output);
        System.out.println("1 - register new workout goal\n" +
                "2 - View old goals\n" +
                "3 - View known exercises\n");
        try {
            String inp = cin.readLine();

        } catch(IOException e){
            System.out.print(e);
        }
        return true;

    }

    private static boolean userStory3(){
        output = "3 - View progression for a specific exercise over a preiod and current and old goals";
        System.out.print(output);
        return true;
    }

    private static boolean userStory4(){
        output = "4 - Check the difference between a specific result and the best result reached during a" +
            "given period and the difference between the result and the current goal";
        System.out.print(output);
        return true;
    }

    private static boolean userStory5(){
        output = "5 - Copy a specific workout as a template";
        System.out.print(output);
        return true;
    }

    private static boolean userStory6(){
        output = "6 - Check the correlation between results and daily form";
        System.out.print(output);
        return true;
    }

    private static boolean userStory7(){
        output = "7 - Print all workoutnotes in a log";
        System.out.print(output);
        return true;
    }

    private static boolean userStory8(){
        output = "8 - Add, reorganize and delete exercises, groups and subgroups";
        System.out.print(output);
        return true;
    }

    public static void main(String[] args){
        cin = new BufferedReader(new InputStreamReader(System.in));
        try {
            int temp = welcomeText();
            System.out.print(temp);
            while(true) {
                switch (temp) {
                    case 1:
                        if(!userStory1()){
                            return;
                        }
                        break;
                    case 2:
                        if(!userStory2()){
                            return;
                        }
                        break;
                    case 3:
                        if(!userStory3()){
                            return;
                        }
                        break;
                    case 4:
                        if(!userStory4()){
                            return;
                        }
                        break;
                    case 5:
                        if(!userStory5()){
                            return;
                        }
                        break;
                    case 6:
                        if(!userStory6()){
                            return;
                        }
                        break;
                    case 7:
                        if(!userStory7()){
                            return;
                        }
                        break;
                    case 8:
                        if(!userStory8()){
                            return;
                        }
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