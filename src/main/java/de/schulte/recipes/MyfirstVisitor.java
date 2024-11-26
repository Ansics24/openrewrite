package de.schulte.recipes;

import org.openrewrite.Cursor;
import org.openrewrite.ExecutionContext;
import org.openrewrite.java.AddMethodParameter;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.JavaTemplate;
import org.openrewrite.java.tree.J;

import java.util.List;

public class MyfirstVisitor extends JavaIsoVisitor<ExecutionContext> {

    private final JavaTemplate addNameParameter = JavaTemplate.builder("String name").build();

    @Override
    public J.MethodDeclaration visitMethodDeclaration(J.MethodDeclaration method, ExecutionContext executionContext) {
        final var methodDeclaration = super.visitMethodDeclaration(method, executionContext);
        if (getCursor().getMessage("greeting", false)) {
            final J.MethodDeclaration newMethod = addNameParameter.apply(getCursor(), method.getCoordinates().replaceParameters());
            return newMethod.withName(method.getName().withSimpleName("greet")).withMethodType(method.getMethodType().withName("greet"));
        }

        return methodDeclaration;
    }

    @Override
    public J.Literal visitLiteral(J.Literal literal, ExecutionContext executionContext) {
        if (literal.getValue() instanceof String && ((String) literal.getValue()).contains("Hello")) {
            final var methodDeclCursor = getCursor().dropParentUntil(J.MethodDeclaration.class::isInstance);
            final var method = getCursor().firstEnclosing(J.MethodDeclaration.class);
            if(method != null && !method.getName().getSimpleName().equals("greet")) {
                methodDeclCursor.putMessage("greeting", true);
            }
        }
        return super.visitLiteral(literal, executionContext);
    }
}
