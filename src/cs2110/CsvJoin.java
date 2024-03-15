package cs2110;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import org.w3c.dom.DOMStringList;

public class CsvJoin {

    /**
     * Load a table from a Simplified CSV file and return a row-major list-of-lists representation.
     * The CSV file is assumed to be in the platform's default encoding. Throws an IOException if
     * there is a problem reading the file.
     */
    public static Seq<Seq<String>> csvToList(String file) throws IOException {
        Seq<Seq<String>> table = new LinkedSeq<>();

        try (Reader in = new FileReader(file)) {
            Scanner lines = new Scanner(in);
            while (lines.hasNextLine()) {
                String line = lines.nextLine();
                String[] values = line.split(",", -1);

                Seq<String> row = new LinkedSeq<>();
                for (String value : values) {
                    row.append(value);
                }
                table.append(row);
            }
        } catch (IOException e) {
            throw new IOException();
        }
        return table;
    }

    /**
     * Return the left outer join of tables `left` and `right`, joined on their first column. Result
     * will represent a rectangular table, with empty strings filling in any columns from `right`
     * when there is no match. Requires that `left` and `right` represent rectangular tables with at
     * least 1 column.
     */
    public static Seq<Seq<String>> join(Seq<Seq<String>> left, Seq<Seq<String>> right) {
        assert (left.size() >= 1 && right.size() >= 1);
        int topL = left.get(0).size();
        int topR = right.get(0).size();
        for(Seq<String> rows: right){
            if(rows.size()!=topR){
                throw new IllegalArgumentException("Error: Right table is not rectangular.");
            }
        }
        for(Seq<String> rows: left){
            if(rows.size()!=topL){
                throw new IllegalArgumentException("Error: Left table is not rectangular.");
            }
        }

        Seq<Seq<String>> joined = new LinkedSeq<>();
        for (Seq<String> leftRow : left) {
            boolean foundMatch = false;
            for (Seq<String> rightRow : right) {
                if (leftRow.get(0).equals(rightRow.get(0))) {
                    // Found a match, add the combined row to the result after combining
                    Seq<String> combinedRow = new LinkedSeq<>();
                    for (String s : leftRow) {
                        combinedRow.append(s);
                    }
                    // Starting at i = 1 to skip the first column
                    for (int i = 1; i < rightRow.size(); i++) {
                        combinedRow.append(rightRow.get(i));
                    }
                    joined.append(combinedRow);
                    foundMatch = true;
                }
            }
            // Could also do else I suppose.
            if (!foundMatch) {
                // No match found, add a row with empty strings from `right`
                Seq<String> emptyRow = new LinkedSeq<>(); // emptyRow only has leftRow contents
                for (String s : leftRow) {
                    emptyRow.append(s);
                }
                for (int i = 1; i < right.get(0).size(); i++) {
                    emptyRow.append("");
                }
                joined.append(emptyRow);
            }
        }

        return joined;

    }

    /**
     * Convert a 2D linked list representing a CSV file to a CSV string.
     *
     * @param table A 2D linked list of strings. Each inner list represents a row in the CSV file,
     *              and each string in the inner list represents a cell in the CSV file.
     * @return A string representing the CSV file. Each row is represented as a line in the string,
     *         and each cell is separated by a comma. Rows are separated by newline characters (\n).
     */
    private static String toCsvTable(Seq<Seq<String>> table) {
        String result = "";
        for (Seq<String> row : table) {
            result += String.join(",", row) + "\n";
        }
        return result;
    }
/**
 * The entry point of the CsvJoin application.
 *
 * @param args An array of command-line arguments. The first element should be the path to the
 * first CSV file, and the second element should be the path to the second CSV file.
 * The CSV files should represent rectangular tables with at least one column.
 * After making the CSV files into lists, it joins the two lists using the join method and
 * converts the result back to a CSV string using the toCsvTable method.
 */

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: cs2110.CsvJoin <left_table.csv> <right_table.csv>");
            System.exit(1);
        }
        String fileL = args[0];
        String fileR = args[1];
        try {
            Seq<Seq<String>> leftList = csvToList(fileL);
            Seq<Seq<String>> rightList = csvToList(fileR);
            Seq<Seq<String>> joinedList = join(leftList, rightList);
            System.out.println(toCsvTable(joinedList));
        } catch (IOException e) {
            System.err.println("Error: Could not read an input file.");
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: Ensure both csv files are in a rectangular format.");
            System.exit(1);
        }
    }
}
