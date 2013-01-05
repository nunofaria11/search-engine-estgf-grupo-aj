/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nuno
 */
public class RankingListTest {

    public RankingListTest() {
    }

    @Test
    public void testCreateRankingList() {
        System.out.println("createRankingList");
        double[][] M = null;
        double[][] Q = null;
        ArrayList<String> filenames = null;
        RankingList instance = null;
        instance.createRankingList(M, Q, filenames);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetRankingList() {
        System.out.println("getRankingList");
        RankingList instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getRankingList();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
}
