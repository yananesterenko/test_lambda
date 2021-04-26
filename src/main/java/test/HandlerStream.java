package test;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterResult;
import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class HandlerStream implements RequestHandler<Object, String> {

    private static AWSSimpleSystemsManagement ssmclient = AWSSimpleSystemsManagementClientBuilder
            .standard().withRegion(Regions.EU_CENTRAL_1).build();


    public String handleRequest(Object inputStream, Context context) {

        boolean isEncrypted = true;
        String parameterKey = "/app/fbk-cm/wlgdw";
        GetParameterRequest parameterRequest = new GetParameterRequest();
        parameterRequest.withName(parameterKey).setWithDecryption(isEncrypted);


        GetParameterResult parameterResponse = ssmclient.getParameter(parameterRequest);


        System.out.println("The parameter value is " + parameterResponse.getParameter().getValue());

        context.getLogger().log("Input: " + inputStream);

        //    Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //   PojoClass el = new PojoClass();
//        String jsonStr = gson.toJson(parameterResponse.getParameter().getValue().replace("\n","")
//                .replace( "\\\\", ""))
//                .replaceAll("^\"|\"$", "");

        String jsonStr = parameterResponse.getParameter().getValue();
        String parameterResponseUnescape = StringEscapeUtils.unescapeJson(jsonStr);

        System.out.println("The parameter value is 2 " + jsonStr);

        //   ObjectMapper objectMapper = new ObjectMapper();
        String username = "";
        String json = parameterResponse.getParameter().getValue();


        //ObjectMapper objectMapper = new ObjectMapper();
        // String unwrappedJSON = objectMapper.readValue(json, String.class);
        //       YourClass result = objectMapper.readValue(unwrappedJSON, YourClass.class);


//        try {
//            JSONObject json = new JSONObject((String) new JSONParser().parse(parameterResponse.getParameter().getValue()));
//            //String userName = json.get("username").toString();
//        } catch (ParseException e) {
//            System.out.println("Error : " + e.getMessage());
//        }

        //  PojoClass object = new Gson().fromJson(jsonStr, PojoClass.class);

        JSONParser jsonParser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonStr);
            String userName = jsonObject.get("username").toString();
            String password = jsonObject.get("password").toString();
            String host = jsonObject.get("host").toString();
            String port = jsonObject.get("port").toString();
            String service = jsonObject.get("service").toString();
            String output = "Hello, user: " + userName + " password : "+ password + " \n" + "host :  " + host +
                "port:  "+port + "serv: " + service +"!";
        } catch (ParseException e) {
            e.printStackTrace();
        }


//        String password = obj.get("password").toString();
//        String host = obj.get("host").toString();
//        String port = obj.get("port").toString();
//        String service = obj.get("service").toString();
//        String output = "Hello, user: " + userName + " password : "+ password + " \n" + "host :  " + host +
//                "port:  "+port + "serv: " + service +"!";


        String output = "test json  : " + username;
        return output;
    }


}