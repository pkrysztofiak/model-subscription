package pl.pkrysztofiak.demo.modelsubscription;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.pkrysztofiak.demo.modelsubscription.model.Employee;
import pl.pkrysztofiak.demo.modelsubscription.view.EmployeeView;

public class App extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Employee employee = new Employee("Kowalsky");

        EmployeeView employeeView1 = new EmployeeView(employee);
        EmployeeView employeeView2 = new EmployeeView(employee);
        
        VBox vBox = new VBox(employeeView1, employeeView2);
        
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }
}