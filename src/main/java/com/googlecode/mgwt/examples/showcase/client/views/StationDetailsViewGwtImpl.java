package com.googlecode.mgwt.examples.showcase.client.views;

import com.google.gwt.user.client.ui.Label;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.tabbar.BookmarkTabBarButton;
import com.googlecode.mgwt.ui.client.widget.tabbar.HistoryTabBarButton;
import com.googlecode.mgwt.ui.client.widget.tabbar.SearchTabBarButton;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabPanel;


public class StationDetailsViewGwtImpl extends DetailViewGwtImpl implements StationDetailsView {

    public StationDetailsViewGwtImpl() {
        TabPanel tabPanel = new TabPanel();

        tabPanel.add(new BookmarkTabBarButton(), new Label("Bookmark"));
        // tabPanel.add(new ContactsTabBarButton(), new Label("Contacts"));
        // tabPanel.add(new DownloadsTabBarButton(), new Label("Downloads"));
        // tabPanel.add(new FavoritesTabBarButton(), new Label("Favorites"));
        // tabPanel.add(new FeaturedTabBarButton(), new Label("Featured"));
        tabPanel.add(new HistoryTabBarButton(), new Label("History"));
        // tabPanel.add(new MoreTabBarButton(), new Label("More"));
        // tabPanel.add(new MostRecentTabBarButton(), new Label("Most Recent"));
        // tabPanel.add(new MostViewedTabBarButton(), new Label("Most Viewed"));
        tabPanel.add(new SearchTabBarButton(), new Label("Search"));

        main.add(tabPanel);
        main.remove(scrollPanel);
    }

    @Override
    public HasTapHandlers getBackButton() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
