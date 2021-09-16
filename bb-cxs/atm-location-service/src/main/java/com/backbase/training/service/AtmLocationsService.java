package com.backbase.training.service;

import com.backbase.location.rest.spec.v1.locations.Location;
import com.backbase.location.rest.spec.v1.locations.LocationsGetResponseBody;
import com.backbase.training.mappers.AtmLocationMapper;
import com.openbankproject.api.model.InlineResponse200;
import com.openbankproject.api.spec.ATMApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.client.RestClientException;

/**
 * Service responsible for integration with ATMApi
 */
@Service
public class AtmLocationsService {

    private static final Logger LOG = LoggerFactory.getLogger(AtmLocationsService.class);

    private ATMApi atmApi;

    private AtmLocationMapper atmLocationMapper;

    @Autowired
    public AtmLocationsService(ATMApi atmApi, AtmLocationMapper atmLocationMapper) {
        this.atmApi = atmApi;
        this.atmLocationMapper = atmLocationMapper;
    }


    public LocationsGetResponseBody getATMLocations() {
        try {
            final InlineResponse200 inlineResponse200 = atmApi.atmsGet(null, null);

            // Get all ATMs for all brands and maps to Location list
            final List<Location> locations = inlineResponse200.getData().stream()
                    .flatMap(a -> a.getBrand().stream())
                    .flatMap(b -> b.getATM().stream())
                    .map(atmLocationMapper::mapAtmToLocation).collect(Collectors.toList());

            // Wrap the list of Locations into a response wrapper
            return new LocationsGetResponseBody().withLocations(locations);
        } catch (RestClientException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}