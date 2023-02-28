package ca.concordia.soen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;

public class Example {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public void test() {
        Function method = new Function() {
            @Override
            public Object apply(Object arg0) {
                return null;
            }
        };
    }

    public void test2() {

        try {
            Files.lines(Paths.get("asdfa"));
            try {
                throw new Exception(("sdfs"));

            } finally {
                throw Exception("This is finally 3");
            }


        } catch (IOException e) {
            try {
                throw new Exception("asdf");
            } catch (Exception e1) {

            } finally {
                throw new Exception("finally 2");

            }
        } finally {
            throw new Exception("finally 1");
        }

        try {

        } catch (Exception e) {
            try {
                try {

                } finally {
                    System.out.println("Hello world this is a test finally block");
                    throw new Exception("finally 6");
                }

            } finally {
                throw new Exception("Finally 4");
            }
        } finally {
            throw new Exception("finally 5");
        }
    };

}

