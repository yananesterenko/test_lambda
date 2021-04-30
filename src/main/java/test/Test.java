package test;

//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.ssm.SsmClient;
//import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
//import software.amazon.awssdk.services.ssm.model.GetParameterResponse;
//import software.amazon.awssdk.services.ssm.model.SsmException;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;

public class Test {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    GetParameter <paraName>\n\n" +
                "Where:\n" +
                "    paraName - the name of the parameter\n";

        if (args.length < 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        // Get args
        String paraName = args[0];

//        Region region = Region.US_EAST_1;
//        SsmClient ssmClient = SsmClient.builder()
//                .region(region)
//                .build();
//
//        try {
//            GetParameterRequest parameterRequest = GetParameterRequest.builder()
//                    .name(paraName)
//                    .build();
//
//            GetParameterResponse parameterResponse = ssmClient.getParameter(parameterRequest);
//            System.out.println("The parameter value is " + parameterResponse.parameter().value());
//
//        } catch (SsmException e) {
//            System.err.println(e.getMessage());
//            System.exit(1);
//        }
    }

}



