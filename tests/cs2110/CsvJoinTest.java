package cs2110;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Iterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CsvJoinTest {

    // TODO: Uncomment these tests after implementing the corresponding methods in `CsvJoin`.
    // You must also have implemented `LinkedSeq.toString()` and `LinkedSeq.equals()`.

//    @DisplayName("GIVEN a CSV filename, the CsvJoin class should create a corresponding list")
//    @Test
//    void testCsvToList() throws IOException {
//        // WHEN the data is rectangular
//        Seq<Seq<String>> table = CsvJoin.csvToList("input-tests/example/input2.csv");
//        String expectedString = "[[netid, grade], [def456, junior], [ghi789, first-year], [abc123, senior]]";
//        assertEquals(expectedString, table.toString());
//
//        // WHEN the data is not rectangular
//        table = CsvJoin.csvToList("tests/testCsvToList/non-rectangular.csv");
//        expectedString = "[[1], [1, 2], [1, 2, 3], [1, , , 4], [1, , 3], [1, , ], [1]]";
//        assertEquals(expectedString, table.toString());
//
//        // WHEN the file is empty
//        table = CsvJoin.csvToList("tests/testCsvToList/empty.csv");
//        expectedString = "[]";
//        assertEquals(expectedString, table.toString());
//
//        // WHEN the file contains blank lines
//        table = CsvJoin.csvToList("tests/testCsvToList/empty-col.csv");
//        expectedString = "[[], [], []]";
//        assertEquals(expectedString, table.toString());
//        // Distinguish between empty array and empty string
//        assertEquals(1, table.get(0).size());
//    }
//
//    /**
//     * Assert that joining "input-tests/dir/input1.csv" and "input-tests/dir/input2.csv" yields the
//     * table in "input-tests/dir/output.csv".  Requires that tables in "input1.csv" and "input2.csv"
//     * be rectangular with at least one column.
//     */
//    static void testJoinHelper(String dir) throws IOException {
//        Seq<Seq<String>> left = CsvJoin.csvToList("input-tests/" + dir + "/input1.csv");
//        Seq<Seq<String>> right = CsvJoin.csvToList("input-tests/" + dir + "/input2.csv");
//        Seq<Seq<String>> expected = CsvJoin.csvToList("input-tests/" + dir + "/output.csv");
//        Seq<Seq<String>> join = CsvJoin.join(left, right);
//        assertEquals(expected, join);
//    }
//
//    @DisplayName("GIVEN two lists representing rectangular tables, the CsvJoin class should " +
//            "compute their left outer join on the first column of the first table.")
//    @Test
//    void testJoin() throws IOException {
//        // WHEN the left keys are unique and there is at most one match per key
//        testJoinHelper("example");
//
//        // WHEN there are duplicate left keys and there is at most one match per key
//        testJoinHelper("states");
//
//        // TODO (after implementing `main()`): Run at least two of your own input-tests here
//    }

}
