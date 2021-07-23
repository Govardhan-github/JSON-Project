package com.app;

import java.io.IOException;

import java.util.ArrayList;

import java.util.List;

import org.json.simple.JSONArray;

import org.json.simple.JSONObject;

import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.ObjectWriter;

import com.model.InventoryResult;

import com.model.Pulse;

import com.model.Rice;

import com.model.Wheat;

public class InventoryManagement {

	public static void main(String[] args) throws IOException, ParseException {

		// Declaration and Initializing the Objects

		ObjectMapper mapper = new ObjectMapper();

		ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();

		List<InventoryResult> list = new ArrayList<>();

		// Get the JSON Object from JSON File

		JSONObject inventory = FileOperation.getJSONObjectFromJSONFile(args[0]);

		// Calculate Rice inventory result and add to list

		JSONArray riceInventory = getInventoryByType(inventory, "rice");

		InventoryResult riceInventoryResult = getRiceInventoryResult(riceInventory);

		list.add(riceInventoryResult);

		// Calculate Pulse inventory result and add to list

		JSONArray pulseInventory = getInventoryByType(inventory, "pulses");

		InventoryResult pulseInventoryResult = getPulseInventoryResult(pulseInventory);

		list.add(pulseInventoryResult);

		// Calculate Wheat inventory result and add to list

		JSONArray wheatInventory = (JSONArray) inventory.get("wheats");

		InventoryResult wheatInventoryResult = getWheatInventoryResult(wheatInventory);

		list.add(wheatInventoryResult);

		// Print Inventory Result list into console

		System.out.println(writer.writeValueAsString(list));

		// Write Inventory Result to json file

		String json = writer.writeValueAsString(list);

		FileOperation.writeJSONStringIntoFile(args[1], json);

	}

	private static JSONArray getInventoryByType(JSONObject inventory, String type) {

		return (JSONArray) inventory.get(type);

	}

	private static InventoryResult getRiceInventoryResult(JSONArray riceInventory) throws IOException {

		InventoryResult riceInventoryResult = new InventoryResult();

		riceInventoryResult.setItem("Rice");

		for (int i = 0; i < riceInventory.size(); i++)

		{

			JSONObject riceDetails = (JSONObject) riceInventory.get(i);

			Rice rice = (Rice) InventoryFactory.getInstance("Rice", riceDetails.toJSONString());

			riceInventoryResult.setTotalWeight(riceInventoryResult.getTotalWeight() + rice.getWeight());

			riceInventoryResult
					.setTotalPrice(riceInventoryResult.getTotalPrice() + (rice.getWeight() * rice.getPricePerKg()));

		}

		return riceInventoryResult;

	}

	private static InventoryResult getPulseInventoryResult(JSONArray pulseInventory) throws IOException {

		InventoryResult pulseInventoryResult = new InventoryResult();

		pulseInventoryResult.setItem("Pulse");

		for (int i = 0; i < pulseInventory.size(); i++)

		{

			JSONObject pulseDetails = (JSONObject) pulseInventory.get(i);

			Pulse pulse = (Pulse) InventoryFactory.getInstance("Pulse", pulseDetails.toJSONString());

			pulseInventoryResult.setTotalWeight(pulseInventoryResult.getTotalWeight() + pulse.getWeight());

			pulseInventoryResult
					.setTotalPrice(pulseInventoryResult.getTotalPrice() + (pulse.getWeight() * pulse.getPricePerKg()));

		}

		return pulseInventoryResult;

	}

	private static InventoryResult getWheatInventoryResult(JSONArray wheatInventory) throws IOException {

		InventoryResult wheatInventoryResult = new InventoryResult();

		wheatInventoryResult.setItem("Wheat");

		for (int i = 0; i < wheatInventory.size(); i++)

		{

			JSONObject wheatDetails = (JSONObject) wheatInventory.get(i);

			Wheat wheat = (Wheat) InventoryFactory.getInstance("Wheat", wheatDetails.toJSONString());

			wheatInventoryResult.setTotalWeight(wheatInventoryResult.getTotalWeight() + wheat.getWeight());

			wheatInventoryResult
					.setTotalPrice(wheatInventoryResult.getTotalPrice() + (wheat.getWeight() * wheat.getPricePerKg()));

		}

		return wheatInventoryResult;

	}

}
