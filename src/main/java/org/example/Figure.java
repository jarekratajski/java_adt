package org.example;




sealed interface Shape {
}

record Pixel(int x, int y) implements Shape {
}

record Box(Pixel upperLeft, int a) implements Shape {
}

record Rectangle(Pixel upperLeft, int a, int b) implements Shape {
}