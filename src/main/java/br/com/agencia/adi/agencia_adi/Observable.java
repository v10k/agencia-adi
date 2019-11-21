package br.com.agencia.adi.agencia_adi;

public interface Observable {
	public void registerObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObservers(int id);  
}
