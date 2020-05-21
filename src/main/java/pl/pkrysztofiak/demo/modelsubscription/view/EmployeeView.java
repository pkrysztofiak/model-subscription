package pl.pkrysztofiak.demo.modelsubscription.view;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import pl.pkrysztofiak.demo.modelsubscription.model.Employee;

public class EmployeeView extends StackPane {

    private final TextField nameTextField = new TextField();
    private final Observable<Boolean> focusedObservable = JavaFxObservable.valuesOf(nameTextField.focusedProperty());
    private final Observable<String> nameObservable = JavaFxObservable.valuesOf(nameTextField.textProperty());
    
    public EmployeeView(Employee employee) {
        getChildren().add(nameTextField);
        
        focusedObservable.switchMap(focused -> focused ? Observable.empty() : employee.nameObservable).observeOn(JavaFxScheduler.platform()).subscribe(nameTextField::setText);
        focusedObservable.switchMap(focused -> focused ? nameObservable.skip(1) : Observable.empty()).subscribe(employee.setNameRequest);
    }
}