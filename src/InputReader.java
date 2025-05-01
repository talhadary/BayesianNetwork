import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputReader {
    // Data member
    private BufferedReader br;

    //Constructor
    public InputReader(String fileName) {
        try {
            this.br = new BufferedReader(new FileReader(fileName));
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    public String getNetworkFileName() {
        try {
            String fileName = this.br.readLine();
            br.close();
            return fileName;
        } catch(Exception e) {
            System.out.println("File is empty");
            return null;
        }
    }
    public List<Query> getQueries() {
        List<Query> queries = new ArrayList<>();
        String line;
        try {
            this.br.readLine();
            while((line = this.br.readLine()) != null) {
                queries.add(new Query(line));
            }
            br.close();
        } catch(IOException e) {
            System.out.println("File Is empty");
        }
        return queries;
    }

}
