package parametric;

sealed public interface Tree<T> {

    record Empty()  implements Tree<Void> {
    }

    record Node<T>(Tree<T> left, T value, Tree<T> right) implements Tree<T> {
    }

    default boolean contains(T value) {
        return switch (this) {
            case Tree.Empty l -> false;
            case Node n -> n.value.equals(value) || n.left.contains(value) || n.right.contains(value);
        };
    }
}
