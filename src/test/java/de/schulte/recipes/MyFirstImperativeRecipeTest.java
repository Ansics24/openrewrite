package de.schulte.recipes;

import org.junit.jupiter.api.Test;
import org.openrewrite.java.Assertions;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

import static org.junit.jupiter.api.Assertions.*;

class MyFirstImperativeRecipeTest implements RewriteTest {

    @Override
    public void defaults(RecipeSpec spec) {
        spec.recipe(new MyFirstImperativeRecipe());
    }

    @Test
    void myFirstTest() {
        rewriteRun(Assertions.java(
                """
                        public class Foo {}
                        """
        ));
    }
}
