package org.launchcode.moviedock.movie_rec;

import org.launchcode.moviedock.models.Movie;

import java.nio.file.Paths;
import java.io.*;

public class Movie_rec {



    public Movie_rec() {}

    public boolean isWindows(){
        String sysname = System.getProperty("os.name");
        return sysname.startsWith("Windows");
    }


    public String runFromJava() throws IOException {
        System.out.println("HI HI HIHIHI IM IN THE JAVA TEST IN THE JAVA SECTION");
        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        //check OS
        System.out.println(isWindows());

        String start;
        String flag;
        String command;

        //for mac
        // "/bin/sh"
        // -c
        // ./src/main/java/org/launchcode/moviedock/movie_rec/movie_rec.py
        Process proc = null;
        try {

            //String command = "NUL > EmptyFile.txt";

            if (isWindows()) {
                command = "python3 .\\src\\main\\java\\org\\launchcode\\moviedock\\movie_rec\\movie_rec.py";
                start = "cmd";
                flag = "/c";
            }
            else{
                command = "python3 ./src/main/java/org/launchcode/moviedock/movie_rec/movie_rec.py";
                start = "/bin/sh";
                flag = "-c";
            }

            proc = Runtime.getRuntime().exec(new String[] { start//$NON-NLS-1$
                    , flag, command });//$NON-NLS-1$
            if (proc != null) {
                System.out.println("proc exists");
                proc.waitFor();
            }
            BufferedReader read = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            try {
                proc.waitFor();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            while (read.ready()) {
                System.out.println(read.readLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());



        } catch (Exception e) {
            //Handle

        }
        System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
        System.out.println("goodbye from java");


        return "a";
    }




}
