package com.googlecode.mgwt.examples.showcase.client.activities.test;


import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.examples.showcase.client.model.Station;

import java.util.List;

public interface TestView extends IsWidget {

    public void setTitle(String title);

    public void renderItems(List<Station> items);

}
