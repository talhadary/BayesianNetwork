import java.util.ArrayList;
import java.util.List;

public class Query {

    // Data members
    private List<Pair<String, String>> variables;   // List of variables for joint probability calculation
    private Pair<String, String> targetVariable;    // Target variable for conditional probability calculation
    private List<Pair<String, String>> evidenceMap; // List of evidence for conditional probability calculation
    private final boolean isJoint;                  // Flag to remember if the probability is joint or conditional
    private int algorithm;                          // Which algorithm to use for conditional probability calculation

    // Constructor. receives a query from input file and converts it into query object
    public Query(String query) {
        String[] parts = queryPreprocessor(query);   // Preprocessing original query string
        // If there is no comma to denote algorithm number after the closing brackets, it's a joint probability
        isJoint = parts[1].isEmpty();
        if (isJoint) {
            // Setting the joint probability variables into the Data members
            setVariables(parts[0]);
        } else {
            String[] targetEvidence = parts[0].split("\\|");    // Splitting target from evidence

            // Setting the conditional probability variables into data members
            setTargetVariable(targetEvidence[0]);
            setEvidenceMap(targetEvidence[1]);
            setAlgorithm(parts[1]);
        }
    }

    // Cleans up query string
    private String[] queryPreprocessor(String query) {
        // Splitting the query into usable strings
        String[] parts = query.split("\\)");
        parts[0] = parts[0].replace("P(", "");

        // If there's a comma after cosing brackets, remove it
        parts[1] = parts[1].replace(",", "");
        return parts;
    }

    // Getters and setters. since a query is given and cannot change,
    // setters are private and are only called in the constructors

    public boolean getIsJoint() {
        return this.isJoint;
    }

    public List<Pair<String, String>> getVariables() {
        return this.variables;
    }

    private void setVariables(String query) {
        variables = new ArrayList<>();
        String[] variableStates = query.split(","); // Splitting variables from one another
        // Storing variable name and outcome
        for (String variableState : variableStates) {
            variables.add(new Pair<>(variableState.split("=")[0], variableState.split("=")[1]));
        }
    }

    public Pair<String, String> getTargetVariable() {
        return this.targetVariable;
    }

    // Storing target variable name and outcome
    private void setTargetVariable(String query) {
        String[] target = query.split("=");
        targetVariable = new Pair<>(target[0], target[1]);
    }

    public List<Pair<String, String>> getEvidenceMap() {
        return this.evidenceMap;
    }

    private void setEvidenceMap(String query) {
        evidenceMap = new ArrayList<>();
        String[] variableStates = query.split(","); // Splitting variables from one another
        // Storing evidence variable name and outcome
        for (String variableState : variableStates) {
            evidenceMap.add(new Pair<>(variableState.split("=")[0], variableState.split("=")[1]));
        }
    }

    public int getAlgorithm() {
        return this.algorithm;
    }

    private void setAlgorithm(String algorithm) {
        this.algorithm = Integer.parseInt(algorithm);
    }

}
