public class AutomatNEA {

    private Zustand startzustand;
    private List<Zustand> aktiveZustaende;

    public class Q0 extends Zustand {
        List<Zustand> folgezustaende1;

        public Q0() {
            Q1 q1 = new Q1();
            folgezustaende1 = new List<Zustand>();
            folgezustaende1.append(q1);
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

        public Q1() {
            Q0 q0 = new Q0();
            Q2 q2 = new Q2();
            folgezustaende0 = new List<Zustand>();
            folgezustaende0.append(q0);
            folgezustaende0.append(q2);
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

        public Q2() {
            Q0 q0 = new Q0();
            Q3 q3 = new Q3();
            folgezustaende1 = new List<Zustand>();
            folgezustaende1.append(q0);
            folgezustaende1.append(q3);
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

        public Q3() {
            Q0 q0 = new Q0();
            folgezustaende1 = new List<Zustand>();
            folgezustaende1.append(q0);
        }

        public List<Zustand> gibFolgezustaende1() {
            return folgezustaende1;
        }

        public boolean istEndzustand() {
            return false;
        }

    }

    public void pruefeWort(String pWort) {

    }

    public boolean akzeptiert() {
        return false;
    }

}
