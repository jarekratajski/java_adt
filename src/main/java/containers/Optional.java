package containers;

sealed interface Optional<T> {
    record Some<T>(T value) implements Optional<T> {}

   enum NONE implements Optional<Void> {
       INSTANCE;
   }

    static <T> Optional<T> none() {
        return (Optional<T>) NONE.INSTANCE;
    }



    default String show()  {
        return switch(this) {
            case Some<T> s -> "Some(" + s.value + ")";
            case NONE  _ -> "None";
        };
    }

    public static void main(String[] args) {
        Optional<Integer> some = new Some<>(1);
        Optional<Integer> none = Optional.none();
        System.out.println(some.show());
        System.out.println(none.show());
    }
}


