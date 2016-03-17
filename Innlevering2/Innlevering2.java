package Innlevering2;
import java.io.*;
import java.util.ArrayList;
import java.sql.*;
import java.lang.math;
import java.

//nr til Bjerke 95998212

public class Innlevering2{

    private static BufferedReader cin = null;
    private static String output = null;
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/STUDENTS";
    static final String username = "username";
    static final String password = "password";

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
    private static boolean userStory1() {
        System.out.print("1 - Register a new workout and how it went\n");
        //tar inn input til å lage query
        System.out.print("Type in date and time(dd/mm/yyyy,hh:mm):\n");
        String[] query = new String[12]; //dd,mm,yyyy,hh,mm,varighetHH,varighetMM,type,vurdering,hvilepuls,vaer,tempertur
        ArrayList<String[]> ovelser;
        try {
            String tempInp = cin.readLine();
            if ((tempInp).equals("quit"))
                return false;
            query[0] = tempInp.split(",")[0].split("/")[0];
            query[1] = tempInp.split(",")[0].split("/")[1];
            query[2] = tempInp.split(",")[0].split("/")[2];
            query[3] = tempInp.split(",")[1].split(":")[0];
            query[4] = tempInp.split(",")[1].split(":")[1];
            System.out.print("Type in the length of the workout(hh:mm):\n");
            tempInp = cin.readLine();
            if (tempInp.equals("quit"))
                return false;
            query[5] = tempInp.split(":")[0];
            query[6] = tempInp.split(":")[1];
            System.out.print("Type in type of workout:\n");
            tempInp = cin.readLine();
            if (tempInp.equals("quit"))
                return false;
            query[7] = tempInp;
            System.out.print("Type in evaluation of workout(on a line):\n");
            tempInp = cin.readLine();
            if (tempInp.equals("quit"))
                return false;
            query[8] = tempInp;
            System.out.print("Type in daily resting pulse:\n");
            tempInp = cin.readLine();
            if (tempInp.equals("quit"))
                return false;
            query[9] = tempInp;
            System.out.print("Type in type of weather:\n");
            tempInp = cin.readLine();
            if (tempInp.equals("quit"))
                return false;
            query[10] = tempInp;
            System.out.print("Type in temp of workout:\n");
            tempInp = cin.readLine();
            if (tempInp.equals("quit"))
                return false;
            query[11] = tempInp;

            /* INSERT query into the databse */
            for (String inp : query) {
                System.out.print(inp + " ");
            }

            ovelser = setOvelser();
            //Ovlerser er en arraylist med String[12] av typen,
            // name, dd,mm,yyyy,beskrivelse, lengde, intensitet, marsj(bool med 0 som false of 1 som true), repetisjoner, sett, belastning, prestasjon


        } catch (IOException e) {
            System.out.print(e);
        }
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, username, password);

            statement = connection.createStatement();
            String sql = "INSERT INTO Okt(dato, tidspunkt, varighet, type, vurdering)"
                    + "VALUES ("
                    + query[2] + "-" + query[1] + "-" + query[0] + ", "
                    + query[3] + ":" + query[5] + ":00, "
                    + query[6] + ":" + query[7] + ", "
                    + query[8] + ")";

            String sql2 = "INSERT INTO Dagsform"
                    + "VALUES ("
                    + query[2] + "-" + query[1] + "-" + query[0] + ", "
                    +

                    statement.executeQuery(sql);

            //Itererer gjennom alle øvelser som er utført, og legger dem til i databasen
            for (int i = 0; i < ovelser.size(); i++) {
                String sql3 = "INSERT INTO Ovelse(dato, beskrivelse, intensitet, maalid, lengde, marsj, repetisjoner, sett, belastning)"
                        + "VALUES ("
                        + ovelser.get(i)[3] + "-" + ovelser.get(i)[2] + "-" + ovelser.get(i)[1] + ","
                        + ovelser.get(i)[4] + ","
                        + ovelser.get(i)[5] + ","
                        + ovelser.get(i)[6] + ","
                        + ovelser.get(i)[7] + ","
                        + ovelser.get(i)[8] + ","
                        + ovelser.get(i)[9] + ","
                        + ovelser.get(i)[10] + ","
                        + ovelser.get(i)[11] + ",";
            }
            //System.out.println("Insert into " + tableName + " complete. ");


