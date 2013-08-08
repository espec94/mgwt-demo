package com.googlecode.mgwt.examples.showcase.client.mappers;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.googlecode.mgwt.examples.showcase.client.places.AboutPlace.AboutPlaceTokenizer;
import com.googlecode.mgwt.examples.showcase.client.places.HomePlace.HomePlaceTokenizer;
import com.googlecode.mgwt.examples.showcase.client.places.StationDetailsPlace;
import com.googlecode.mgwt.examples.showcase.client.places.StationSummaryPlace.TestPlaceTokenizer;


@WithTokenizers({HomePlaceTokenizer.class, AboutPlaceTokenizer.class,
        TestPlaceTokenizer.class, StationDetailsPlace.StationDetailsPlaceTokenizer.class})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}
