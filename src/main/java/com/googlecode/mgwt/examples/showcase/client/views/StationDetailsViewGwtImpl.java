package com.googlecode.mgwt.examples.showcase.client.views;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.tabbar.BookmarkTabBarButton;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabPanel;


public class StationDetailsViewGwtImpl implements StationDetailsView {

    private LayoutPanel main;

    private TabPanel tabPanel;

    private HTML trainListLabel;

    public StationDetailsViewGwtImpl() {
        main = new LayoutPanel();
        tabPanel = new TabPanel();
        trainListLabel = new HTML("Initial page");
        tabPanel.add(new BookmarkTabBarButton(), trainListLabel);
        // tabPanel.add(new ContactsTabBarButton(), new Label("Contacts"));
        // tabPanel.add(new DownloadsTabBarButton(), new Label("Downloads"));
        // tabPanel.add(new FavoritesTabBarButton(), new Label("Favorites"));
        // tabPanel.add(new FeaturedTabBarButton(), new Label("Featured"));


//        Maps.loadMapsApi("", "2", false, new Runnable() {
//            @Override
//            public void run() {
//                //To change body of implemented methods use File | Settings | File Templates.
//                LatLng SatuMareCity = LatLng.newInstance(47.792091, 22.885189);
//                final MapWidget map = new MapWidget(SatuMareCity, 2);
//                map.setSize("100%", "100%");
//                map.addControl(new SmallMapControl());
//
//                SimplePanel simplePanel = new SimplePanel();
//                simplePanel.add(map);
//                tabPanel.add(new HistoryTabBarButton(), simplePanel);
//            }
//        });

//        tabPanel.add(new HistoryTabBarButton(), new Label("History"));
        // tabPanel.add(new MoreTabBarButton(), new Label("More"));
        // tabPanel.add(new MostRecentTabBarButton(), new Label("Most Recent"));
        // tabPanel.add(new MostViewedTabBarButton(), new Label("Most Viewed"));
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
}
