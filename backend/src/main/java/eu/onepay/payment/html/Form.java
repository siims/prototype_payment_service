package eu.onepay.payment.html;

import java.util.ArrayList;
import java.util.List;

public class Form {

    private String action = "";
    private String method = "";
    private List<InputElement> inputs = new ArrayList<>();
    private String INPUT_NAME = "%NAME%";
    private String INPUT_VALUE = "%VALUE%";
    private String INPUT_STRING = INPUT_NAME + " " + "<input  name=\"" + INPUT_NAME + "\" value=\"" + INPUT_VALUE
            + "\" />";
    private String LINE_BREAK = "<br/>";

    @Override
    public String toString() {

        StringBuilder formAsString = new StringBuilder();

        formAsString.append("<form method=\"" + method + "\"  action=\"" + action + "\">");
        for (InputElement input : inputs) {

            String inputString = INPUT_STRING.replace(INPUT_NAME, input.getName());
            inputString = inputString.replaceAll(INPUT_VALUE, input.getValue());

            formAsString.append(inputString);
            formAsString.append(LINE_BREAK);
        }
        formAsString.append("</form>");

        return formAsString.toString();
    }

    public void addInputElement(String name, String value) {
        InputElement input = new InputElement(name, value);
        this.setInput(input);
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<InputElement> getInputs() {
        return inputs;
    }

    public void setInput(InputElement input) {
        this.inputs.add(input);
    }

}
