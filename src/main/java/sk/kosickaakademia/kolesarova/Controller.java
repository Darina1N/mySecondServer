package sk.kosickaakademia.kolesarova;

import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class Controller {

    @RequestMapping(path = "/hello")
    public String getHello(){
        return "Hello. How are you? Do you like java?";
    }

    @RequestMapping(path="/time")
    public String currentTime(){
        return new Date().toString();
    }

    @RequestMapping(path="/hello/sk")
    public String getHelloSk(){
        return "<h1>Ahoj, ako sa mas ty java expert?</h1>";
    }

    @RequestMapping(path="/hi")
    public String getHi(){
        return "<h2>Hi user. What are you doing?</h2>";
    }

    @RequestMapping(path="/hi/{username}")
    public String getHiWithName(@PathVariable String username){//ak očakávam určitú hodnotu na vstupe
        return "<h2>Hi "+username+" What are you doing? </h2>";
    }

    @RequestMapping(path="/data", method = RequestMethod.POST)
    public String getHiTest(@RequestBody String username){
        return "<h2>This is a test page.</h2>"+username;
    }

    @GetMapping(path="/spinach")
    public String getSpinach(){
        return "Coz takhle dat si spenat? Jasne som predsa Pepek Namornik.";
    }
}
