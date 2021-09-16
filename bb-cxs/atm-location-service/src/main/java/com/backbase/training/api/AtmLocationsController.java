
package com.backbase.training.api;

import com.backbase.location.rest.spec.v1.locations.LocationsApi;
import com.backbase.location.rest.spec.v1.locations.LocationsGetResponseBody;
import com.backbase.training.service.AtmLocationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Rest controller for the service, defines all methods listed in the RAML
 */
@RestController
public class AtmLocationsController implements LocationsApi {

    private AtmLocationsService atmLocationsService;

    @Autowired
    public AtmLocationsController(AtmLocationsService atmLocationsService) {
        this.atmLocationsService = atmLocationsService;
    }

    @Override
    public LocationsGetResponseBody getLocations(
            final HttpServletRequest httpServletRequest,
            final HttpServletResponse httpServletResponse) {
        return atmLocationsService.getATMLocations();
    }

}