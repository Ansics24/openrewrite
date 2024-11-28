package de.schulte.recipes;

import org.openrewrite.Cursor;
import org.openrewrite.ExecutionContext;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.tree.J;

import java.util.concurrent.atomic.AtomicBoolean;

public class DeleteUnusedPrivateMethodsVisitor extends JavaIsoVisitor<ExecutionContext> {

    @Override
    public J.MethodDeclaration visitMethodDeclaration(J.MethodDeclaration method, ExecutionContext executionContext) {
        final var methodDeclaration = super.visitMethodDeclaration(method, executionContext);

        if(method.hasModifier(J.Modifier.Type.Public)) {
            return methodDeclaration;
        }

        if(isUsed(methodDeclaration)) {
            return methodDeclaration;
        }
        return null;
    }

    private boolean isUsed(J.MethodDeclaration methodDeclaration) {
        final J.ClassDeclaration classDecl = getCursor().firstEnclosing(J.ClassDeclaration.class);
        return new GetsMethodInvokedLocallyVisitor(methodDeclaration).reduce(classDecl,
                new AtomicBoolean()).get();
    }

}
