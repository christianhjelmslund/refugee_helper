package com.example.workflow.location;

        import com.example.workflow.transportation.models.RouteModel;
        import com.example.workflow.transportation.models.RouteRequestModel;
        import com.example.workflow.transportation.services.RoutingService;
        import org.camunda.bpm.engine.delegate.DelegateExecution;
        import org.camunda.bpm.engine.delegate.JavaDelegate;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Component;

        import static com.example.workflow.CheckUserInfo.REFUGEE_APP;

@Component
public class StartLocationTracking implements JavaDelegate {

    @Autowired
    private RoutingService routingService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String destination_city = (String) execution.getVariable("SELECTED_CITY");
        String current_address = (String) execution.getVariable("CURRENT_ADDRESS");
        String mode = (String) execution.getVariable("SELECTED_TRANS_MODE");
        String departure = (String) execution.getVariable("DEPARTURE");

        RouteRequestModel request = new RouteRequestModel();
        request.setDestination(destination_city);
        request.setOrigin(current_address);
        request.setMode(mode);
        request.setDeparture(departure);
        String route = routingService.startLocationTracking(request);
        REFUGEE_APP.info("Tracking started, Route: " + route);
    }

}