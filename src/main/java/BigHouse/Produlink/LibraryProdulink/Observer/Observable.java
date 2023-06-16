package BigHouse.Produlink.LibraryProdulink.Observer;

public interface Observable {

    public void AddObserver(Observer obj);
    public void RemoveObserver(Observer obj);
    public void NotifyAllObserver();
}