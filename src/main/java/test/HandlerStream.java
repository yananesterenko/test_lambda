package test;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import org.json.simple.parser.JSONParser;

import java.io.File;


public class HandlerStream implements RequestHandler<Object, String> {

    private static AWSSimpleSystemsManagement ssmclient = AWSSimpleSystemsManagementClientBuilder
            .standard().withRegion(Regions.EU_CENTRAL_1).build();


    public String handleRequest(Object inputStream, Context context) {

        boolean isEncrypted = true;
       /* String parameterKey = "/app/fbk-cm/wlgdw";
        GetParameterRequest parameterRequest = new GetParameterRequest();
        parameterRequest.withName(parameterKey).setWithDecryption(isEncrypted);
        GetParameterResult parameterResponse = ssmclient.getParameter(parameterRequest);

        System.out.println("The parameter value is " + parameterResponse.getParameter().getValue());

        context.getLogger().log("Input: " + inputStream);

        String jsonStr = parameterResponse.getParameter().getValue();
        String parameterResponseUnescape = StringEscapeUtils.unescapeJson(jsonStr);

        System.out.println("The parameter value is 2 " + parameterResponseUnescape);

        String username = "";
        String json = parameterResponse.getParameter().getValue();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(parameterResponseUnescape);
            String userName = jsonObject.get("username").toString();
            String password = jsonObject.get("password").toString();
            String host = jsonObject.get("host").toString();
            String port = jsonObject.get("port").toString();
            String service = jsonObject.get("service").toString();
            output = "Hello,  user: " + userName + " password : " + password + " host :  " + host +
                    " port:  " + port + " server : " + service + "!";
        } catch (ParseException e) {
            e.printStackTrace();
            context.getLogger().log("Error : " + e.getMessage());

        }
*/
        String region = System.getenv("AWS_REGION");
        String accesskey = System.getenv("AWS_ACCESS_KEY_ID");
        String secret_key = System.getenv("AWS_SECRET_ACCESS_KEY");
        System.out.println("region = " + region + "accesskey = " + accesskey + " secret_key " + secret_key);
        String output = "";
        JSONParser jsonParser = new JSONParser();

        AWSCredentials credentials = new BasicAWSCredentials(accesskey, secret_key);
        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
        s3.putObject("lambdatest32","test_30_04.txt", "/Users/user/Document/hello.txt");

        output = "test json  : " + output;
        return output;
    }


}