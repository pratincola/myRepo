import junit.framework.*;
import com.mockobjects.servlet.MockHttpServletRequest;
import com.mockobjects.servlet.MockHttpServletResponse;

import java.text.DecimalFormat;


/**
 * Created by prateek on 11/22/14.
 */
public class MockTestTestingLabConverterServlet extends TestCase {


    private static String testTemperature;
    private static final String nullTestOutput = "<html><head><title>No Temperature</title></head><body><h2>Need to enter a temperature!</h2></body></html>\n";
    private static final String invalidTestOutput = "<html><head><title>Bad Temperature</title></head><body><h2>Need to enter a valid temperature!Got a NumberFormatException on ";
    private static final String closeOutputTags = "</h2></body></html>\n";
    private static final String validTestOutput = "<html><head><title>Temperature Converter Result</title></head><body><h2>";
    private static final String validTestOutputEnd = " Celsius </h2>\n";
    private static final String austinTemperature = "<p><h3>The temperature in Austin is 451 degrees Farenheit</h3>\n</body></html>\n";

    public void test_bad_parameter() throws Exception {
        TestingLabConverterServlet s = new TestingLabConverterServlet();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setupAddParameter("farenheitTemperature", (String[]) null);
        response.setExpectedContentType("text/html");
        s.doGet(request,response);
        response.verify();
        assertEquals("<html><head><title>No Temperature</title>"
                + "</head><body><h2>Need to enter a temperature!"
                + "</h2></body></html>", response.getOutputStreamContents());
    }

    public void test_inputs() throws Exception {
        TestingLabConverterServlet s = new TestingLabConverterServlet();
        String[] testTemperatures = {"-20", "-5", "0","20","50", "150", "212"};
        DecimalFormat df = new DecimalFormat("#.##");

        for(String temp:testTemperatures){
            MockHttpServletRequest request = new MockHttpServletRequest();
            MockHttpServletResponse response = new MockHttpServletResponse();

            Double farTempDouble = Double.parseDouble(temp);
            Double celTempDouble = 100.0*(farTempDouble - 32.0)/180.0;
            String celTemp = df.format(celTempDouble);

            request.setupAddParameter("farenheitTemperature", temp);
            response.setExpectedContentType("text/html");

            s.doGet(request,response);
            response.verify();
            String a = "<html><head><title>Temperature Converter Result</title></head><body><h2>"
                    + temp + " Farenheit = " + celTemp + " Celsius </h2>\n"
                    + "<p><h3>The temperature in Austin is 451 degrees Farenheit</h3>\n"
                    + "</body></html>\n";
            System.out.print(response.getOutputStreamContents());
            System.out.print(a);
            assertEquals(a,
                    response.getOutputStreamContents());
        }
    }
}
