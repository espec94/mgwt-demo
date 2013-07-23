package com.googlecode.mgwt.examples.showcase.client.utils;

import com.googlecode.mgwt.examples.showcase.client.model.Station;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StationUtil {

    private static Map<String, Station> mapOfStations = new TreeMap<String, Station>();

    private static String allStationsXml;

    private String allTrainsXml;

    public StationUtil() {
    }

    public static void setAllStationXml(String allStationsXml) {
        if (StationUtil.allStationsXml == null) {
            StationUtil.allStationsXml = allStationsXml;
            XmlParser.parseMessage(allStationsXml, mapOfStations);
        }
    }

    public List<Station> getAllStation() {
        List<Station> listStation = new ArrayList<Station>();

        for (Map.Entry<String, Station> entry : mapOfStations.entrySet()) {
            listStation.add(entry.getValue());
        }

        return listStation;
    }

    public void setTrainDetailsOfCurrentStation(String allTrainsXml){
        this.allTrainsXml = allTrainsXml;
    }

    public String getTrainDetailsOfCurrentStation(){
        return allTrainsXml;
    }
}
