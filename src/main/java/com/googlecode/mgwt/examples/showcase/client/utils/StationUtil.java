package com.googlecode.mgwt.examples.showcase.client.utils;

import com.googlecode.mgwt.examples.showcase.client.model.Station;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StationUtil {

    private static Map<String, Station> mapOfStations = new TreeMap<String, Station>();

    private static String allStationsXml;

    private static List<Station> listStation = new ArrayList<Station>();

    private String currentStation;

    public StationUtil() {
    }

    public static void setAllStationXml(String allStationsXml) {
        if (StationUtil.allStationsXml == null) {
            StationUtil.allStationsXml = allStationsXml;
            XmlParser.parseAllStationXml(allStationsXml, mapOfStations);
            for (Map.Entry<String, Station> entry : mapOfStations.entrySet()) {
                listStation.add(entry.getValue());
            }
        }
    }

    public List<Station> getAllStation() {
       return listStation;
    }

    public void setCurrentStation(String currentStation){
        this.currentStation = currentStation;
    }

    public String getCurrentStation(){
        return currentStation;
    }
}
