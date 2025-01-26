package org.launchcode.moviedock.movie_rec;

import org.launchcode.moviedock.models.Movie;

import java.nio.file.Paths;
import java.io.*;

public class Movie_rec {


    public Movie_rec() {}



    public String runFromJava() throws IOException {
        System.out.println("HI HI HIHIHI IM IN THE JAVA TEST IN THE JAVA SECTION");
        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        //for mac
        // "/bin/sh"
        // -c

        Process proc = null;
        try {
            String command = "python C:\\Users\\Dyerm\\IdeaProjects\\kavin-dev-dazzlers\\src\\main\\java\\org\\launchcode\\moviedock\\movie_rec\\movie_rec.py";
            //String command = "NUL > EmptyFile.txt";
            proc = Runtime.getRuntime().exec(new String[] { "cmd"//$NON-NLS-1$
                    , "/c", command });//$NON-NLS-1$
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
