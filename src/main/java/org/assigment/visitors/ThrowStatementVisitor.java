package org.assigment.visitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.ThrowStatement;

public class ThrowStatementVisitor extends ASTVisitor {
    @Override
    public boolean visit(ThrowStatement throwStatement) {

        return true;
    }
}
