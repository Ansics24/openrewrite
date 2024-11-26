package de.schulte.recipes;

import org.openrewrite.ExecutionContext;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.tree.J;

public class MyfirstVisitor extends JavaIsoVisitor<ExecutionContext> {

    @Override
    public J.MethodDeclaration visitMethodDeclaration(J.MethodDeclaration method, ExecutionContext executionContext) {
        System.out.printf("Visiting method %s%n", method.getName());
        return super.visitMethodDeclaration(method, executionContext);
    }

    @Override
    public J.ClassDeclaration visitClassDeclaration(J.ClassDeclaration classDecl, ExecutionContext executionContext) {
        final var result = super.visitClassDeclaration(classDecl, executionContext);
        System.out.printf("Visited class %s%n", classDecl.getName().getSimpleName());
        return result;
    }
}
