/**
 * This class has been generated by Fast Code Eclipse Plugin 
 * For more information please go to http://fast-code.sourceforge.net/
 * @author : mihkel
 * Created : 09/03/2015 01:41:09
 */

package eu.onepay.payment.html;

import org.junit.*;

import static org.junit.Assert.*;
import eu.onepay.payment.html.Form;
import eu.onepay.payment.html.InputElement;

import java.lang.String;



public class FormTest {

    private Form form = new Form();

    /**
     *
     * @see eu.onepay.payment.html.Form#toString()
     */
    @Test
    public void testToString() {
        
        InputElement element = new InputElement("amount", 124.00);
        form.setInput(element);
    	String result = form.toString();
    	System.out.println(result);
    	assertNotNull("result cannot be null", result);
    }

	
}
