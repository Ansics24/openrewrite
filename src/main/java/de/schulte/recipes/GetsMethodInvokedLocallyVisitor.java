package de.schulte.recipes;

import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.tree.Expression;
import org.openrewrite.java.tree.J;

import java.util.concurrent.atomic.AtomicBoolean;

public class GetsMethodInvokedLocallyVisitor extends JavaIsoVisitor<AtomicBoolean> {

    private final J.MethodDeclaration methodDeclaration;

    public GetsMethodInvokedLocallyVisitor(J.MethodDeclaration methodDeclaration) {
        this.methodDeclaration = methodDeclaration;
    }

    @Override
    public J.MethodInvocation visitMethodInvocation(J.MethodInvocation method, AtomicBoolean atomicBoolean) {
        final var methodInvocation = super.visitMethodInvocation(method, atomicBoolean);

        final boolean nameMatches = method.getName().getSimpleName().equals(methodDeclaration.getName().getSimpleName());
        final boolean returnTypeMatches = method.getType().equals(methodDeclaration.getType());
        final boolean argumentsMatches = method.getArguments().equals(methodInvocation.getArguments());

        if(nameMatches && returnTypeMatches && argumentsMatches) {
            atomicBoolean.set(true);
        }

        return methodInvocation;
    }
}
