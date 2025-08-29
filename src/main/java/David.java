import java.io.IOException;

public class David {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public David(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            storage.init();
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DavidException e) {
                ui.showError(e.getMessage());
            }
        }
    }


    public static void main(String[] args) {
        new David("data/David.txt").run();
    }
}