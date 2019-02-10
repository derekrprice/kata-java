import org.apache.commons.csv.CSVRecord;

public interface CSVRowOperator {
    void op(CSVRecord row);
}
