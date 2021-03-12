package sk.kosickaakademia.kolesarova;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PrezidentController {
    String prezident1="Cyprus, CYP, Nikos Anastasiadis";
    String prezident2="Estónsko, EST, Kersti Kaljulaidová";
    String prezident3="Fínsko, FIN, Sauli Niinistö";
    String prezident4="Chorvátsko, HRV, Kolinda Grabarová Kitarovičová";
    String prezident5="Island, ISL, Guđni Thorlacius Jóhannesson";
    String prezident6="Litva, LTU, Dalia Grybauskaite";
    String prezident7="Malta, MLT, Marie Louise Coleiro Preca";
    String prezident8="Rusko, RUS, Vladimír Putin";
    String prezident9="Slovensko, SVK, Zuzana Čaputová";
    String prezident10="Švajčiarsko, CHE, Ueli Maurer";
    List<String> list=new ArrayList<>();

    public PrezidentController(){
        list.add(prezident1);
        list.add(prezident2);
        list.add(prezident3);
        list.add(prezident4);
        list.add(prezident5);
        list.add(prezident6);
        list.add(prezident7);
        list.add(prezident8);
        list.add(prezident9);
        list.add(prezident10);
    }

    @GetMapping("/prezidents")
    public ResponseEntity<String> getAllPrezident(){
        JSONObject jsonObject=new JSONObject();
        JSONArray jsonArray=new JSONArray();
        for(String s: list)
            jsonArray.add(s);
        jsonObject.put("prezidents",jsonArray);
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(jsonObject.toJSONString());
    }

    @GetMapping("/prezident/{CODE}")
    public ResponseEntity<String> getOnePrezident(@PathVariable String CODE){
        JSONObject jsonObject = new JSONObject();
        int status;
      //  jsonObject.put("prezident", list.);
        status = 200;

        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(jsonObject.toJSONString());
    }
}


