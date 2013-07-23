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
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.tabbar.BookmarkTabBarButton;
import com.googlecode.mgwt.ui.client.widget.tabbar.HistoryTabBarButton;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabPanel;


public class StationDetailsViewGwtImpl implements StationDetailsView {

    private LayoutPanel main;

    private TabPanel tabPanel;

    private HTML trainListLabel;

    private SimplePanel simplePanel;

    public StationDetailsViewGwtImpl() {
        main = new LayoutPanel();
        tabPanel = new TabPanel();
        trainListLabel = new HTML("Initial page");
        simplePanel = new SimplePanel();
        tabPanel.add(new BookmarkTabBarButton(), trainListLabel);
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
                LatLng SatuMareCity = LatLng.newInstance(47.792091, 22.885189);

                final MapWidget map = new MapWidget(SatuMareCity, 2);
                map.setSize("100%", "100%");
                // Add some controls for the zoom level
                map.addControl(new LargeMapControl());

                // Add a marker
                map.addOverlay(new Marker(SatuMareCity));

                // Add an info window to highlight a point of interest
                map.getInfoWindow().open(map.getCenter(),
                        new InfoWindowContent("The River Somes is here in Satu Mare"));

                final DockLayoutPanel dock = new DockLayoutPanel(Style.Unit.PX);
                dock.addNorth(map, 500);

                // Add the map to the HTML host page
                simplePanel.add(dock);
            }
        });

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
    public HTML getTrainListLabel() {
        return trainListLabel;
    }

    @Override
    public SimplePanel getSimplePanel() {
        return simplePanel;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
