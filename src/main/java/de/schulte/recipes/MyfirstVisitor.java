package de.schulte.recipes;

import org.openrewrite.Cursor;
import org.openrewrite.ExecutionContext;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.tree.J;

public class MyfirstVisitor extends JavaIsoVisitor<ExecutionContext> {

    public static final String MESSAGE_NAME_UNPERSONAL_GREETING = "unpersonalGreeting";

    @Override
    public J.MethodDeclaration visitMethodDeclaration(J.MethodDeclaration method, ExecutionContext executionContext) {
        final var methodDeclaration = super.visitMethodDeclaration(method, executionContext);
        if (isUnpersonalGreeting(getCursor())) {
            // TODO return adjustment
        }
        return methodDeclaration;
    }

    private boolean isUnpersonalGreeting(Cursor methodCursor) {
        return methodCursor.getMessage(MESSAGE_NAME_UNPERSONAL_GREETING, false);
    }

    @Override
    public J.Literal visitLiteral(J.Literal literal, ExecutionContext executionContext) {
        if (literal.getValue() instanceof String || literal.getValue().toString().equals("Hello")) {
            final var methodCursor = getCursor().dropParentUntil(node -> node instanceof J.MethodDeclaration);
            methodCursor.putMessage(MESSAGE_NAME_UNPERSONAL_GREETING, true);
        }
        return super.visitLiteral(literal, executionContext);
    }
}
