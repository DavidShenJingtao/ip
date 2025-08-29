import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path path;

    public Storage(String p) {
        this.path = Paths.get(p);
    }

    public void init() throws IOException {
        if (Files.notExists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }
        if (Files.notExists(path)) {
            Files.createFile(path);
        }
    }

    /*public void save(ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(path.toFile(), false);
        for (Task t : list) {
            fw.write(t.toString() + System.lineSeparator());
        }
        fw.close();
    }*/

    public void save(TaskList tasks) throws DavidException {
        try {
            ArrayList<Task> list = tasks.getList();
            FileWriter fw = new FileWriter(path.toFile(), false);
            for (Task t : list) {
                fw.write(t.toString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new SaveException("cannot save changes.");
        }
        
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> list = new ArrayList<>();
        if (Files.notExists(path)) {
            return list; //empty list
        }
        BufferedReader br = Files.newBufferedReader(path);
        String line;
        while ((line = br.readLine()) != null) {
            loadTasks(list, line);
        }
        return list;
    }


    private static void loadTasks(ArrayList<Task> list, String line) {
        try {
            Task t = Task.create(line);
            list.add(t);
        } catch (DavidException e) {
            System.out.println(Formatter.format("Corrupted input: " + e.getMessage()));
        }
    }
}