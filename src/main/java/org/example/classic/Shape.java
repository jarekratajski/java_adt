package org.example.classic;

interface Shape {

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

class Rectangle extends Square {
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
            return new Square(x,y,a);
        }
        if (b == 0 && a == 0) {
            return new Pixel(x,y);
        }
        return new Rectangle(x,y,a,b);
    }
}