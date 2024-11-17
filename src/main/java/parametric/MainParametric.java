package parametric;

public class MainParametric {

    void main(String args[]) {
        var list = new List.Cons<>(3,new List.Cons<>(1, List.empty()));
        System.out.println(list.show());
    }
}
