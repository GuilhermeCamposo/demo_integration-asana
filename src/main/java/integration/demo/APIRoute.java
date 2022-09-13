package integration.demo;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class APIRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        restConfiguration().bindingMode(RestBindingMode.off);

        onException(Exception.class)
        .handled(true)
        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(500))
        .log(LoggingLevel.ERROR,"${exception.message}")
        .setBody(simple("${exception.message} "));

        rest("/")

        .get("/tracing")
            .route()
                .log("trace received")
                .setBody(constant("test worked"))
                .log("message sent")
            .endRest()
        .post("/asanaAdapter")
            .consumes("application/json")
            .route()
            .log("received payload -> ${body}")
            .to("bean:asanaBean");

    }

  

}
