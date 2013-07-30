package com.googlecode.mgwt.examples.showcase.client.views;

import com.google.gwt.dom.client.Style;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.examples.showcase.client.model.StationData;
import com.googlecode.mgwt.examples.showcase.client.model.TrainPosition;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.tabbar.BookmarkTabBarButton;
import com.googlecode.mgwt.ui.client.widget.tabbar.HistoryTabBarButton;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabPanel;

import java.util.List;


public class StationDetailsViewGwtImpl implements StationDetailsView {

    private LayoutPanel main;
    private TabPanel tabPanel;
    private HeaderPanel headerPanel;
    private HTML trainListLabel;
    private SimplePanel simplePanel;
    private MapWidget map;
    private DockLayoutPanel dock;


    public StationDetailsViewGwtImpl() {
        main = new LayoutPanel();
        headerPanel = new HeaderPanel();
        tabPanel = new TabPanel();
        trainListLabel = new HTML("Initial page");
        simplePanel = new SimplePanel();
        tabPanel.add(new BookmarkTabBarButton(), trainListLabel);
        dock = new DockLayoutPanel(Style.Unit.PX);

        // tabPanel.add(new ContactsTabBarButton(), new Label("Contacts"));
        // tabPanel.add(new DownloadsTabBarButton(), new Label("Downloads"));
        // tabPanel.add(new FavoritesTabBarButton(), new Label("Favorites"));
        // tabPanel.add(new FeaturedTabBarButton(), new Label("Featured"));


        tabPanel.add(new HistoryTabBarButton(), simplePanel);
        // tabPanel.add(new MoreTabBarButton(), new Label("More"));
        // tabPanel.add(new MostRecentTabBarButton(), new Label("Most Recent"));
        // tabPanel.add(new MostViewedTabBarButton(), new Label("Most Viewed"));

        Maps.loadMapsApi("", "2", false, new Runnable() {

            public void run() {
                // Open a map centered on Satu Mare, Romania
                LatLng DublinCity = LatLng.newInstance(53.23727683624094, -6.21826171875);

                map = new MapWidget(DublinCity, 7);
                map.setSize("100%", "100%");
                // Add some controls for the zoom level
                map.addControl(new LargeMapControl());

                // Add a marker
                map.addOverlay(new Marker(DublinCity));
                // Add an info window to highlight a point of interest
                map.getInfoWindow().open(map.getCenter(),
                        new InfoWindowContent("The River Somes is here in Satu Mare"));

                dock.addNorth(map, 500);

                // Add the map to the HTML host page
                simplePanel.add(dock);
            }
        });

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
        for (StationData current : trainList) {
            buffer.append("Destination: " + current.getDestination());
            buffer.append("Due: " + current.getDueIn());
            buffer.append("Current Time: " + current.getQueryTime());
            buffer.append("Lasy Location: " + current.getLastLocation());
        }
        trainListLabel.setText(buffer.toString());
    }

    @Override
    public void setTitle(String title) {
        headerPanel.setTitle(title);
    }

    @Override
    public void setOverraysOnMap(final List<TrainPosition> trainPositionList) {
        Maps.loadMapsApi("", "2", false, new Runnable() {
            public void run() {
                dock.remove(map);
                System.out.println("trainPositions: " + trainPositionList.toString());
                map.clearOverlays();
                for (TrainPosition current : trainPositionList) {
                    map.addOverlay(new Marker(LatLng.newInstance(current.getTrainLatitude(), current.getTrainLongitude())));
//                    map.addOverlay(new Marker(DublinCity2));
                }
                dock.add(map);
            }
        });
    }
}
