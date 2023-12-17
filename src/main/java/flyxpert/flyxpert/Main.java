package flyxpert.flyxpert;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
public class Main extends Application {
    public static void main(String[] args) throws FileNotFoundException {
        Reader reader = new Reader();
        reader.readAllFiles();
        ReadExternalConfig externalConfig = new ReadExternalConfig();
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.initialize(8);
        dijkstra.run(0);
        for(int i = 1; i < 5; i++)
        {
            dijkstra.printShortestPathFromSource(i);
        }
        launch(args);
        Writer writer = new Writer();
        writer.writeToAllFiles();
    }
    public void start(Stage stage) throws IOException {
        HomePageController.homePageScene(stage);
    }
}