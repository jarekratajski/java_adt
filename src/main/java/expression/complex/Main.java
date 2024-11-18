package expression.complex;

public class Main {

        public static void main(String[] args) {
            NBoolean a = new NBoolean(true);
            NBoolean b = new NBoolean(false);
            NBoolean c = new NBoolean(true);
            System.out.println(a.eq(b).show());
            System.out.println(a.eq(c).show());
            System.out.println(a.plus(b).show());
            System.out.println(a.plus(c).show());

            NInt a1 = new NInt(1);
            NInt a2 = new NInt(2);
            var a3 = new Add(new Lit(a1), new Lit(a2));
            System.out.println(a3.eval().show());

            var a4 = new Add(new Lit(a2), new Lit(a2));
            var eq = new Eq(a3, a4);
            System.out.println(eq.show());
            System.out.println(eq.eval().show());

        }
}
