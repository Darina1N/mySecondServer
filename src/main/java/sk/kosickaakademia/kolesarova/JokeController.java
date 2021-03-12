package sk.kosickaakademia.kolesarova;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class JokeController {
    String joke1="U doktorky: Jožko nedýchaj! Ty si si prdla?";
    String joke2="Múdrosť: Nikdy nepoužívaj záchod v snoch. Je to chyták.";
    String joke3="Predseda volá zootechnikovi: Zdochol nám vôl, máme kúpiť nového alebo počkať na teba?";
    List<String> list= new ArrayList<>();

    public JokeController(){
        list.add(joke1);
        list.add(joke2);
        list.add(joke3);
    }

    @GetMapping("/jokes")
    public ResponseEntity<String> getJokes(){
        JSONObject jsonObject=new JSONObject();
        JSONArray jsonArray=new JSONArray();
        for(String s: list)
            jsonArray.add(s);
        jsonObject.put("jokes",jsonArray);
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(jsonObject.toJSONString());
    }

    @GetMapping("/joke/{id}")
    public ResponseEntity<String> getJokeById(@PathVariable Integer id) {
        JSONObject jsonObject = new JSONObject();
        int status;
        if (id < 1 || id > list.size()) {
            jsonObject.put("ERROR", "Nesprávne id");
            status = 404;
        } else {
            jsonObject.put("joke", list.get(id - 1));//id-1 lebo pole mi ide od 0
            status = 200;
        }
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(jsonObject.toJSONString());
    }

    @GetMapping("/joke")
    public ResponseEntity<String> getRandomJoke(){
        JSONObject jsonObject=new JSONObject();
        int status;
        if (list.size()==0) {
            jsonObject.put("ERROR", "Bohužiaľ sa vtipy minuli");
            status = 404;
        } else {
            Random random=new Random();
            int idNumber=random.nextInt(list.size());
            jsonObject.put("id",idNumber);
            jsonObject.put("joke", list.get(idNumber));
            status = 200;
        }
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(jsonObject.toJSONString());
    }

    @PostMapping("/joke/add")
    public ResponseEntity<String> addNewJoke(@RequestBody String input) {
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(input);
            String newJoke = String.valueOf(jsonObject.get("joke"));
            list.add(newJoke);
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(newJoke.toString());
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        } catch (NumberFormatException exception) {
            JSONObject object = new JSONObject();
            object.put("ERROR", "Bad request.");
            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(object.toJSONString());
        }
        return null;
    }
}
