package sk.kosickaakademia.kolesarova;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MathController {

    /*@PostMapping(path="/add")
    public String addTwoNumbers(@RequestBody String input){ //metoda ktorá pracuje priamo so stringom
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(input);
            int a=Integer.parseInt(String.valueOf(jsonObject.get("a")));
            int b=Integer.parseInt(String.valueOf(jsonObject.get("b")));
            int result=a+b;
            return "{\"result\":"+result+"}"; //vypíšem presne formát ako chcem aby mi to vrátilo
        }catch(ParseException parseException){
            parseException.printStackTrace();
        }
        return null;
    }*/

    @PostMapping(path = "/add")
    public ResponseEntity<String> addTwoNumbers(@RequestBody String input) {//už nepracuje len so  stringom ale s triedov Respnse Entity
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(input);
            int a = Integer.parseInt(String.valueOf(jsonObject.get("a")));
            int b = Integer.parseInt(String.valueOf(jsonObject.get("b")));
            int result = a + b;
            JSONObject res = new JSONObject();
            res.put("result", result);
//chcem aby mi vypísalo aj status že je všetko OK (200) a že je to json
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(res.toJSONString());
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        } catch (NumberFormatException exception) {//potrebujem ošetriť aj vypisovanie chýb, aby užívateľ vedel, v čom je problém
            //takto potrebujem ošetriť každú chybu ktorá v tom konkrétnom prípade môže nastať
            JSONObject object = new JSONObject();
            object.put("ERROR", "Chybne zadané parametre. Oprav request.");
            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(object.toJSONString());
        }
        return null;
    }
@GetMapping(path="/mul")
public ResponseEntity<String> multiply(@RequestParam(value = "a")int value1, @RequestParam(value = "b")int value2){
        int result = (value1*value2);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("result",result);
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(jsonObject.toJSONString());
}
}
