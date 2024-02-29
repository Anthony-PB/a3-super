package cs2110;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Scanner;

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
            while (lines.hasNextLine()){
                String line = lines.nextLine();
                String[] values = line.split(",", -1);

                Seq<String> row = new LinkedSeq<>();
                for(String value: values){
                    row.append(value);
                }
                table.append(row);
            }
        }
        catch (IOException e){
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
    public static Seq<Seq<String>> join(Seq<Seq<String>> left, Seq<Seq<String>> right){
        assert(left.size() >= 1 && right.size() >= 1);
        Seq<Seq<String>> joined = new LinkedSeq<>();
        for (Seq<String> leftRow : left) {
            boolean foundMatch = false;
            for (Seq<String> rightRow : right) {
                if (leftRow.get(0).equals(rightRow.get(0))) {
                    // Found a match, add the combined row to the result
                    Seq<String> combinedRow = new LinkedSeq<>();
                    for (String s: leftRow){
                        combinedRow.append(s);
                    }
                    // Starting at i = 1 to skip the first column.
                    for ( int i = 1; i < rightRow.size(); i++){
                        combinedRow.append(rightRow.get(i));
                    }
                    joined.append(combinedRow);
                    foundMatch = true;
                    break;
                }
            }
            // Could also do else I suppose.
            if (!foundMatch) {
                // No match found, add a row with empty strings from `right`
                Seq<String> emptyRow = new LinkedSeq<>(); // emptyRow only has leftRow contents.
                for (String s: leftRow){
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
}
