package dev.gabriel.wisewallet.core.infrastructure.eventstore.configuration;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;

@Validated
@Configuration
@ConfigurationProperties(prefix = "event-store")
public class EventStoreConfiguration {
    public record SnapshottingConfiguration(
            boolean enabled,
            int nthEvent
    ) {}

    private static final SnapshottingConfiguration NO_SNAPSHOTTING = new SnapshottingConfiguration(false, 0);

    @NestedConfigurationProperty
    @Setter
    private Map<String, SnapshottingConfiguration> snapshotting = new HashMap<>();

    public SnapshottingConfiguration getSnapshotting(String aggregateType) {
        return snapshotting.getOrDefault(aggregateType, NO_SNAPSHOTTING);
    }
}
