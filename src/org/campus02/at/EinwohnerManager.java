package org.campus02.at;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class EinwohnerManager {

    public static void main(String[] args) throws DataFileException {
        ArrayList<Einwohner> loadedEinwohner = new EinwohnerManager().load();

        Collections.sort(loadedEinwohner);

        for (Einwohner einwohner : loadedEinwohner) {
            System.out.println(einwohner);
        }

    }

    public ArrayList<Einwohner> load() throws DataFileException {
        String filePath = "data\\testdata-einwohner.csv";
        ArrayList<Einwohner> einwohnerList = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            br.readLine();
            // br.skip(1);
            String line;

            while((line = br.readLine()) != null){
                String[] einwohnerData = line.split(";");
                int id = Integer.parseInt(einwohnerData[0]);
                String name = einwohnerData[1];
                String bundesland = einwohnerData[2];
                int geburtsjahr = Integer.parseInt(einwohnerData[3]);

                Einwohner einwohner = new Einwohner(id, name, bundesland, geburtsjahr);

                einwohnerList.add(einwohner);
            }

            Collections.sort(einwohnerList, new EinwohnerComparator());

            return einwohnerList;
        } catch (Exception e) {
            throw new DataFileException("Error: " + e.getMessage(), e);
        }
    }

}
