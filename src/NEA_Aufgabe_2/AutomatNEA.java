package NEA_Aufgabe_2;


public class AutomatNEA {

    private Zustand startzustand;
    private List<Zustand> aktiveZustaende;
    private String word;

    public class Q0 extends Zustand {


        public Q0() {
            super();
        }

        public List<Zustand> gibFolgezustaendeA() {
            List<Zustand> folgezustaende = new List<>();
            Q1 q1 = new Q1();
            folgezustaende.append(q1);
            folgezustaende.toFirst();
            return folgezustaende;
        }

        public List<Zustand> gibFolgezustaendeB() {
            List<Zustand> folgezustaende = new List<>();
            Q1 q1 = new Q1();
            Q2 q2 = new Q2();
            folgezustaende.append(q1);
            folgezustaende.append(q2);
            folgezustaende.toFirst();
            return folgezustaende;
        }

        public boolean istEndzustand() {
            return true;
        }

    }

    public class Q1 extends Zustand {


        public Q1() {
            super();
        }

        public List<Zustand> gibFolgezustaendeB() {
            List<Zustand> folgezustaende = new List<>();
            Q2 q2 = new Q2();
            folgezustaende.append(q2);
            return folgezustaende;
        }

        public boolean istEndzustand() {
            return true;
        }

    }

    public class Q2 extends Zustand {

        public Q2() {
            super();
        }

        public List<Zustand> gibFolgezustaendeA() {
            List<Zustand> folgezustaende = new List<>();
            Q1 q1 = new Q1();
            folgezustaende.append(q1);
            return folgezustaende;
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
                case 'a':
                    for (aktiveZustaende.toFirst(); aktiveZustaende.hasAccess(); aktiveZustaende.next())
                        tempStateStorage.concat(aktiveZustaende.getContent().gibFolgezustaendeA());
                    break;
                case 'b':
                    for (aktiveZustaende.toFirst(); aktiveZustaende.hasAccess(); aktiveZustaende.next())
                        tempStateStorage.concat(aktiveZustaende.getContent().gibFolgezustaendeB());
                    break;
                default:
                    System.out.println("Character not in the alphabet.");
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
        String word = "abababababababababababababababa";
        nea.pruefeWort(word);
        if (nea.akzeptiert()) System.out.println("Das Wort \"" + word + "\" wird von diesem Automaten akzeptiert.");
        else {
            System.out.println("Das Wort \"" + word + "\" wird von diesem Automaten nicht akzeptiert.");
        }
    }
}