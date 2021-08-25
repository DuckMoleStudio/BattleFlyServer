package BattleFlyServer.supplimentary;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// create a mock results json file if no client app at hand

public class MockFileWriter {
    public static void main(String[] args){
        List<ResultStringClass> results = new ArrayList<>();

        // 1 sortie 4 pilots 2 teams
        results.add(new ResultStringClass("Pokryshkin",1,1,300,33,
                3,0,8,0,1,0,1,"none"));
        results.add(new ResultStringClass("Rechkalov",1,1,400,15,
                8,3,0,0,0,0,1,"none"));
        results.add(new ResultStringClass("Hartmann",2,1,200,3,
                20,0,0,0,0,1,0,"Pokryshkin"));
        results.add(new ResultStringClass("Rudel",2,1,250,0,
                20,15,0,0,0,1,0,"group"));

        // 2 sortie
        results.add(new ResultStringClass("Pokryshkin",1,2,200,11,
                3,0,0,0,0,0,1,"none"));
        results.add(new ResultStringClass("Rechkalov",1,2,100,11,
                20,0,0,0,0,1,1,"Hartmann"));
        results.add(new ResultStringClass("Hartmann",2,2,300,23,
                2,0,0,0,1,0,0,"none"));
        results.add(new ResultStringClass("Rudel",2,2,210,0,
                20,9,0,0,0,1,0,"group"));

        // 3 sortie
        results.add(new ResultStringClass("Pokryshkin",1,3,330,16,
                20,9,0,0,0,1,0,"group"));
        results.add(new ResultStringClass("Rechkalov",1,3,340,10,
                8,8,0,0,0,0,0,"none"));
        results.add(new ResultStringClass("Hartmann",2,3,290,20,
                8,0,0,0,0,0,1,"none"));
        results.add(new ResultStringClass("Rudel",2,3,150,8,
                18,6,0,0,0,0,1,"none"));

        // 4 sortie
        results.add(new ResultStringClass("Pokryshkin",1,4,300,24,
                15,0,0,0,1,0,0,"none"));
        results.add(new ResultStringClass("Rechkalov",1,4,400,16,
                20,0,0,0,1,1,0,"Hartmann"));
        results.add(new ResultStringClass("Hartmann",2,4,200,23,
                20,0,0,0,1,1,0,"Pokryshkin"));
        results.add(new ResultStringClass("Rudel",2,4,350,12,
                20,9,0,0,0,1,0,"Rechkalov"));

        ObjectMapper objectMapper = new ObjectMapper();
        try(FileWriter writer = new FileWriter("C:\\Users\\User\\Documents\\bf_results001.txt"))
        {
            for(ResultStringClass cc: results) writer.write(objectMapper
                    .writeValueAsString(cc));

            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
