package org.assigment;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        String sourceFolder = System.getProperty("sourceFolder");
        System.out.println("Printing the data : "+sourceFolder);

        assertTrue( true
        );
    }
}
