package Services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import java.lang.Object;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import models.Location;

@Service
public class CovidDataService {
	
	private static String VIRUS_DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	private List<Location> allStats = new ArrayList<>();
	
	public List<Location> getAllStats() {
		return allStats;
	}
	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException
	{
		 List<Location> newStats = new ArrayList<>();
		HttpClient client =HttpClient.newHttpClient();
		HttpRequest request= HttpRequest.newBuilder()
			.uri(URI.create(VIRUS_DATA_URL))
			.build();
		HttpResponse<String> httpResponse=client.send(request, HttpResponse.BodyHandlers.ofString());
		
		
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
		    
		    Location locationStats = new Location();
		    locationStats.setState(record.get("Province/State"));
		    locationStats.setCountry(record.get("Country/Region"));
		   int latestCases= Integer.parseInt(record.get(record.size()-1));
		   int prevDayCases= Integer.parseInt(record.get(record.size()-2));
		    locationStats.setLatestTotalCases(latestCases);
		    locationStats.setDiffFromPrevDay(latestCases-prevDayCases);
		    newStats.add(locationStats);
		    
		    
		   
		}
		this.allStats=newStats;
		   
		
	}

}