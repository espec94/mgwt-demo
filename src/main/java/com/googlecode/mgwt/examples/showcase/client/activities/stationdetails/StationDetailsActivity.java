package com.googlecode.mgwt.examples.showcase.client.activities.stationdetails;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.examples.showcase.client.ClientFactory;
import com.googlecode.mgwt.examples.showcase.client.DetailView;
import com.googlecode.mgwt.examples.showcase.client.activities.animationdone.AnimationDoneView;
import com.googlecode.mgwt.examples.showcase.client.event.ActionEvent;
import com.googlecode.mgwt.examples.showcase.client.event.ActionNames;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;

/**
 * @author Daniel Kurka
 *
 */
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
