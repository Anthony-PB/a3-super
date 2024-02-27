package cs2110;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

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
            while (lines.hasNext()){
                String line = lines.nextLine();
                String[] values = line.split(",", -1);

                Seq<String> row = new LinkedSeq<>();
                for(String value: values){
                    row.append(value);
                }
                table.append(row);
            }
        }
        return table;
    }
}