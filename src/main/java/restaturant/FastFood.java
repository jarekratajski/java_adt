package restaturant;

public class FastFood {
}

sealed interface Ingredient {

}

sealed interface PizzaIngredient extends Ingredient {

}

record Ham(int slices) implements PizzaIngredient {

}

record  Champinions(int weight) implements PizzaIngredient {

}


non-sealed interface SoupIngredient extends Ingredient {

}


sealed interface Dish {
}

sealed interface Pizza extends Dish {

}

record Margherita() implements Pizza {}

record PizzaWithTopping(Pizza base, PizzaIngredient ingredient) {

}