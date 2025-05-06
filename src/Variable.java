import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Variable {

    // Data members
    private String name;
    private List<String> outcomes;
    private List<String> parents;
    private List<Double> cpt;

    // Constructor
    public Variable(Node var, Node def) {
        NodeList varChildren = var.getChildNodes();
        NodeList defChildren = def.getChildNodes();
        setName(varChildren);
        setOutcomes(varChildren);
        setParents(defChildren);
        setCpt(defChildren);
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    private void setName(NodeList varChildren) {
        this.name = varChildren.item(0).getNodeValue();
    }

    public List<String> getOutcomes() {
        return outcomes;
    }

    private void setOutcomes(NodeList varChildren) {
        this.outcomes = new ArrayList<>();
        for (int i = 1; i < varChildren.getLength(); i++) {
            outcomes.add(varChildren.item(i).getNodeValue());
        }
    }

    public List<String> getParents() {
        return parents;
    }

    private void setParents(NodeList defChildren) {
        this.parents = new ArrayList<>();
        for (int i = 0; i < defChildren.getLength(); i++) {
            if(defChildren.item(i).getNodeName().equals("GIVEN")) {
                parents.add(defChildren.item(i).getNodeValue());
            }
        }
    }

    public List<Double> getCpt() {
        return cpt;
    }

    private void setCpt(NodeList defChildren) {
        this.cpt = new ArrayList<>();
        String table = "";
        for (int i = 0; i < defChildren.getLength(); i++) {
            if(defChildren.item(i).getNodeName().equals("TABLE")) {
                table = defChildren.item(i).getNodeValue();
            }
        }
        if (!table.isEmpty()) {
            String[] nums = table.split(" ");
            for (String num : nums) {
                cpt.add(Double.parseDouble(num));
            }
        }
    }


}
