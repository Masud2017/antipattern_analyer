package org.assigment.visitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.TryStatement;

public class TryStatementVisitor extends ASTVisitor {
    @Override
    public boolean visit(TryStatement tryStatement) {
        return true;
    }
}
