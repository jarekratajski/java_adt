package expression.simple;

public sealed interface Expr<T> {

    default Expr<Boolean> eq(Expr<T> other) {
        return new BoolExpr(this.eval().equals(other.eval()));
    }

    default String show() {
        return switch (this) {
            case IntExpr(var i)  when i  <0 -> "("+i+")";
            case IntExpr(var s) -> s.toString();
            case BoolExpr(var b) -> b.toString();
            case Sum(var left, var right) -> "("+left.show() + " + " + right.show()+")";
            case Sub(var left, var right) -> "("+left.show() + " - " + right.show()+")";
            case Product<T> p -> "("+p.left().show() + " * " + p.right().show()+")";
            case Eq<?>(var left, var right) -> left.show() + " == " + right.show();
        };
    }
    default T eval() {
        return switch (this) {
            case IntExpr(var i)  -> (T)i;
            case BoolExpr(var b) -> (T)b;
            case Sum(var left, var right) -> switch (left.eval()) {
                case Integer v ->(T) (Integer)(v + (Integer) right.eval());
                case Boolean v -> (T) (Boolean)(v | (Boolean) right.eval());
                default -> throw new IllegalStateException("Unexpected value: " + left.eval());
            };
            case Sub<T> s -> switch (s.left().eval()) {
                case Integer v ->(T) (Integer)(v - (Integer) s.right().eval());
                default -> throw new IllegalStateException("Unexpected value: " + s.left().eval());
            };
            case Product(var left, var right)  -> switch (left.eval()) {
                case Integer v -> (T) (Integer)(v * (Integer) right.eval());
                default -> throw new IllegalStateException("Unexpected value: " + left.eval());
            };
            case Eq<?>(var left, var right)  -> (T) (Boolean)(left.eval().equals(right.eval()));
        };
    }

    default Expr<T> norm() {
        return switch (this) {
            case Sub(var left, IntExpr(var i)) when i<0 -> new Sum(left, new IntExpr(-i));
            case Sub(var left, Sub(var innerLeft, var innerRight))  ->
                new Sum(left, new Sub(innerRight, innerLeft).norm());
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
