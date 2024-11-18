package examples.anyadt;

sealed class A {
    int a;
    boolean b;
}

//A is a union of types [C,D]
final class C extends A {
    String c;
}

final class D extends A {
    double d;
}
