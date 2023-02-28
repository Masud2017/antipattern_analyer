package org.assigment.services;

import org.eclipse.jdt.core.dom.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ThrowKitchenSinkAntiPatternDetector implements AntiPatternDetector {
    private Scanner fileReader = null;
    private String source;
    private int occuranceCount = 0;
    public ThrowKitchenSinkAntiPatternDetector(String filename) throws FileNotFoundException {
        File file = new File(filename);
        this.fileReader = new Scanner(new FileInputStream(file));
    }
    @Override
    public boolean hasAntiPattern() {
        while (this.fileReader.hasNext()) {
            this.source += this.fileReader.nextLine() + "\n";
        }

        ASTParser parser = ASTParser.newParser(AST.getJLSLatest());
        parser.setSource(source.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setResolveBindings(true);
        CompilationUnit unit = (CompilationUnit) parser.createAST(null);
        ThrowsKitchenSinkDetector detector = new ThrowsKitchenSinkDetector();
        unit.accept(detector);

        for (MethodDeclaration methodItem : detector.getAffectedMethods()) {
            System.out.println("Method Name: " + methodItem.getName());
            System.out.println("Line Number: " + unit.getLineNumber(methodItem.getStartPosition()));
        }
        System.out.println("\n\n*************************************************************");
        this.occuranceCount = detector.getAffectedMethods().size();


        return !detector.getAffectedMethods().isEmpty();
    }

    @Override
    public int getAntiPatternCount() {
        return this.occuranceCount;
    }

    @Override
    public void clearPatternCount() {

    }

    static class ThrowsKitchenSinkDetector extends ASTVisitor {
        private List<MethodDeclaration> affectedMethods = new ArrayList<>();

        @Override
        public boolean visit(MethodDeclaration node) {
            List<Type> exceptions = node.thrownExceptionTypes();

            if (exceptions.size() > 3) {
                affectedMethods.add(node);
            }

            return false;
        }
        public List<MethodDeclaration> getAffectedMethods() {
            return affectedMethods;
        }
    }
}