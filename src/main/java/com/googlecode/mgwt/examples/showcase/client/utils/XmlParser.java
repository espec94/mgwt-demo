package com.googlecode.mgwt.examples.showcase.client.utils;

import com.google.gwt.xml.client.*;
import com.googlecode.mgwt.examples.showcase.client.model.Station;
import com.googlecode.mgwt.examples.showcase.client.model.StationData;
import com.googlecode.mgwt.examples.showcase.client.model.TrainPosition;

import java.util.List;
import java.util.Map;

public class XmlParser {

    public static void parseAllStationXml(String messageXml, Map stationList) {
        try {
            // parse the XML document into a DOM
            Document messageDom = XMLParser.parse(messageXml);

            // find each station in an attribute of the <objStation> tag
            NodeList nodeList = messageDom.getElementsByTagName("objStation");

            //initialize Station object
            for (int i = 0; i < nodeList.getLength(); i++) {
                final Element node = (Element) nodeList.item(i);
                String stationDesc = messageDom.getElementsByTagName("StationDesc").item(i).getFirstChild().getNodeValue();
                //String stationAlias = messageDom.getElementsByTagName("StationAlias").item(i).getFirstChild().getNodeValue();
                String stationAlias = "";
                String stationLatitude = messageDom.getElementsByTagName("StationLatitude").item(i).getFirstChild().getNodeValue();
                String stationLongitude = messageDom.getElementsByTagName("StationLongitude").item(i).getFirstChild().getNodeValue();
                String stationCode = messageDom.getElementsByTagName("StationCode").item(i).getFirstChild().getNodeValue();
                int stationId = Integer.parseInt(messageDom.getElementsByTagName("StationId").item(i).getFirstChild().getNodeValue());

                Station station = new Station(stationId, stationCode, stationLongitude, stationLatitude, stationDesc, stationAlias);
                stationList.put(stationDesc, station);
            }

        } catch (DOMException e) {
        }
    }

    public static void parseStationDataXml(String messageXml, List stationDataList) {
        try {
            // parse the XML document into a DOM
            Document messageDom = XMLParser.parse(messageXml);

            // find each station in an attribute of the <objStation> tag
            NodeList nodeList = messageDom.getElementsByTagName("objStationData");

            //initialize Station object
            for (int i = 0; i < nodeList.getLength(); i++) {
                final Element node = (Element) nodeList.item(i);

                String trainCode = messageDom.getElementsByTagName("Traincode").item(i).getFirstChild().getNodeValue();
                String stationFullName = messageDom.getElementsByTagName("Stationfullname").item(i).getFirstChild().getNodeValue();
                String stationCode = messageDom.getElementsByTagName("Stationcode").item(i).getFirstChild().getNodeValue();
                String queryTime = messageDom.getElementsByTagName("Querytime").item(i).getFirstChild().getNodeValue();
                String destination = messageDom.getElementsByTagName("Destination").item(i).getFirstChild().getNodeValue();
                String direction = messageDom.getElementsByTagName("Direction").item(i).getFirstChild().getNodeValue();
                String trainType = messageDom.getElementsByTagName("Traintype").item(i).getFirstChild().getNodeValue();
                int dueIn = Integer.parseInt(messageDom.getElementsByTagName("Duein").item(i).getFirstChild().getNodeValue());
                String expectedArrival = messageDom.getElementsByTagName("Exparrival").item(i).getFirstChild().getNodeValue();
                String scheduledArrival = messageDom.getElementsByTagName("Scharrival").item(i).getFirstChild().getNodeValue();
                String lastLocation = messageDom.getElementsByTagName("Lastlocation").item(i).getFirstChild().getNodeValue();

                stationDataList.add(new StationData(trainCode, stationFullName, stationCode, queryTime, destination, direction, trainType, dueIn, expectedArrival, scheduledArrival, lastLocation));
            }

        } catch (DOMException e) {
        }

    }

    public static void parseTrainPositionsXml(String responseText, List<TrainPosition> listTrainPosition) {
        try {
            // parse the XML document into a DOM
            Document messageDom = XMLParser.parse(responseText);

            // find each station in an attribute of the <objStation> tag
            NodeList nodeList = messageDom.getElementsByTagName("objTrainPositions");

            //initialize Station object
            for (int i = 0; i < nodeList.getLength(); i++) {
                final Element node = (Element) nodeList.item(i);

                String trainStatus = messageDom.getElementsByTagName("TrainStatus").item(i).getFirstChild().getNodeValue();
                double trainLatitude = Double.parseDouble(messageDom.getElementsByTagName("TrainLatitude").item(i).getFirstChild().getNodeValue());
                double trainLongitude = Double.parseDouble(messageDom.getElementsByTagName("TrainLongitude").item(i).getFirstChild().getNodeValue());
                String trainCode = messageDom.getElementsByTagName("TrainCode").item(i).getFirstChild().getNodeValue();

                listTrainPosition.add(new TrainPosition(trainStatus, trainLatitude, trainLongitude, trainCode));
            }

        } catch (DOMException e) {
        }

    }
}
