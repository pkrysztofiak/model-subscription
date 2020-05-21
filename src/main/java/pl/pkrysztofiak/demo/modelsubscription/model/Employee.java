package pl.pkrysztofiak.demo.modelsubscription.model;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Employee {

    public final Subject<String> setNameRequest = PublishSubject.create();
    
    private final ObjectProperty<String> nameProperty = new SimpleObjectProperty<>();
    public final Observable<String> nameObservable = JavaFxObservable.valuesOf(nameProperty).subscribeOn(Schedulers.single());
    
    {
        setNameRequest.delay(0, TimeUnit.SECONDS, Schedulers.single()).subscribe(this::onSetNameRequest);
    }
    
    public Employee(String name) {
        nameProperty.set(name);
    }
    
    private void onSetNameRequest(String name) {
        System.out.println("setNameRequest(" + name + ")");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        nameProperty.set(name);
    }
}
