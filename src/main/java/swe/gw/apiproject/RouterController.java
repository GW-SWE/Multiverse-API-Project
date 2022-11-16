package swe.gw.apiproject;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.*;

//TODO - Check this code is no longer required.

@RestController
public class RouterController {
    //return a string
    @GetMapping("")
    public String hello() {
        return "Hello RouterController!";
    }

    //return original body back
    @GetMapping("returnRequestBody")
    public String data(@RequestBody String data) {
        return data;
    }

    //param query with some maths before return
    @GetMapping("squarenumber")
    public @ResponseBody double getDouble(@RequestParam("input") double inputValue) {
        return inputValue * inputValue;
    }

    //mapping and returning only part of the initial request body
    @GetMapping("gettest")
    //returns the first key's value back as a string to the sender.
    public @ResponseBody String getTest(@RequestBody String data) {
        Gson gson = new Gson();
        Map map = gson.fromJson(data, Map.class);
        //map.get("a").getClass();
        System.out.println(map.get("a"));
        return map.get("a").toString();
    }

    //TODO - Remove this redundant code.
}
