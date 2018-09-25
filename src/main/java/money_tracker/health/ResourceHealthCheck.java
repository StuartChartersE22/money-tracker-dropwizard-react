package money_tracker.health;

import money_tracker.resources.TransactionResource;
import com.codahale.metrics.health.HealthCheck;

public class ResourceHealthCheck extends HealthCheck {
    private final TransactionResource transactionResource;

    public ResourceHealthCheck(TransactionResource eventResource) {
        this.transactionResource = eventResource;
    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy() ;
    }
}


