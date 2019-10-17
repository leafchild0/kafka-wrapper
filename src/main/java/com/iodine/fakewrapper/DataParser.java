package com.iodine.fakewrapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This class should parse the data and produce results in JSON
 *
 * @author victor
 * @date 10/17/19
 */
class DataParser
{
	private String filePath;
	private List<String> headers = new ArrayList<>();

	DataParser(String filePath)
	{
		this.filePath = filePath;
	}

	private Function<String, Map<String, String>> mapToItem = (line) -> {
		String[] p = line.split(",");
		Map<String, String> data = new HashMap<>();

		for (int i = 0; i < headers.size(); i++)
		{
			data.put(headers.get(i), p[i]);
		}

		//more initialization goes here
		return data;
	};

	List<Map<String, String>> parseData()
	{
		// Parse the file and get the data
		// Assuming 1st line in file are headers, only one tab is supported
		return processDataFile();
	}

	private List<Map<String, String>> processDataFile()
	{
		List<Map<String, String>> inputList = new ArrayList<>();
		try
		{
			File inputF = new File(filePath);
			InputStream inputFS = new FileInputStream(inputF);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
			// Get the headers
			headers = Arrays.asList(br.readLine().split(","));
			// skip the header of the csv
			inputList = br.lines().map(mapToItem).collect(Collectors.toList());
			br.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return inputList;
	}

	private List<Map<String, String>> getDummyData()
	{
		List<Map<String, String>> dummy = new ArrayList<>();

		Map<String, String> dummy1 = new HashMap<>();
		dummy1.put("account_no", "D000086677");
		dummy1.put("patient_id", "P000066496");
		dummy1.put("order_service_id", "dicthp");
		dummy1.put("order_service_name", "dicthp");
		dummy1.put("observation_val1", "This patient has pretty severe sepsis and should be treated immediately");

		Map<String, String> dummy2 = new HashMap<>();
		dummy2.put("account_no", "D000086677");
		dummy2.put("patient_id", "P000066496");
		dummy2.put("order_service_id", "dicthp");
		dummy2.put("order_service_name", "dicthp");
		dummy2.put("observation_val1", "This patient has pretty severe blood decease and should be treated tomorrow");

		HashMap<String, String> dummy3 = new HashMap<>();
		dummy3.put("account_no", "D000086677");
		dummy3.put("patient_id", "P000066496");
		dummy3.put("order_service_id", "dicthp");
		dummy3.put("order_service_name", "dicthp");
		dummy3.put("observation_val1", "This patient has pretty severe HIV and should be treated immediately");

		dummy.add(dummy1);
		dummy.add(dummy2);
		dummy.add(dummy3);

		return dummy;
	}

}