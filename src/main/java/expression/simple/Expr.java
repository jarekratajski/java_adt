package expression.simple;

public sealed interface Expr<T> {

    default Expr<Boolean> eq(Expr<T> other) {
        return new BoolExpr(this.eval().equals(other.eval()));
    }

    default String show() {
        return switch (this) {
            case IntExpr i when i.v() <0 -> "("+i.v().toString()+")";
            case IntExpr i -> i.v().toString();
            case BoolExpr b -> b.v().toString();
            case Sum<T> s -> "("+s.left().show() + " + " + s.right().show()+")";
            case Sub<T> s -> "("+s.left().show() + " - " + s.right().show()+")";
            case Product<T> p -> "("+p.left().show() + " * " + p.right().show()+")";
            case Eq<?> e -> e.left().show() + " == " + e.right().show();
        };
    }
    default T eval() {
        return switch (this) {
            case IntExpr i -> (T)i.v();
            case BoolExpr b -> (T)b.v();
            case Sum<T> s -> switch (s.left().eval()) {
                case Integer v ->(T) (Integer)(v + (Integer) s.right().eval());
                case Boolean v -> (T) (Boolean)(v | (Boolean) s.right().eval());
                default -> throw new IllegalStateException("Unexpected value: " + s.left().eval());
            };
            case Sub<T> s -> switch (s.left().eval()) {
                case Integer v ->(T) (Integer)(v - (Integer) s.right().eval());
                default -> throw new IllegalStateException("Unexpected value: " + s.left().eval());
            };
            case Product<T> p -> switch (p.left().eval()) {
                case Integer v -> (T) (Integer)(v * (Integer) p.right().eval());
                default -> throw new IllegalStateException("Unexpected value: " + p.left().eval());
            };
            case Eq<?> e -> (T) (Boolean)(e.left().eval().equals(e.right().eval()));
        };
    }

    default Expr<T> norm() {
        return switch (this) {
            case Sub<T> s -> switch (s.right()) {
                case Sub<T> r -> new Sum(s.left(), new Sub(r.right(), r.left()).norm()); //story about norm here
                case IntExpr i when i.v() < 0-> new Sum(s.left(), new IntExpr(-i.v()));
                default -> s;
            };
            default -> this;
        };
    }

    T evalp();

}

record Sum<T>(Expr<T> left, Expr<T> right) implements Expr<T> {
    @Override
    public T evalp() {
        return switch (left().evalp()) {
            case Integer v ->(T) (Integer)(v + (Integer) right().evalp());
            case Boolean v -> (T) (Boolean)(v | (Boolean) right().evalp());
            default -> throw new IllegalStateException("Unexpected value: " + left().evalp());
        };
    }
}

record Sub<T>(Expr<T> left, Expr<T> right) implements Expr<T> {
    @Override
    public T evalp() {
        return switch (left().evalp()) {
            case Integer v ->(T) (Integer)(v - (Integer) right().evalp());
            default -> throw new IllegalStateException("Unexpected value: " + left().evalp());
        };
    }
}

record Product<T>(Expr<T> left, Expr<T> right) implements Expr<T> {
    @Override
    public T evalp() {
        return switch (left().evalp()) {
            case Integer v ->(T) (Integer)(v * (Integer) right().evalp());
            default -> throw new IllegalStateException("Unexpected value: " + left().evalp());
        };
    }
}


record Eq<T>(Expr<T> left, Expr<T> right) implements Expr<Boolean> {

    @Override
    public Boolean evalp() {
        return left.evalp().equals(right.evalp());
    }
}

record IntExpr(Integer v) implements Expr<Integer> {
    @Override
    public Integer evalp() {
        return v;
    }
}

record BoolExpr(Boolean v) implements Expr<Boolean> {
    @Override
    public Boolean evalp() {
        return v;
    }
}
