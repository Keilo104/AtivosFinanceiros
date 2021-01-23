package br.edu.ifsp.domain.usecases.utils;

import br.edu.ifsp.domain.entities.usuario.Usuario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Subject {
    private List<Observer> observerList = new ArrayList<>();

    public void notifyObservers() {
        for (Observer o: this.observerList) {
            o.update(this);
        }
    }

    public void addObserver(Observer o) {
        this.observerList.add(o);
    }

    public void removeObserver(Observer o) {
        this.observerList.remove(o);
    }

    public void removeObserver(int idx) {
        this.observerList.remove(idx);
    }

    public void clearObservers() {
        this.observerList.clear();
    }

    public Iterator<Observer> getObserverIterator() {
        return this.observerList.iterator();
    }
}
