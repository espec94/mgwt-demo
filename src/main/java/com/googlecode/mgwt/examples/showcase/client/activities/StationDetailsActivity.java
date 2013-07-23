package com.googlecode.mgwt.examples.showcase.client.activities;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.examples.showcase.client.ClientFactory;
import com.googlecode.mgwt.examples.showcase.client.views.StationDetailsView;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;

public class StationDetailsActivity extends MGWTAbstractActivity {

    private final ClientFactory clientFactory;

    public StationDetailsActivity (ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptsOneWidget panel, final EventBus eventBus) {
        StationDetailsView view = clientFactory.getStationDetailsView();

//        addHandlerRegistration(view.getBackButton().addTapHandler(new TapHandler() {
//
//            @Override
//            public void onTap(TapEvent event) {
//                ActionEvent.fire(eventBus, ActionNames.ANIMATION_END);
//
//            }
//        }));

        panel.setWidget(view);


    }

}
