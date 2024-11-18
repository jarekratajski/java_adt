package expression.simple;

class Main {
    void main(String[] args) {
        var a1 = new IntExpr(1);
        var a2 = new IntExpr(2);
        var a3 = new IntExpr(3);
        var e1 = new Sum(a1, a2);
        var e2 = new Sum(a3, e1);
        System.out.println(e2.show());
        System.out.println(e2.eval());
        
        var e3 = new Sub(e2, a2);
        var e4 = new Sum(a1, a3);
        System.out.println(e3.show());
        System.out.println(e4.show());
        var b1 = new Eq(e3, e4);
        System.out.println(b1.show());
        System.out.println(b1.eval());
        var false1 = new BoolExpr(false);
        var b2 = new Eq(e3, false1);
        System.out.println(b2.show());
        System.out.println(b2.eval());

        var e5 = new Sub(a1, new IntExpr(-5));
        System.out.println(e5.show());
        var e6 = e5.norm();
        System.out.println(e6.show());

        var e7 = new Sub(a1, new Sub(new IntExpr(-3), a2));
        System.out.println(e7.show());
        System.out.println(e7.norm().show());


    }
}

