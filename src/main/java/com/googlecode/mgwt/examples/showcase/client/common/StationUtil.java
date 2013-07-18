package com.googlecode.mgwt.examples.showcase.client.common;

import com.googlecode.mgwt.examples.showcase.client.model.Station;
import com.googlecode.mgwt.examples.showcase.client.request.ServerRequestUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StationUtil {

    private Map<String, Station> mapOfStations = new TreeMap<String, Station>();

    private XmlParser parser = new XmlParser();

    private String allStationsXml;

    public StationUtil() {
        initAllStationInformation();
    }

    private void initAllStationInformation() {
//        System.out.println("initializing StationUtil");
//        ServerRequestUtil.GetRequest(ApplicationConstants.BASE_URL + "/" + ApplicationConstants.ALL_STATIONS);
    }

    public List<Station> getAllStation() {
        parser.parseMessage(allStationsXml, mapOfStations);

        List<Station> listStation = new ArrayList<Station>();

        for (Map.Entry<String, Station> entry : mapOfStations.entrySet()) {
            listStation.add(entry.getValue());
        }

        return listStation;
    }
}
