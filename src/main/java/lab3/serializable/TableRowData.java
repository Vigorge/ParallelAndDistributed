package labTwo.serializable;

import java.io.Serializable;

public class TableRowData implements Serializable {
    private static final long serialVersionUID = 2L;
    private static final String MAGIC_DEL = "!@#&&%:!";

    private String[] values;


    /*private String eliminateQuotes(String str) {
        return str.replaceAll("\"", "");
    }*/

    public static TableRowData parseRowQuoted(String row, String dlmtr) {
        return new TableRowData(
                row.replaceAll("\"" + dlmtr + "\"", MAGIC_DEL)
                .replaceAll("\"", "")
                .split(MAGIC_DEL)
        );
    }

    public static TableRowData parseRow(String row, String regex) {
        return new TableRowData(row.split(regex));
    }

    private TableRowData(String[] values) {
        this.values = values;
    }

    public String getValue(int column) {
        return values[column];
    }
}
