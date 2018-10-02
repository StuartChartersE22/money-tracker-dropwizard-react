package money_tracker;

import money_tracker.db.Seed;
import money_tracker.health.ResourceHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import money_tracker.resources.PersonResource;
import money_tracker.resources.TagResource;
import money_tracker.resources.TransactionResource;
import money_tracker.resources.TripResource;

import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class TrackerApplication extends Application<TrackerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new TrackerApplication().run(args);
    }

    @Override
    public void run(TrackerConfiguration configuration, Environment environment) {

        Seed.seedData();

        DateFormat trackerDateFormat = new SimpleDateFormat(configuration.getDateFormat());
        environment.getObjectMapper().setDateFormat(trackerDateFormat);

        TransactionResource transactionResource = new TransactionResource();
        TripResource tripResource = new TripResource();
        TagResource tagResource = new TagResource();
        PersonResource personResource = new PersonResource();

        environment.jersey().register(transactionResource);
        environment.jersey().register(tripResource);
        environment.jersey().register(tagResource);
        environment.jersey().register(personResource);

//        final ResourceHealthCheck resourceHealthCheck = new ResourceHealthCheck(eventResource);

//        environment.healthChecks().register("ResourceHealthCheck", resourceHealthCheck);
    }

    @Override
    public void initialize(Bootstrap<TrackerConfiguration> bootstrap) {
    }

}
