package org.campus02.at;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class EinwohnerHandler {

    private Socket client;
    private EinwohnerManager em;

    public EinwohnerHandler(Socket client) {
        this.client = client;
        this.em = new EinwohnerManager();
    }

    public void start() throws DataFileException {
        ArrayList<Einwohner> einwohnerList = em.load();

        System.out.println("Warte auf Kommando von Client...");
        try(BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))){

            String cmd;
            while((cmd = br.readLine()) != null){
                System.out.println("cmd = " + cmd);
                String[] cmds = cmd.split(" ");
                if(cmds[0].equals("GET")){
                    System.out.println("Bearbeite Anfrage...");
                    if (cmds.length == 2) {
                        if(cmds[1].equalsIgnoreCase("wien")){
                            searchBundesland(cmds[1], einwohnerList, bw);
                            bw.write("Einwohner Ende");
                            bw.newLine();
                            bw.flush();
                        } else if(cmds[1].equalsIgnoreCase("steiermark")){
                            searchBundesland(cmds[1], einwohnerList, bw);
                            bw.write("Einwohner Ende");
                            bw.newLine();
                            bw.flush();
                        } else if(cmds[1].equalsIgnoreCase("kärnten")){
                            searchBundesland(cmds[1], einwohnerList, bw);
                            bw.write("Einwohner Ende");
                            bw.newLine();
                            bw.flush();
                        }
                    } else {
                        searchByGeburtsjahr(cmds[1], einwohnerList, bw);
                        bw.write("Einwohner Ende");
                    }
                } else if(cmds[0].equals("EXIT")){
                    client.close();
                } else {
                    bw.write("Wrong Input!");
                    bw.newLine();
                    bw.flush();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void searchByGeburtsjahr(String geburtsjahr, ArrayList<Einwohner> einwohnerList, BufferedWriter bw) throws IOException {
        int gj = Integer.parseInt(geburtsjahr);

        ArrayList<Einwohner> duplicateEW = new ArrayList<>(einwohnerList);
        Collections.sort(duplicateEW);

        for (Einwohner einwohner : einwohnerList) {
            if(einwohner.getGeburtsjahr() == gj){
                bw.write(einwohner.toString());
                bw.newLine();
            }
        }
    }

    private void searchBundesland(String bundesland, ArrayList<Einwohner> einwohnerList, BufferedWriter bw) throws IOException {
        for (Einwohner einwohner : einwohnerList) {
            if(einwohner.getBundesland().equalsIgnoreCase(bundesland)){
                bw.write(einwohner.toString());
            }
        }
    }

}
