package com.googlecode.mgwt.examples.showcase.client.activities;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.examples.showcase.client.ClientFactory;
import com.googlecode.mgwt.examples.showcase.client.common.ApplicationConstants;
import com.googlecode.mgwt.examples.showcase.client.model.StationData;
import com.googlecode.mgwt.examples.showcase.client.model.TrainInfo;
import com.googlecode.mgwt.examples.showcase.client.model.TrainPosition;
import com.googlecode.mgwt.examples.showcase.client.utils.XmlParser;
import com.googlecode.mgwt.examples.showcase.client.views.StationDetailsView;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class StationDetailsActivity extends MGWTAbstractActivity {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private final ClientFactory clientFactory;
    private List<StationData> listStationData = new ArrayList<StationData>();
    private Map<String, TrainPosition> listTrainPosition = new HashMap<String, TrainPosition>();

    public StationDetailsActivity(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptsOneWidget panel, final EventBus eventBus) {
        super.start(panel, eventBus);
        StationDetailsView view = clientFactory.getStationDetailsView();

        //init train information in tab panel
        String stationDesc = clientFactory.getStationUtil().getCurrentStation();
        view.setTitle(stationDesc);

        final String URIToGetTrains = ApplicationConstants.BASE_URL + "/" + ApplicationConstants.GET_STATION_DATA_BY_NAME + "?" + ApplicationConstants.STATION_DESC + "=" + stationDesc;
        logger.info("Sending HTTP GET request:" + URIToGetTrains + " to get train details from " + stationDesc + " station.");
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URIToGetTrains);
        httpGetForTrainsOfCurrentStation(builder);

        view.getTabpanel().addSelectionHandler(new SelectionHandler<Integer>() {
            @Override
            public void onSelection(SelectionEvent<Integer> integerSelectionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
                int tabId = integerSelectionEvent.getSelectedItem();
                System.out.println("tab selected" + tabId);
            }
        });

        panel.setWidget(view);

    }

    private void httpGetForAllTrains(RequestBuilder builder) {
        try {
            builder.setTimeoutMillis(5000);
            Request response = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    // Couldn't connect to server (could be timeout, SOP violation, etc.)
                    logger.severe("Can't connect to server" + exception.getMessage());
                }

                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {
                        String responseText = response.getText();
                        XmlParser.parseTrainPositionsXml(responseText, listTrainPosition);
                        List<TrainInfo> trainsRelatedToCurrentStation = getRunningTrainsForCurrentStation(listStationData, listTrainPosition);

                        //create a list of LatLng to mark it onto the map
                        clientFactory.getStationDetailsView().setOverraysOnMap(trainsRelatedToCurrentStation);
                    } else {
                        // Handle the error.  Can get the status text from response.getStatusText()
                        logger.severe("HTTP error code:" + response.getStatusCode() + "," + response.getStatusText());
                    }
                }
            });
        } catch (RequestException e) {
            logger.severe("Exception thrown while requesting to server" + e.getMessage());
        }
    }

    private void httpGetForTrainsOfCurrentStation(RequestBuilder builder) {
        try {
            builder.setTimeoutMillis(5000);
            Request response = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    // Couldn't connect to server (could be timeout, SOP violation, etc.)
                    logger.severe("Can't connect to server" + exception.getMessage());
                }

                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {
                        String responseText = response.getText();
                        XmlParser.parseStationDataXml(responseText, listStationData);
                        clientFactory.getStationDetailsView().setTrainList(listStationData);

                        //Since GWT doesn't support synchronoss call, I need to send another HTTP GET request from here

                        final String URIToGetTrainsForCurrentStation = ApplicationConstants.BASE_URL + "/" + ApplicationConstants.GET_CURRENT_TRAIN_XML;
                        logger.info("Sending HTTP GET request:" + URIToGetTrainsForCurrentStation + " to get current trains.");
                        RequestBuilder builder2 = new RequestBuilder(RequestBuilder.GET, URIToGetTrainsForCurrentStation);
                        httpGetForAllTrains(builder2);

                    } else {
                        // Handle the error.  Can get the status text from response.getStatusText()
                        logger.severe("HTTP error code:" + response.getStatusCode() + "," + response.getStatusText());
                    }
                }
            });
        } catch (RequestException e) {
            logger.severe("Exception thrown while requesting to server" + e.getMessage());
        }
    }

    private List<TrainInfo> getRunningTrainsForCurrentStation(List<StationData> listStationData, Map<String, TrainPosition> listTrainPosition) {
        List<TrainInfo> result = new ArrayList<TrainInfo>();

        for (StationData current : listStationData) {
            TrainPosition trainPosition = listTrainPosition.get(current.getTrainCode());
            if (trainPosition != null) {
                TrainInfo trainInfo = new TrainInfo();
                trainInfo.setTrainPosition(trainPosition);
                trainInfo.setDestination(current.getDestination());
                trainInfo.setDirection(current.getDirection());
                trainInfo.setDueIn(current.getDueIn());
                result.add(trainInfo);
            }
        }
        return result;
    }

}
