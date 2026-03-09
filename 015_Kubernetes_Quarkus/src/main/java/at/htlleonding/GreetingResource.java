package at.htlleonding;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")  
public class GreetingResource {  
  
    @ConfigProperty(name = "greeting.message.properties", defaultValue="(missing)")  
    String greetingMessageProperties;  
  
    @ConfigProperty(name = "greeting.message.configmap", defaultValue="(missing)")  
    String greetingMessageConfigMap;  
  
    @ConfigProperty(name = "greeting.message.secret", defaultValue="(missing)")  
    String greetingMessageSecret;  
  
    @GET  
    @Produces(MediaType.TEXT_PLAIN)  
    public String hello() {  
        return "Hello from Quarkus REST" +  
                "\nProperties: " + greetingMessageProperties +  
                "\nConfigMap: " + greetingMessageConfigMap +  
                "\nSecret: " + greetingMessageSecret;  
    }  
}
