package money_tracker;

import money_tracker.core.repos.PersonRepo;
import money_tracker.core.repos.TagRepo;
import money_tracker.core.repos.TransactionRepo;
import money_tracker.core.repos.TripRepo;
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

        DateFormat trackerDateFormat = new SimpleDateFormat(configuration.getDateFormat());
        environment.getObjectMapper().setDateFormat(trackerDateFormat);

        TransactionRepo transactionRepo = new TransactionRepo();
        TripRepo tripRepo = new TripRepo();
        TagRepo tagRepo = new TagRepo();
        PersonRepo personRepo = new PersonRepo();

        TransactionResource transactionResource = new TransactionResource(transactionRepo);
        TripResource tripResource = new TripResource(tripRepo);
        TagResource tagResource = new TagResource(tagRepo);
        PersonResource personResource = new PersonResource(personRepo);

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
