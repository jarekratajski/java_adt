package containers;

sealed interface Optional<T> {
    record Some<T>(T value) implements Optional<T> {}

   None NONE = new None();

    static <T> Optional<T> none() {
        return (Optional<T>) NONE;
    }

    final static class None implements Optional<Void> {
        private None() { }
    }

    default String show()  {
        return switch(this) {
            case Some<T> s -> "Some(" + s.value + ")";
            case None n -> "None";
        };
    }

    public static void main(String[] args) {
        Optional<Integer> some = new Some<>(1);
        Optional<Integer> none = Optional.none();
        System.out.println(some.show());
        System.out.println(none.show());
    }
}


