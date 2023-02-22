package ir.mapsa.secondspringproject;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class FirstController {

    @GetMapping("/hello/{from}")
    public Response sayHelloWorld(@PathVariable("from") int from) {
        Response response = new Response();
        response.setMessage("Hello world from " + from);
        return response;
    }
}
