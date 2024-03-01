package NEA_0_1;

public class AutomatNEA {

    private Zustand startzustand;
    private List<Zustand> aktiveZustaende;
    private String word;

    public class Q0 extends Zustand {


        public Q0() {
            super();
        }

        public List<Zustand> gibFolgezustaende1() {
            List<Zustand> folgezustaende1 = new List<>();
            Q1 q1 = new Q1();
            folgezustaende1.append(q1);
            folgezustaende1.toFirst();
            return folgezustaende1;
        }

        public boolean istEndzustand() {
            return true;
        }

    }

    public class Q1 extends Zustand {


        public Q1() {
            super();
        }

        public List<Zustand> gibFolgezustaende0() {
            List<Zustand> folgezustaende0 = new List<>();
            Q0 q0 = new Q0();
            Q2 q2 = new Q2();
            folgezustaende0.append(q0);
            folgezustaende0.append(q2);
            return folgezustaende0;
        }

        public boolean istEndzustand() {
            return false;
        }

    }

    public class Q2 extends Zustand {

        public Q2() {
            super();
        }

        public List<Zustand> gibFolgezustaende1() {
            List<Zustand> folgezustaende1 = new List<>();
            Q0 q0 = new Q0();
            Q3 q3 = new Q3();
            folgezustaende1.append(q0);
            folgezustaende1.append(q3);
            return folgezustaende1;
        }

        public boolean istEndzustand() {
            return false;
        }

    }

    public class Q3 extends Zustand {

        public Q3() {
            super();
        }

        public List<Zustand> gibFolgezustaende1() {
            List<Zustand> folgezustaende1 = new List<>();
            Q0 q0 = new Q0();
            folgezustaende1.append(q0);
            return folgezustaende1;
        }

        public boolean istEndzustand() {
            return false;
        }

    }

    public AutomatNEA() {
        Q0 q0 = new Q0();
        startzustand = q0;
        aktiveZustaende = new List<>();
        aktiveZustaende.append(startzustand);
    }

    public void pruefeWort(String pWort) {
        word = pWort;
        while (!word.isEmpty()) {
            char character = getCurrentChar();
            List<Zustand> tempStateStorage = new List<>();
            switch (character) {
                case '0':
                    for (aktiveZustaende.toFirst(); aktiveZustaende.hasAccess(); aktiveZustaende.next())
                        tempStateStorage.concat(aktiveZustaende.getContent().gibFolgezustaende0());
                    break;
                case '1':
                    for (aktiveZustaende.toFirst(); aktiveZustaende.hasAccess(); aktiveZustaende.next())
                        tempStateStorage.concat(aktiveZustaende.getContent().gibFolgezustaende1());
                    break;
            }
            aktiveZustaende = tempStateStorage;
            aktiveZustaende.toFirst();
        }
    }

    public boolean akzeptiert() {
        for (aktiveZustaende.toFirst(); aktiveZustaende.hasAccess(); aktiveZustaende.next()) {
            if (aktiveZustaende.getContent().istEndzustand()) return true;
        }
        return false;
    }

    private char getCurrentChar() {
        char first = word.charAt(0);
        word = word.substring(1);
        return first;
    }

    public static void main(String[] args) {
        AutomatNEA nea = new AutomatNEA();
        String word = "";
        nea.pruefeWort(word);
        if (nea.akzeptiert()) System.out.println("Das Wort \"" + word + "\" wird von diesem Automaten akzeptiert.");
        else {
            System.out.println("Das Wort \"" + word + "\" wird von diesem Automaten nicht akzeptiert.");
        }
    }
}