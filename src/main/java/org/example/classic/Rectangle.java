package org.example.classic;

public class Rectangle extends Square {
    final int b;

    public Rectangle(int x, int y, int a, int b) {
        super(x, y, a);
        this.b = b;
    }

    Shape b(int b) {
        if (this.b == b) {
            return this;
        }
        if (b == this.a) {
            return new Square(x, y, a);
        }
        if (b == 0 && a == 0) {
            return new Pixel(x, y);
        }
        return new Rectangle(x, y, a, b);
    }
}
