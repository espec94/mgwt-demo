package com.googlecode.mgwt.examples.showcase.client.activities.test;

import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.examples.showcase.client.BasicCell;
import com.googlecode.mgwt.examples.showcase.client.model.Station;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.celllist.CellListWithHeader;

import java.util.List;

public class TestViewGwtImpl implements TestView {

    private LayoutPanel main;
    private HeaderButton forwardButton;
    private HeaderPanel headerPanel;
    private CellListWithHeader<Station> cellList;

    public void TestViewGwtImpl() {
        cellList = new CellListWithHeader<Station>(new BasicCell<Station>() {

            @Override
            public String getDisplayString(Station model) {
                return model.getDescription();
            }

            @Override
            public boolean canBeSelected(Station model) {
                return true;
            }
        });

        cellList.getCellList().setRound(true);

        ScrollPanel scrollPanel = new ScrollPanel();
        scrollPanel.setWidget(cellList);
        scrollPanel.setScrollingEnabledX(false);
        main.add(scrollPanel);
    }


    @Override
    public void setTitle(String title) {
        headerPanel.setTitle(title);
    }

    @Override
    public void renderItems(List<Station> stations) {
        cellList.getCellList().render(stations);
    }

    @Override
    public Widget asWidget() {
        return main;
    }
}
