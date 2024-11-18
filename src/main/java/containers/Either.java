package containers;

sealed interface Either<T, U> {

    record Left<T>(T t) implements Either<T, Void> {
    }

    record Right<U>(U u) implements Either<Void, U> {
    }

    static <T,U>  Either<T,U> left(T t ) {
        return (Either<T,U>)new Left<T>(t);
    }
    static <T,U>  Either<T,U> right(U u ) {
        return (Either<T,U>)new Right<U>(u);
    }

    default String show() {
        return switch (this) {
            case Left<?> l -> "Left(" + l.t + ")";
            case Right<?> r -> "Right(" + r.u + ")";
        };
    }

    public static void main(String[] args) {
        Either<Integer, Boolean> left = Either.left(1);
        Either<Integer, Boolean> right =Either.right(false);
        System.out.println(left.show());
        System.out.println(right.show());
    }
}
