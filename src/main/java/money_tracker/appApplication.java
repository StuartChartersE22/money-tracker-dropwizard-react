package money_tracker;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class appApplication extends Application<appConfiguration> {

    public static void main(final String[] args) throws Exception {
        new appApplication().run(args);
    }

    @Override
    public String getName() {
        return "app";
    }

    @Override
    public void initialize(final Bootstrap<appConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final appConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
