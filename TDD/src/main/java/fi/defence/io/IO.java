package fi.defence.io;

import fi.defence.engine.Path;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.util.Pair;

public class IO {

    public File createFile(String name) throws IOException {
        File file = new File(name);
        file.createNewFile();
        return file;
    }

    public void savePath(Path path, String name) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(createFile("maps.txt"), true));
        writer.write(name + ":");
        for (Pair<Integer, Integer> coords : path.getCoords()) {
            writer.write(coords.getKey() + "," + coords.getValue() + ":");
        }
        writer.write("end");
        writer.println("");
        writer.close();
    }

    public HashMap<String, List<Pair<Integer, Integer>>> loadPath() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(this.createFile("maps.txt")));
        HashMap<String, List<Pair<Integer, Integer>>> list = new HashMap<>();

        for (String line; (line = br.readLine()) != null;) {

            String[] splitLine = line.split(":");
            List<Pair<Integer, Integer>> coordList = new ArrayList<>();
            for (String coords : (splitLine)) {
                if (!"end".equals(coords) && !coords.equals(splitLine[0])) {
                    String[] coordsInteger = coords.split(",");
                    Pair<Integer, Integer> tmp = new Pair<>(Integer.parseInt(coordsInteger[0]),
                            Integer.parseInt(coordsInteger[1]));

                    coordList.add(tmp);
                }
            }
            list.put(splitLine[0], coordList);
        }
        return list;
    }
}
