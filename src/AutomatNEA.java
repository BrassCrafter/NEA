public class AutomatNEA {

    private Zustand startzustand;
    private String word;
    private List<Zustand> aktiveZustaende;
    Q0 q0;
    Q1 q1;
    Q2 q2;
    Q3 q3;

    public class Q0 extends Zustand {

        public Q0(String name) {
            folgezustaende1 = new List<>();
            this.name = name;
        }

        public List<Zustand> gibFolgezustaende1() {
            return folgezustaende1;
        }

        public boolean istEndzustand() {
            return true;
        }

    }

    public class Q1 extends Zustand {
        public Q1(String name) {
            folgezustaende0 = new List<>();
            this.name = name;
        }

        public List<Zustand> gibFolgezustaende0() {
            return folgezustaende0;
        }

        public boolean istEndzustand() {
            return false;
        }

    }

    public class Q2 extends Zustand {

        public Q2(String name) {
            folgezustaende1 = new List<>();
            this.name = name;
        }

        public List<Zustand> gibFolgezustaende1() {
            return folgezustaende1;
        }

        public boolean istEndzustand() {
            return false;
        }

    }

    public class Q3 extends Zustand {
        public Q3(String name) {
            this.name = name;
            folgezustaende1 = new List<>();
        }

        public List<Zustand> gibFolgezustaende1() {
            return folgezustaende1;
        }

        public boolean istEndzustand() {
            return false;
        }

    }

    public AutomatNEA() {
        System.out.println("Starting construction...");
        q0 = new Q0("Q0");
        q1 = new Q1("Q1");
        q2 = new Q2("Q2");
        q3 = new Q3("Q3");
        List<Zustand> folgezustaendeQ01 = new List<>();
        folgezustaendeQ01.append(q1);
        List<Zustand> folgezustaendeQ10 = new List<>();
        folgezustaendeQ10.append(q0);
        folgezustaendeQ10.append(q2);
        List<Zustand> folgezustaendeQ21 = new List<>();
        folgezustaendeQ21.append(q0);
        folgezustaendeQ21.append(q3);
        List<Zustand> folgezustaendeQ31 = new List<>();
        folgezustaendeQ31.append(q0);
        q0.setFolgezustaende1(folgezustaendeQ01);
        q1.setFolgezustaende0(folgezustaendeQ10);
        q2.setFolgezustaende1(folgezustaendeQ21);
        q3.setFolgezustaende1(folgezustaendeQ31);
        System.out.println(q0.getName());
        System.out.println(q0.gibFolgezustaende1().getContent().getName());
        System.out.println(q1.getName());
        System.out.println(q2.getName());
        System.out.println(q3.getName());
        startzustand = q0;
        aktiveZustaende = new List<>();
        aktiveZustaende.append(startzustand);
        aktiveZustaende.toFirst();
        System.out.println("Startzustand: " + aktiveZustaende.getContent().getName());
        System.out.println("Construction complete!");
    }

    public void pruefeWort(String pWort) {
        word = pWort;
        while (!word.isEmpty()) {
            for (aktiveZustaende.toFirst(); aktiveZustaende.hasAccess(); aktiveZustaende.next())
                System.out.println(aktiveZustaende.getContent().getName());
            geheInDieFolgezustaende(getCurrentChar());
            if (notValid())
                return;

        }

        System.out.println("Finished");

    }

    public boolean akzeptiert() {
        if (!notValid())
            for (aktiveZustaende.toFirst(); aktiveZustaende.hasAccess(); aktiveZustaende.next())
                if (aktiveZustaende.getContent() == startzustand)
                    return true;
        return false;
    }

    private void geheInDieFolgezustaende(char currentChar) {
        List<Zustand> newActiveStates = new List<Zustand>();
        switch (currentChar) {
            case '0':
                for (aktiveZustaende.toFirst(); aktiveZustaende.hasAccess(); aktiveZustaende.next()) {
                    if (aktiveZustaende.getContent().gibFolgezustaende0() != null) {
                        newActiveStates.concat(aktiveZustaende.getContent().gibFolgezustaende0());
                        System.out.println("Folgezustände 0: " + aktiveZustaende.getContent().getName());
                    }
                }
                break;
            case '1':
                for (aktiveZustaende.toFirst(); aktiveZustaende.hasAccess(); aktiveZustaende.next()) {
                    if (aktiveZustaende.getContent().gibFolgezustaende1() != null) {
                        newActiveStates.concat(aktiveZustaende.getContent().gibFolgezustaende1());
                        System.out.println("Folgezustände 1: " + aktiveZustaende.getContent().getName());
                    }

                }
                break;
            default:
                break;
        }
        aktiveZustaende = newActiveStates;
    }

    private char getCurrentChar() {

        System.out.println(word);

        if (word.isEmpty()) //in diesem Modell überflüssig => für weitere Modelle zur Prävention stehengelassen
            return '$';
        else {
            char first = word.charAt(0);
            word = word.substring(1);
            return first;
        }
    }

    private boolean notValid() {
        return (aktiveZustaende.isEmpty());
    }

    public static void main(String[] args) {
        String inputString = "1011";
        AutomatNEA nea = new AutomatNEA();
        nea.pruefeWort(inputString);
        if (nea.akzeptiert()) {
            System.out.println("Das Wort '" + inputString + "' wurde aktzeptiert. ");
        } else {
            System.out.println("Das Wort '" + inputString + "' wurde nicht aktzeptiert. ");
        }
    }
}
