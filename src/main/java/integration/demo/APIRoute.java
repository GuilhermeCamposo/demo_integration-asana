package integration.demo;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class APIRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        restConfiguration().bindingMode(RestBindingMode.off);

        rest("/asanaAdapter")
        .consumes("application/json")
        .post()
            .route()
            .to("bean:asanaBean");


    }

  

}
