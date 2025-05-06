import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BayesianNetwork {

    // Data members
    private final Map<String, Variable> variables;
    private final Map<String, List<String>> children;

    // Constructor
    public BayesianNetwork(List<Variable> variables) {
        this.variables = new HashMap<>();
        this.children = new HashMap<>();

        for (Variable variable : variables) {
            this.variables.put(variable.getName(), variable);
            for (String parent : variable.getParents()) {
                this.children.putIfAbsent(parent, new ArrayList<>());
                children.get(parent).add(variable.getName());
            }
        }
    }

    public Map<String, Variable> getVariables() {
        return variables;
    }

    public Map<String, List<String>> getChildren() {
        return children;
    }


}
