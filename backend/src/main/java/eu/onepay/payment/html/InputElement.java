package eu.onepay.payment.html;

public class InputElement {
    
    private String name;
    private String value;
    
    public InputElement (String name, String value ){
        this.name = name;
        this.value = value;
    }
    
    public InputElement (String name, double value ){
        this.name = name;
        this.value = Double.toString(value);
    }
    
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
