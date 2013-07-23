package com.googlecode.mgwt.examples.showcase.client.views;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabPanel;

public interface StationDetailsView extends IsWidget {

    public HasTapHandlers getBackButton();

    public TabPanel getTabpanel();

    public void setTrainList(String trainList);


}
