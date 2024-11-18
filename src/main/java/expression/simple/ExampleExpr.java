package expression.simple;

public class ExampleExpr {


    public static final Expr exampleExpr;
        static {
            var a1 = new IntExpr(1);
            var a2 = new IntExpr(2);
            var a3 = new IntExpr(3);
            var e1 = new Sum(a1, a2);
            var e2 = new Sum(a3, e1);
            var e3 = new Sub(e2, a1);
            exampleExpr = e3;
    }
}
