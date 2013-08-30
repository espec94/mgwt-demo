package com.googlecode.mgwt.examples.showcase.client.views;

import com.google.gwt.dom.client.Style;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.control.SmallMapControl;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.examples.showcase.client.model.StationData;
import com.googlecode.mgwt.examples.showcase.client.model.TrainInfo;
import com.googlecode.mgwt.examples.showcase.client.model.TrainPosition;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.tabbar.BookmarkTabBarButton;
import com.googlecode.mgwt.ui.client.widget.tabbar.HistoryTabBarButton;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabPanel;

import java.util.List;


public class StationDetailsViewGwtImpl implements StationDetailsView {

    private LayoutPanel main;
    private HeaderPanel headerPanel;
    private TabPanel tabPanel;
    private HTML trainListLabel;
    private MapWidget map;
    private DockLayoutPanel dock;
    private ScrollPanel scrollPanel;


    public StationDetailsViewGwtImpl() {
        main = new LayoutPanel();
        headerPanel = new HeaderPanel();
        tabPanel = new TabPanel();
        trainListLabel = new HTML("Initial page");
        dock = new DockLayoutPanel(Style.Unit.PX);
        scrollPanel = new ScrollPanel();
        scrollPanel.setShowScrollBarY(true);
        scrollPanel.add(trainListLabel);
        tabPanel.add(new BookmarkTabBarButton(), scrollPanel);
        tabPanel.add(new HistoryTabBarButton(), dock);
        main.add(headerPanel);
        main.add(tabPanel);
    }

    @Override
    public HasTapHandlers getBackButton() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public TabPanel getTabpanel() {
        return tabPanel;
    }

    @Override
    public Widget asWidget() {
        return main;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setTrainList(List<StationData> trainList) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<div><table><tr><td>");
        for (StationData current : trainList) {
            buffer.append("Destination: " + current.getDestination() + "<br>");
            buffer.append("Due: " + current.getDueIn() + "<br>");
            buffer.append("Current Time: " + current.getQueryTime() + "<br>");
            buffer.append("Last Location: " + current.getLastLocation() + "<br>");
            buffer.append("<hr>");
        }
        buffer.append("<td></tr></table></div>");
        trainListLabel.setHTML(buffer.toString());
    }

    @Override
    public void setTitle(String title) {
        headerPanel.setTitle(title);
    }

    @Override
    public void setOverraysOnMap(final List<TrainInfo> trainInfoList) {
        Maps.loadMapsApi("", "2", false, new Runnable() {

            public void run() {
                // Open a map centered on Ireland
                LatLng ireland = LatLng.newInstance(53.2734, -7.77832031);
                map = new MapWidget(ireland, 8);
                map.setSize("100%", "100%");
                // Add some controls for the zoom level
                map.addControl(new SmallMapControl());

                // Add a marker
                for (final TrainInfo current : trainInfoList) {
                    final LatLng currentLatLng = LatLng.newInstance(current.getTrainPosition().getTrainLatitude(), current.getTrainPosition().getTrainLongitude());
                    final Marker currentMarker = new Marker(currentLatLng);
                    map.addOverlay(currentMarker);

                    currentMarker.addMarkerClickHandler(new MarkerClickHandler() {
                        @Override
                        public void onClick(MarkerClickEvent markerClickEvent) {
                            map.setCenter(currentLatLng);
                            map.getInfoWindow().open(currentMarker, new InfoWindowContent("Destination: +" + current.getDestination()));
//                                    + "Direction: " + current.getDirection()
//                                    + "Due: " + current.getDueIn()));
                        }
                    });
                }

                // Add an info window to highlight Ireland location
                map.getInfoWindow().open(map.getCenter(),
                        new InfoWindowContent("Ireland is here!"));

                dock.addNorth(map, 500);
            }
        });
    }
}
