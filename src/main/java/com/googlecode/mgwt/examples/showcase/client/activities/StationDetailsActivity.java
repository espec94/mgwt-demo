package com.googlecode.mgwt.examples.showcase.client.activities;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.examples.showcase.client.ClientFactory;
import com.googlecode.mgwt.examples.showcase.client.views.StationDetailsView;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;

public class StationDetailsActivity extends MGWTAbstractActivity {

    private final ClientFactory clientFactory;

    public StationDetailsActivity(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptsOneWidget panel, final EventBus eventBus) {
        StationDetailsView view = clientFactory.getStationDetailsView();
        //init train information in tabpanel
        view.getTrainListLabel().setText(clientFactory.getStationUtil().getTrainDetailsOfCurrentStation());


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
