package expression.complex;


sealed interface Alg<T extends Alg> {

    default  String show() {
        return switch (this) {
            case NBoolean b -> String.valueOf(b.value());
            case NInt n -> String.valueOf(n.value());
            case NDouble n -> String.valueOf(n.value());
        };
    }

    default NBoolean eq(T other) {
        return switch (other) {
            case NBoolean b -> new NBoolean( b.value() == ((NBoolean) this).value());
            case NInt n -> new NBoolean(n.value() == ((NInt) this).value());
            case NDouble n -> new NBoolean(n.value() == ((NDouble) this).value()); //tricky
        };
    }

    T plus(T other);
}

record NBoolean(boolean value) implements Alg<NBoolean> {

    @Override
    public NBoolean plus(NBoolean other) {
        return new NBoolean(value | other.value());
    }
}

sealed interface Num<T extends Num> extends Alg<T> {

}

record NDouble(Double value) implements Num<NDouble> {

    @Override
    public NDouble plus(NDouble other) {
        return new NDouble(value + other.value());
    }
}

record NInt(Integer value) implements Num<NInt> {

    @Override
    public NInt plus(NInt other) {
        return new NInt(value + other.value());
    }
}

sealed interface Expr<T extends Alg<T>> {

    T eval();

    default String show() {
        return switch (this) {
            case Lit<T> l -> l.t().show();
            case Add<T> a -> a.left().show() + " + " + a.right().show();
//           case Mul<T> m -> m.left().show() + " * " + m.right().show();
            case Eq<?> e -> e.left().show() + " == " + e.right().show();
        };
    }

}

record Lit<T extends Alg<T>>(T t) implements Expr<T> {

    @Override
    public T eval() {
        return t;
    }
}

record Add<T extends Alg<T>>(Expr<T> left, Expr<T> right) implements Expr<T> {

    @Override
    public T eval() {
        return left.eval().plus(right.eval());
    }

}


record Eq<T extends Alg<T>>(Expr<T> left, Expr<T> right) implements Expr<NBoolean> {

    @Override
    public NBoolean eval() {
        return left.eval().eq(right.eval());
    }
}