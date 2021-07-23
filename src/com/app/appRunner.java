package com.app;


import java.io.FileReader;

import java.io.FileWriter;

import java.io.IOException;

import java.util.ArrayList;

import java.util.List;




import org.json.simple.JSONArray;

import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;

import org.json.simple.parser.ParseException;




import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.ObjectWriter;

import com.model.InventoryResult;
import com.model.Pulse;

//import com.model.Pulse;

import com.model.Rice;

import com.model.Wheat;




public class appRunner {




public static void main(String[] args) throws IOException, ParseException {






        JSONParser jsonparser = new JSONParser();

        FileReader reader = new FileReader(".//data/inventory.json");

        

        ObjectMapper mapper = new ObjectMapper();

        ObjectWriter writer= mapper.writerWithDefaultPrettyPrinter();

        List<InventoryResult> list = new ArrayList<>();




        // Java Object variable

        Object obj = jsonparser.parse(reader);




        // JSON Object ( after type casting)

        JSONObject inventory = (JSONObject)obj;

        JSONArray riceInventory = (JSONArray)inventory.get("rice");

        JSONArray pulseInventory = (JSONArray)inventory.get("pulses");

        JSONArray wheatInventory = (JSONArray)inventory.get("wheats");

        

        InventoryResult riceInventoryResult = new InventoryResult();

        int riceTotalWeight = 0;

        int riceTotalPrice = 0;




        for (int i = 0; i < riceInventory.size(); i++ )

        {

            JSONObject riceDetails = (JSONObject)riceInventory.get(i);

            

            Rice rice = new Rice();

            rice.setName((String)riceDetails.get("name"));

            rice.setPricePerKg((long)riceDetails.get("pricePerKg"));

            rice.setWeight((long)riceDetails.get("weight"));

            

            riceTotalWeight += rice.getWeight();

            riceTotalPrice += rice.getWeight() * rice.getPricePerKg();

            

            

        }

        

        riceInventoryResult.setItem("Rice");

        riceInventoryResult.setTotalPrice(riceTotalPrice);

        riceInventoryResult.setTotalWeight(riceTotalWeight);

        list.add(riceInventoryResult);

        

        System.out.println(riceInventoryResult);

        System.out.println(writer.writeValueAsString(riceInventoryResult));

        //writer.writeValue(new File(".//data/inventory_output.json"), riceInventoryResult);

        

        

        InventoryResult pulseInventoryResult = new InventoryResult();

        int pulseTotalWeight = 0;

        int pulseTotalPrice = 0;

        

        for (int i = 0; i < pulseInventory.size(); i++ )

        {

            JSONObject pulseDetails = (JSONObject)pulseInventory.get(i);

            

            Pulse pulse = new Pulse();

            pulse.setName((String)pulseDetails.get("name"));

            pulse.setPricePerKg((long)pulseDetails.get("pricePerKg"));

            pulse.setWeight((long)pulseDetails.get("weight"));

            

            pulseTotalWeight += pulse.getWeight();

            pulseTotalPrice += pulse.getWeight() * pulse.getPricePerKg();

            

        }

        

        pulseInventoryResult.setItem("Pulse");

        pulseInventoryResult.setTotalPrice(pulseTotalPrice);

        pulseInventoryResult.setTotalWeight(pulseTotalWeight);

        list.add(pulseInventoryResult);

        

        System.out.println(pulseInventoryResult);

       // writer.writeValue(new File(".//data/inventory_output.json"), pulseInventoryResult);

        

        InventoryResult wheatInventoryResult = new InventoryResult();

        int wheatTotalWeight = 0;

        int wheatTotalPrice = 0;

        

        for (int i = 0; i < wheatInventory.size(); i++ )

        {

            JSONObject wheatDetails = (JSONObject)wheatInventory.get(i);
            Wheat wheat = new Wheat();
            wheat.setName((String)wheatDetails.get("name"));
            wheat.setPricePerKg((long)wheatDetails.get("pricePerKg"));
            wheat.setWeight((long)wheatDetails.get("weight"));
            wheatTotalWeight += wheat.getWeight();
            wheatTotalPrice += wheat.getWeight() * wheat.getPricePerKg();
        }

        wheatInventoryResult.setItem("Wheat");
        wheatInventoryResult.setTotalPrice(wheatTotalWeight);
        wheatInventoryResult.setTotalWeight(wheatTotalPrice);
        list.add(wheatInventoryResult);

        

        FileWriter fw=new FileWriter(".//data/inventory_output.json");    

        fw.write(writer.writeValueAsString(list));    

        fw.close();    

        

        //writer.writeValue(new File(".//data/inventory_output.json"), list);

        

        System.out.println(writer.writeValueAsString(list));
}

}