package ir.mapsa.secondspringproject;

import org.springframework.web.bind.annotation.*;

@RestController
public class FirstController {

    @GetMapping("/hello/{from}")
    public Response sayHelloWorld(@PathVariable("from") int from) {
        Response response = new Response();
        response.setMessage("Hello world from " + from);
        return response;
    }

    @PostMapping("/hello")
    public Response sayHelloWorld(@RequestBody Request request) {
        Response response = new Response();
        response.setMessage("Hello world from " + request.getFrom());
        return response;
    }
}
