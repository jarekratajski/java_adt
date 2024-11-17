package parametric;

sealed interface List<T> {

    static <T> List<T>empty() {
        return (List<T>) Empty.INSTANCE;
    }

    final class Empty implements List<Void> {
        private Empty() { }
        private static Empty INSTANCE= new Empty();
    }

    record Cons<T>(T head, List<T> tail) implements List<T> {
    }

    default String show() {
        return switch (this) {
            case Empty e -> "[]";
            case Cons c -> c.head + " : " + c.tail.show();
        };
    }
}
