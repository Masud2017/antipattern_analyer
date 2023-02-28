package org.assigment.visitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CatchClause;

public class CatchClauseVisitor extends ASTVisitor {
    @Override
    public boolean visit(CatchClause catchClause) {

        return true;
    }
}
