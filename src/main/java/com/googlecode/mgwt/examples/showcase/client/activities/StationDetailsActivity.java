package com.googlecode.mgwt.examples.showcase.client.activities;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.examples.showcase.client.ClientFactory;
import com.googlecode.mgwt.examples.showcase.client.common.ApplicationConstants;
import com.googlecode.mgwt.examples.showcase.client.views.StationDetailsView;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;

public class StationDetailsActivity extends MGWTAbstractActivity {

    private final ClientFactory clientFactory;

    public StationDetailsActivity(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptsOneWidget panel, final EventBus eventBus) {
        super.start(panel, eventBus);
        StationDetailsView view = clientFactory.getStationDetailsView();

        //init train information in tabpanel
        String stationDesc = clientFactory.getStationUtil().getCurrentStation();
        String requestURL = ApplicationConstants.BASE_URL + "/" + ApplicationConstants.GET_STATION_DATA_BY_NAME + "?" + ApplicationConstants.STATION_DESC + "=" + stationDesc;

        System.out.println("Sending HTTP request:" + requestURL + " to get train details from current station.");
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, requestURL);

        try {
            builder.setTimeoutMillis(5000);
            Request response = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    // Couldn't connect to server (could be timeout, SOP violation, etc.)
                }

                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {
                        String responseText = response.getText();
                        // System.out.println("all train information: " + responseText);
                        clientFactory.getStationDetailsView().setTrainList(responseText);
                        //clientFactory.getStationUtil().setTrainDetailsOfCurrentStation(responseText);

                    } else {
                        // Handle the error.  Can get the status text from response.getStatusText()
                        System.out.println("HTTP error code:" + response.getStatusCode() + "," + response.getStatusText());
                    }
                }
            });
        } catch (RequestException e) {
            // Couldn't connect to server
        }
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

}
