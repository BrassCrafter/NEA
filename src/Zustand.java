public abstract class Zustand {
    List<Zustand> folgezustaende0;
    List<Zustand> folgezustaende1;
    String name;

    public Zustand() {

    }

    public List<Zustand> gibFolgezustaende0() {
        return null;
    }

    public List<Zustand> gibFolgezustaende1() {
        return null;
    }

    public abstract boolean istEndzustand();

    public String getName() {
        return name;
    }

    public void setFolgezustaende0(List<Zustand> folgeZustaende){
        folgezustaende0 = folgeZustaende;
    }
    public void setFolgezustaende1(List<Zustand> folgeZustaende){
        folgezustaende1 = folgeZustaende;
    }
}
