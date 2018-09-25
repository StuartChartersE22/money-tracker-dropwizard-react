package money_tracker;

import money_tracker.core.DummyEventRepository;
import money_tracker.core.EventRepository;
import money_tracker.health.ResourceHealthCheck;
import money_tracker.resources.EventResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class TrackerApplication extends Application<TrackerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new TrackerApplication().run(args);
    }

    @Override
    public void run(TrackerConfiguration configuration, Environment environment) {

        DateFormat eventDateFormat = new SimpleDateFormat(configuration.getDateFormat());
        environment.getObjectMapper().setDateFormat(eventDateFormat);

        EventRepository repository = new DummyEventRepository();
        EventResource eventResource = new EventResource(repository);
        environment.jersey().register(eventResource);

        final ResourceHealthCheck resourceHealthCheck = new ResourceHealthCheck(eventResource);

        environment.healthChecks().register("ResourceHealthCheck", resourceHealthCheck);
    }

    @Override
    public void initialize(Bootstrap<TrackerConfiguration> bootstrap) {
    }

}
