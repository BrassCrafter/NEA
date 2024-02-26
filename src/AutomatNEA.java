public class AutomatNEA {

    private Zustand startzustand;
    private List<Zustand> aktiveZustaende;
    private String word;

    public class Q0 extends Zustand {
        List<Zustand> folgezustaende1;

        public Q0(Zustand q1) {
            folgezustaende1 = new List<>();
            folgezustaende1.append(q1);
            if(folgezustaende1.isEmpty()) System.out.println(" 0 Ist leer");
        }

        public List<Zustand> gibFolgezustaende1() {
            return folgezustaende1;
        }

        public boolean istEndzustand() {
            return true;
        }

    }

    public class Q1 extends Zustand {
        List<Zustand> folgezustaende0;

        public Q1(Zustand q0, Zustand q2) {
            folgezustaende0 = new List<>();
            folgezustaende0.append(q0);
            folgezustaende0.append(q2);
            if(folgezustaende0.isEmpty()) System.out.println(" 1 Ist leer");
        }

        public List<Zustand> gibFolgezustaende0() {
            return folgezustaende0;
        }

        public boolean istEndzustand() {
            return false;
        }

    }

    public class Q2 extends Zustand {
        List<Zustand> folgezustaende1;

        public Q2(Zustand q0, Zustand q3) {
            folgezustaende1 = new List<>();
            folgezustaende1.append(q0);
            folgezustaende1.append(q3);
            if(folgezustaende1.isEmpty()) System.out.println(" 2 Ist leer");
        }

        public List<Zustand> gibFolgezustaende1() {
            return folgezustaende1;
        }

        public boolean istEndzustand() {
            return false;
        }

    }

    public class Q3 extends Zustand {
        List<Zustand> folgezustaende1;

        public Q3(Zustand q0) {
            folgezustaende1 = new List<>();
            folgezustaende1.append(q0);
            if(folgezustaende1.isEmpty()) System.out.println(" 3 Ist leer");
        }

        public List<Zustand> gibFolgezustaende1() {
            return folgezustaende1;
        }

        public boolean istEndzustand() {
            return false;
        }

    }

    public AutomatNEA() {
        Zustand q1 = null;
        Zustand q2 = null;
        Zustand q3 = null;
        Zustand q0 = new Q0(q1);
        q1 = new Q1(q0, q2);
        q2 = new Q2(q0, q3);
        q3 = new Q3(q0);
        startzustand = q0;
        aktiveZustaende = new List<>();
        aktiveZustaende.append(startzustand);
    }

    public void pruefeWort(String pWort) {
        word = pWort;
        while (!word.isEmpty()) {
            for (aktiveZustaende.toFirst(); aktiveZustaende.hasAccess(); aktiveZustaende.next()) geheInDieFolgezustaende(getCurrentChar());
            if (notValid())
                return;

        }
    }

    private void geheInDieFolgezustaende(char currentChar) {
        List<Zustand> newActiveStates = new List<Zustand>();
        switch (currentChar) {
            case '0':
                for (aktiveZustaende.toFirst(); aktiveZustaende.hasAccess(); aktiveZustaende.next()) {
                    if (aktiveZustaende.getContent().gibFolgezustaende0() != null) {
                        newActiveStates.concat(aktiveZustaende.getContent().gibFolgezustaende0());
                    }
                }
                break;
            case '1':
                for (aktiveZustaende.toFirst(); aktiveZustaende.hasAccess(); aktiveZustaende.next()) {
                    if (aktiveZustaende.getContent().gibFolgezustaende1() != null) {
                        newActiveStates.concat(aktiveZustaende.getContent().gibFolgezustaende1());
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

    public boolean akzeptiert() {
        return false;
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
