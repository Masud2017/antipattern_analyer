package org.assigment.services;

import org.eclipse.jdt.core.dom.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ThrowInsideFinallyAntiPatternDetector implements AntiPatternDetector {
    private Scanner fileReader = null;
    private String source;
    private Integer throwInsideFinallyCount = 0;
    public ThrowInsideFinallyAntiPatternDetector(String filename) throws FileNotFoundException {
        File file = new File(filename);
        this.fileReader = new Scanner(new FileInputStream(file));
    }

    @Override
    public boolean hasAntiPattern() {
        while (this.fileReader.hasNext()) {
            this.source += this.fileReader.nextLine();
        }

        ASTParser parser = ASTParser.newParser(AST.getJLSLatest());
        parser.setSource(this.source.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);

        CompilationUnit root = (CompilationUnit) parser.createAST(null);

        ThrowInFinallyVisitor visitor = new ThrowInFinallyVisitor(this.throwInsideFinallyCount);
        root.accept(visitor);
        this.throwInsideFinallyCount = visitor.getThrowInsideFinallyCount();

        return false;
    }

    @Override
    public int getAntiPatternCount() {
        return throwInsideFinallyCount;
    }

    @Override
    public void clearPatternCount() {
        this.throwInsideFinallyCount = 0;
    }


    static class ThrowInFinallyVisitor extends ASTVisitor {
        private Integer throwInsideFinallyCount;
        public ThrowInFinallyVisitor(Integer throwInsideFinallyCount) {
            this.throwInsideFinallyCount = throwInsideFinallyCount;
        }
        @Override
        public boolean visit(TryStatement tryStatement) {
            if (tryStatement.getFinally() != null) {

                tryStatement.getFinally().accept(new ASTVisitor() {
                    @Override
                    public boolean visit(ThrowStatement node) {
                        throwInsideFinallyCount++;
                        System.out.println(node);
                        return false;
                    }
                });

                tryStatement.getBody().accept(this);
                for (Object o : tryStatement.catchClauses()) {
                    ((CatchClause) o).getBody().accept(this);
                }
                if (tryStatement.getFinally() != null) {
                    tryStatement.getFinally().accept(this);
                }

            }
            return false;
        }

        public Integer getThrowInsideFinallyCount () {return this.throwInsideFinallyCount;}

    }
}
