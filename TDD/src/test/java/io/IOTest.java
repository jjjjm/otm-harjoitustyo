package io;

import fi.defence.engine.Path;
import fi.defence.io.IO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.util.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IOTest {

    IO io;

    public IOTest() {
    }

    @Before
    public void setUp() {
        io = new IO();
        File file = new File("test1");
        if (file.exists()) {
            file.delete();
        }
        file = new File("maps.txt");
        if (file.exists()) {
            file.delete();
        }
    }

    @After
    public void tearDown() {
        File file = new File("test1");
        if (file.exists()) {
            file.delete();
        }
        file = new File("maps.txt");
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void createFileCreatesFileIfDoesntExist() throws IOException {
        File file = new File("test1");
        Boolean exists = file.exists();
        io.createFile("test1");
        assertTrue(!exists && file.exists());
    }

    @Test
    public void createFileDoesntCreateFileIfItExists() throws IOException {
        File file = new File("test1");
        file.createNewFile();
        PrintWriter w = new PrintWriter(file);
        w.write("test");
        w.close();
        io.createFile("test1");
        assertTrue(new BufferedReader(new FileReader(file)).readLine().contains("test"));

    }

    @Test
    public void savePathSavesMultiplePathsCorrectly() throws IOException { // works with one also
        Path path = new Path(0);
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        list.add(new Pair(0, 0));
        path.setCoords(list);
        io.savePath(path, "one");
        list.add(new Pair(1, 1));
        io.savePath(path, "two");
        BufferedReader rd = new BufferedReader(new FileReader("maps.txt"));
        boolean firstClause = rd.readLine().contains("one");
        boolean secondClause = rd.readLine().contains("two");
        assertTrue(firstClause && secondClause);
    }

    //todo test on top of already existing
    
    @Test
    public void loadPathLoadsNameCorrectly() throws IOException {
        Path path = new Path(0);
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        list.add(new Pair(0, 0));
        path.setCoords(list);
        io.savePath(path, "one");
        list.add(new Pair(1, 1));
        io.savePath(path, "two");
        HashMap<String, List<Pair<Integer, Integer>>> hm = io.loadPath();
        assertTrue(hm.containsKey("one") && hm.containsKey("two"));
    }

    @Test
    public void loadPathLoadsCordinatesCorrectly() throws IOException {
        Path path = new Path(0);
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        list.add(new Pair(0, 0));
        path.setCoords(list);
        io.savePath(path, "one");
        list.add(new Pair(1, 1));
        io.savePath(path, "two");
        HashMap<String, List<Pair<Integer, Integer>>> hm = io.loadPath();
        System.out.println(hm);
        assertTrue(hm.get("one").get(0).getKey() == 0 && hm.get("two").get(1).getKey() == 1);
    }
}
