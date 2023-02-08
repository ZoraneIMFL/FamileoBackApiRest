package service;

import junit.framework.TestCase;
import org.junit.Test;

import java.sql.Connection;

public class BDDSingletonTests extends TestCase {
    @Test
    public void test() {
        Connection c = BDDSingleton.getInstance();
        assertNotNull(c);
    }
}
