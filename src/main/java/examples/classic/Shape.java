package examples.classic;

public interface Shape {

    Shape height(int h);
}



class  Pixel implements Shape {
    final int x;
    final int y;

    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Shape height(int h) {
        throw new UnsupportedOperationException();
    }
}

class Square extends Pixel {
    final int a;
    public Square(int x, int y, int a) {
        super(x, y);
        this.a = a;
    }
}

