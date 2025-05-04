import java.util.List;
import java.util.Map;

public class BayesianNetwork {

    // Data members
    private List<Variable> variables;
    private Map<Variable, List<Variable>> variableDependencyMap;
    private Map<Variable, List<Double>> cptMap;

    // Constructor
    public BayesianNetwork() {

    }
}
