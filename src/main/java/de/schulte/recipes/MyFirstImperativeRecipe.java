package de.schulte.recipes;

import org.openrewrite.ExecutionContext;
import org.openrewrite.NlsRewrite;
import org.openrewrite.Recipe;
import org.openrewrite.TreeVisitor;

public class MyFirstImperativeRecipe extends Recipe {
    @Override
    public @NlsRewrite.DisplayName String getDisplayName() {
        return "My first imperative recipe";
    }

    @Override
    public @NlsRewrite.Description String getDescription() {
        return "The first imperative recipe as a showcase for the OpenRewrite course";
    }

    @Override
    public TreeVisitor<?, ExecutionContext> getVisitor() {
        return new MyfirstVisitor();
    }
}