            return true;
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null)
                    connection.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
            return true;
        }
    }

    private static ArrayList<String[]> setOvelser(){
        ArrayList<String[]> strings = new ArrayList<String[]>();
        System.out.println("Add an exercise(y), type q when done.");
        try {
            String inp = cin.readLine();
            String[] q;
            while(!inp.equals("q")) {
                q = new String[12];
                System.out.println("Name:");
                q[0] = cin.readLine();
                System.out.println("Date(dd/mm/yyyy):");
                inp = cin.readLine();
                q[1] = inp.split("/")[0];
                q[2] = inp.split("/")[1];
                q[3] = inp.split("/")[2];
                System.out.println("Description(on a single line):");
                q[4] = cin.readLine();
                System.out.println("Length(0 if not needed): ");
                q[5] = cin.readLine();
                System.out.println("Intensity(0 if not needed): ");
                q[6] = cin.readLine();
                System.out.println("March(0 if not needed, 1 if it was): "); //er vel boolean i db, så 0 = false
                q[7] = cin.readLine();
                System.out.println("Number of repetitions(0 if not needed): ");
                q[8] = cin.readLine();
                System.out.println("Number of sets(0 if not needed): ");
                q[9] = cin.readLine();
                System.out.println("Weight(0 if not needed): ");
                q[10] = cin.readLine();
                System.out.println("Performance: ");
                q[11] = cin.readLine();

                strings.add(q);
                System.out.println("Done adding exercises? q for yes, anything else for no");
                inp = cin.readLine();
            }
            return strings;
        } catch (IOException e){
            System.out.print(e);
        }
        return strings;
    }

    private static boolean userStory2() {
        output = "2 - Register a new workout goal, view old goals or view known exercises\n";
        System.out.print(output);
        System.out.println("1 - Register new workout goal\n" +
                "2 - View old goals\n" +
                "3 - View known exercises\n");
        try {
            String inp = cin.readLine();
            switch (inp) {
                case "quit":
                    return false;
                case "1":
                    String[] query = new String[9];
                    System.out.println("Date(dd/mm/yyyy:");
                    inp = cin.readLine();
                    if (inp.equals("quit"))
                        return false;
                    query[0] = inp.split("/")[0];
                    query[1] = inp.split("/")[1];
                    query[2] = inp.split("/")[2];
                    System.out.println("Name of exercise:");
                    inp = cin.readLine();
                    if (inp.equals("quit"))
                        return false;
                    query[3] = inp;
                    System.out.println("Goal length(0 if not needed):");
                    inp = cin.readLine();
                    if (inp.equals("quit"))
                        return false;
                    query[4] = inp;
                    System.out.println("Goal duration(0 if not needed):");
                    inp = cin.readLine();
                    if (inp.equals("quit"))
                        return false;
                    query[5] = inp;
                    System.out.println("Number repetitions(0 if not needed):");
                    inp = cin.readLine();
                    if (inp.equals("quit"))
                        return false;
                    query[6] = inp;
                    System.out.println("Number of sets(0 if not needed):");
                    inp = cin.readLine();
                    if (inp.equals("quit"))
                        return false;
                    query[7] = inp;
                    System.out.println("Goal weight(0 if not needed):");
                    inp = cin.readLine();
                    if (inp.equals("quit"))
                        return false;
                    query[8] = inp;

                    /*
                    INSERT INTO MAAL
                    query: dd,mm,yyyy,navn,lengde,varighet,repitisjon,sett,belastning
                    */
                    Connection connection = null;
                    Statement statement = null;

                    try {
                        Class.forName(JDBC_DRIVER);

                        connection = DriverManager.getConnection(DB_URL, username, password);

                        statement = connection.createStatement();
                        String sql = "INSERT INTO Maal "
                                + "VALUES ("
                                + query[2] + "-" + query[1] + "-" + query[0] + ", "
                                + query[3] + ", "
                                + query[4] + ", "
                                + query[5] + ", "
                                + query[6] + ", "
                                + query[7] + ", "
                                + query[8] + ", "
                                + ");";

                        int progression = Integer.valueOf(statement.executeQuery(sql2)) - Integer.valueOf(statement.executeQuery(sql).toString());
                        System.out.println("Progression in given period:" + progression);
                        return true;
                    } catch (SQLException se) {
                        se.printStackTrace();
                        return false;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    } finally {
                        try {
                            if (statement != null)
                                connection.close();
                        } catch (SQLException se) {
                        }// do nothing
                        try {
                            if (connection != null)
                                connection.close();
                        } catch (SQLException se) {
                            se.printStackTrace();
                        }//end finally try
                    }

                case "2":
                    System.out.println("Wanted exercise:");
                    inp = cin.readLine();
                    if (inp.equals("quit"))
                        return false;

                    connection = null;
                    statement = null;

                    /*
                    MANGLER Å BRUKE inp FOR Å VELGE RIKTIG OVELSE
                     */

                    try {
                        Class.forName(JDBC_DRIVER);

                        connection = DriverManager.getConnection(DB_URL, username, password);

                        statement = connection.createStatement();
                        String sql = "SELECT * FROM Maal;";

                        System.out.println("Old goals" + executeQuery(sql));
                        return true;
                    } catch (SQLException se) {
                        se.printStackTrace();
                        return false;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    } finally {
                        try {
                            if (statement != null)
                                connection.close();
                        } catch (SQLException se) {
                        }// do nothing
                        try {
                            if (connection != null)
                                connection.close();
                        } catch (SQLException se) {
                            se.printStackTrace();
                        }//end finally try
                    }

                    break;
                case "3":
                    connection = null;
                    statement = null;

                    try {
                        Class.forName(JDBC_DRIVER);

                        connection = DriverManager.getConnection(DB_URL, username, password);

                        /*
                        BURDE VEL HA UNIKE OVELSER?
                         */
                        statement = connection.createStatement();
                        String sql = "SELECT * FROM Ovelse;";

                        System.out.println("Known exercises" + executeQuery(sql));
                        return true;
                    } catch (SQLException se) {
                        se.printStackTrace();
                        return false;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    } finally {
                        try {
                            if (statement != null)
                                connection.close();
                        } catch (SQLException se) {
                        }// do nothing
                        try {
                            if (connection != null)
                                connection.close();
                        } catch (SQLException se) {
                            se.printStackTrace();
                        }//end finally try
                    }
                    break;
            }
        } catch (IOException e) {
            System.out.print(e);
        }
        return true;
    }

    private static boolean userStory3(){
        output = "3 - View progression for a specific exercise over a preiod and current and old goals";
        System.out.print(output);
        /* Trenger følgende input:
            - startDate = dato for begynnelse av perioden
            - endDate = dato for slutt av perioden
            Tar bare her differansen mellom begynnelse og slutt fordi det er representativt for hele perioden.
            - type = hvilke attribut man vil sjekke (f.eks. belastning hvis det er vekter)

            TAR INN DATO SOM startDate = "dd/mm/yyyy"
         */
        String startDate = "";
        String endDate = "";
        String type = "";
        try {
            System.out.println("Start date(yyyy-mm-dd): ");
            startDate = cin.readLine();
            System.out.println("End date(yyyy-mm-dd): ");
            endDate = cin.readLine();
            System.out.println("Type(styrke1/styrke2/styrke3/utholdenhet/svomming):"); // folge forsvaret sin saann greie
            type = cin.readLine();
        } catch (IOException e) {
            System.out.println(e);
        }
        Connection  connection = null;
        Statement statement = null;

        try{
        Class.forName(JDBC_DRIVER);

        connection = DriverManager.getConnection(DB_URL, username, password);

        statement = connection.createStatement();
        String sql = "SELECT " + type + " FROM Ovelse WHERE Ovelse.dato=" + startDate + ";";

        String sql2 = "SELECT " + type + " FROM Ovelse WHERE Ovelse.dato=" + endDate + ";";

        int progression = Integer.valueOf(statement.executeQuery(sql2).toString()) - Integer.valueOf(statement.executeQuery(sql).toString());
        System.out.println("Progression in given period:" + progression);
        return true;
        } catch(SQLException se){
        se.printStackTrace();
        return false;
        } catch(Exception e){
        e.printStackTrace();
        return false;
        } finally {
            try {
                if (statement != null)
                    connection.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
            return true;
        }
    }

    private static boolean userStory4() {
        output = "4 - Check the difference between a specific result and the best result reached during a" +
                "given period and the difference between the result and the current goal";
        System.out.print(output);
        /*Trenger følgende:
            - pastDate = dato som definerer tid tilbake, må sjekke at den enten er 1 uke, 1 eller 3 måneder
            - maalIdSpesifikk = iden for det spesifikke målet
            - aktivtMaal = aktivt maalId for perioden
            - type = type verdi man sjekker for målet
         */
        System.out.println("Past date(yyyy-mm-dd): ");
        String pastDate = "";
        String maalIdSpesifikk = "";
        String aktivtMaal = "";
        String type = "";
        try {
            pastDate = cin.readLine();
            System.out.println("Goal id: ");
            maalIdSpesifikk = cin.readLine();
            System.out.println("Active goal id: ");
            aktivtMaal = cin.readLine();
            System.out.println("Type to check(lengde/varighet/repetisjoner/sett/belastning):");
            type = cin.readLine();

        } catch (IOException e) {
            System.out.println(e);
        }

        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, username, password);

            statement = connection.createStatement();
            String sql = "SELECT " + type + "FROM Maal WHERE Maal.maalId=" + maalIdSpesifikk + ";";

            String sql2 = "SELECT MAX(" + type + "FROM Okt WHERE Okt.dato>=" + pastDate + ";";

            String current = "SELECT " + type + "FROM Maal WHERE Maal.maalId=" + aktivtMaal + ";";

            //differansen mellom spesifikk mål og best i perioden
            int differanseSpesifikk = Integer.valueOf(statement.executeQuery(sql).toString()) - Integer.valueOf(statement.executeQuery(sql2).toString());
            System.out.println("Differnse mellom spesifikt mål og beste = " + differanseSpesifikk);

            int differanseAktiv = Integer.valueOf(statement.executeQuery(sql2).toString()) - Integer.valueOf(statement.executeQuery(sql3).toString());
            System.out.println("Differanse mellom aktivt mål og best =" + differanseAktiv);

            return true;
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null)
                    connection.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
            return true;
        }
    }

    private static boolean userStory5() {
        output = "5 - Copy a specific workout as a template";
        System.out.print(output);
        /*Trenger følgende input:
            - dato = dato for treningsøkten som skal lages
            - tidspunkt  = tidspunkt for økten som skal lages
            - gittDato = dato for treningsøkt som skal kopieres
            - gittTidspunkt = tidspunkt for treningsøkt som skal kopieres
        */
        String dato = "";
        String tidspunkt = "";
        String gittDato = "";
        String gittTidspunkt = "";
        try {
            System.out.println("Date for the new workout(yyyy-mm-dd):");
            dato = cin.readLine();
            System.out.println("Time for the new workout(hh:mm):");
            tidspunkt = cin.readLine().concat(":00"); //legger til så det går rett inn i databasen
            System.out.println("Date for workout to copy(yyyy-mm-dd):");
            gittDato = cin.readLine();
            System.out.println("Time for the workout to copy(hh:mm):");
            gittTidspunkt = cin.readLine().concat(":00");
        } catch (IOException e) {
            System.out.println(e);
        }


        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, username, password);

            statement = connection.createStatement();
            String sql = "INSERT INTO Okt(" + dato + ", " + ", " + tidspunkt + ", varighet, type, vurdering) SELECT tidspunkt, varighet, type, vurdering FROM Okt WHERE Okt.dato =" + gittDato + " AND Okt.tidspunkt=" + gittTidspunkt + ";";

            statement.executeQuery(sql);
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null)
                    connection.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
            return true;
        }
    }

    private static boolean userStory6(){
        output = "6 - Check the correlation between results and daily form";
        System.out.print(output);

        //Bruker Pearson sin korrelasjons koeffisient formel for å sjekke korrelasjon mellom
        //hvilepuls i dagsform for en dato og intensitet i ovelser på en dato
        //LYKKE TIL HAJEM :D

        // Vet ikke hva du vil ha hajem :S men gir deg nå litt av hvert så kan du fjerne/hente inn mer om du trenger det
        /*
            startDate - Dato for å sjekke forholdet mellom resultat og dagsform start
            endDate - ------------------||---------------------- bare at slutten
            type - navn på ovelse man sjekker
         */
        String startDate = "";
        String endDate = "";
        String type = "";
        try {
            System.out.println("Start date of period to check(yyyy-mm-dd):");
            startDate = cin.readLine();
            System.out.println("End date of period to check(yyyy-mm-dd):");
            endDate = cin.readLine();
            System.out.println("Name of excersice to compare:");
            type = cin.readLine();
        }catch(IOException e) {
            System.out.println(e);
        }


        //resulst and daily forms from given period
        String[] resultSet;
        String[] formSet;
        //sum of sets
        int sumResult = 0;
        int sumForm = 0;
        //sum of combined set
        int sumFormResult = 0;
        //sum of set^2
        int sumResultSqr = 0;
        int sumFormSqr = 0;
        //pearson correlation coefficient
        double r;

        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, username, password);

            statement = connection.createStatement();

            String sql1 = "SELECT intensitet FROM Ovelse WHERE navn LIKE" + type;

            String sql2 = "SELECT dato FROM Ovelse WHERE navn LIKE" + type;

            String sql3 = "SELECT dagsform FROM Dagsform WHERE dato >" + type;


            //calculates the different sums
            for (int i = 0; i < resultSet.length;i++) {
                //since neither intensity or daily form can be null, the sets will always have the same size
                sumResult += resultSet[i];
                sumForm += formSet[i];

                sumFormResult += (resultSet[i]*formSet[i]);

                sumResultSqr += ((resultSet[i])^2);
                sumFormSqr += ((formSet[i])^2);
            }

            r = (sumFormResult - ((((sumResult*sumForm)/resultSet.length))/(sqrt((sumResultSqr-((sumResult^2)/resultSet.length))*(sumFormSqr-((sumForm^2)/resultSet.length))))));
        } catch (SQLException se) {
            se.printStackTrace();
        }





        /*
            GL HF HAJEM!
         */



        return r;
    }

    private static boolean userStory7(){
        output = "7 - Print all workoutnotes in a log";
        System.out.print(output);
        Connection  connection = null;
        Statement statement = null;

        try{
        Class.forName(JDBC_DRIVER);

        connection = DriverManager.getConnection(DB_URL, username, password);

        statement = connection.createStatement();
        String sql = "Select *"
                    +"FROM Okt, Dagsform, Ovelse"
                    +"WHERE Okt.dato = Dagsform.dato AND Okt.dato = Ovelse.dato"
                    +"ORDER BY Okt.dato";
        //Kan hende vi må fjerne duplicates av okt ovenfor, slik at en okt nevnes en gang med alle øvelsene :S

        statement.executeQuery(sql);
        return true;
        } catch(SQLException se){
        se.printStackTrace();
        return false;
        } catch(Exception e){
        e.printStackTrace();
        return false;
        } finally {
            try {
                if (statement != null)
                    connection.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
            return true;
        }
    }

    private static boolean userStory8() {
        output = "8 - Add, reorganize and delete exercises, groups and subgroups";
        System.out.print(output);
        /*Add an exercise
         *TRENGER FØLGENDE VALUES SOM INPUT:
         - alt for en exercise, gir deg,  q = ["navn","dd","mm","yyyy","beskrivelse","lengde","intensitet","marsj(0 er false,1 er true)","repetisjoner","sett","belastning","maalId"]
         - dato for økten den var en del av
         */
        int choice;
        String[] q = new String[13];

        try {
            System.out.println("1 - Add exercise\n" +
                    "2 - Delete single exercise\n" +
                    "3 - Delete all exercises on this date\n" +
                    "4 - Delete all exercises of this name");
            choice = Integer.valueOf(cin.readLine());
        } catch (IOException e) {
            System.out.println(e);
        }
        if (choice == 1) {
            Connection connection = null;
            Statement statement = null;

            try {
                System.out.println("Name:");
                q[0] = cin.readLine();
                System.out.println("Date(dd/mm/yyyy):");
                String inp = cin.readLine();
                q[1] = inp.split("/")[0];
                q[2] = inp.split("/")[1];
                q[3] = inp.split("/")[2];
                System.out.println("Description(on a single line):");
                q[4] = cin.readLine();
                System.out.println("Length(0 if not needed): ");
                q[5] = cin.readLine();
                System.out.println("Intensity(0 if not needed): ");
                q[6] = cin.readLine();
                System.out.println("March(0 if not needed, 1 if it was): "); //er vel boolean i db, så 0 = false
                q[7] = cin.readLine();
                System.out.println("Number of repetitions(0 if not needed): ");
                q[8] = cin.readLine();
                System.out.println("Number of sets(0 if not needed): ");
                q[9] = cin.readLine();
                System.out.println("Weight(0 if not needed): ");
                q[10] = cin.readLine();
                System.out.println("Performance: ");
                q[11] = cin.readLine();
                System.out.println("Goal id(0 if none): ");
                q[12] = cin.readLine();
            } catch (IOException e) {
                System.out.println(e);
            }
            String dato = q[3] + "-" + q[2] + "-" + q[1];
            String navn = q[0];
            String beskrivelse = q[4];
            String intensitet = q[6];
            String lengde = q[5];
            String marsj = q[7];
            String repetisjoner = q[8];
            String sett = q[9];
            String belastning = q[10];
            String prestasjon = q[11];
            String maalId = q[12];


            try {
                Class.forName(JDBC_DRIVER);

                connection = DriverManager.getConnection(DB_URL, username, password);

                statement = connection.createStatement();
                String sql = "SELECT INTO Ovelse"
                        + "VALUES("
                        + navn + "," + beskrivelse + "," + intensitet + "," + maalId + ","
                        + lengde + "," + marsj + "," + repetisjoner + "," + sett + "," + belastning
                        + ");";

                String sql2 = "INSERT INTO Utfort"
                        + "VALUES ("
                        + prestasjon
                        + "," + navn
                        + "," + dato
                        + ");";
                statement.executeQuery(sql);
                statement.executeQuery(sql2);
                return true;
            } catch (SQLException se) {
                se.printStackTrace();
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                try {
                    if (statement != null)
                        connection.close();
                } catch (SQLException se) {
                }// do nothing
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }//end finally try
            }
        } else if (choice == 2) {
            //Delete single exercise
            String navn = "";
            String dato = "";

            try {
                System.out.println("Name:");
                navn = cin.readLine();
                System.out.println("Date(yyyy-mm-dd):");
                dato = cin.readLine();
            } catch (IOException e) {
                System.out.println(e);
            }

            try {
                Class.forName(JDBC_DRIVER);

                connection = DriverManager.getConnection(DB_URL, username, password);

                statement = connection.createStatement();
                String sql = "DELETE FROM Utfort WHERE dato=" + dato + "AND navn=" + navn + ";";
                statement.executeQuery(sql);
                return true;
            } catch (SQLException se) {
                se.printStackTrace();
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                try {
                    if (statement != null)
                        connection.close();
                } catch (SQLException se) {
                }// do nothing
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }//end finally try
            }


        } else if (choice == 3) {
            //delete based on date
            //TRENGER: Datoen ovelsen ble utført

            String dato = "";

            try {
                System.out.println("Date(yyyy-mm-dd):");
                dato = cin.readLine();
            } catch (IOException e) {
                System.out.println(e);
            }
            try {
                Class.forName(JDBC_DRIVER);

                connection = DriverManager.getConnection(DB_URL, username, password);

                statement = connection.createStatement();
                String sql = "DELETE FROM Utfort WHERE dato=" dato + ";"
                statement.executeQuery(sql);
                return true;
            } catch (SQLException se) {
                se.printStackTrace();
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                try {
                    if (statement != null)
                        connection.close();
                } catch (SQLException se) {
                }// do nothing
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }//end finally try
            }


        } else if (choice == 4) {

            String navn = "";

            try {
                System.out.println("Name:");
                navn = cin.readLine();
            } catch (IOException e) {
                System.out.println(e);
            }

            //Delete øvelse based on navn
            try {
                Class.forName(JDBC_DRIVER);

                connection = DriverManager.getConnection(DB_URL, username, password);

                statement = connection.createStatement();
                String sql = "DELETE FROM Utfort WHERE navn=" navn + ";"
                statement.executeQuery(sql);
                return true;
            } catch (SQLException se) {
                se.printStackTrace();
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                try {
                    if (statement != null)
                        connection.close();
                } catch (SQLException se) {
                }// do nothing
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }//end finally try
            }
        }
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