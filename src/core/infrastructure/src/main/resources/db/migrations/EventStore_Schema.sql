CREATE TABLE IF NOT EXISTS aggregates (
    id UUID PRIMARY KEY,
    version INTEGER NOT NULL,
    type TEXT NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_aggregates_type ON aggregates (type);

CREATE TABLE IF NOT EXISTS events (
    id BIGSERIAL PRIMARY KEY,
    transaction_id XID8 NOT NULL,
    aggregate_id UUID NOT NULL REFERENCES aggregates (id),
    version INTEGER NOT NULL,
    type TEXT NOT NULL,
    payload JSON NOT NULL,
    UNIQUE (aggregate_id, version)
);

CREATE INDEX IF NOT EXISTS idx_events_transaction_id ON events (transaction_id);
CREATE INDEX IF NOT EXISTS idx_events_aggregate_id ON events (aggregate_id);
CREATE INDEX IF NOT EXISTS idx_events_version ON events (version);

CREATE TABLE IF NOT EXISTS snapshots (
    aggregate_id UUID NOT NULL REFERENCES aggregates (id),
    version INTEGER NOT NULL,
    data JSON NOT NULL,
    PRIMARY KEY (aggregate_id, version)
);

CREATE INDEX IF NOT EXISTS idx_snapshots_aggregate_id ON snapshots (aggregate_id);
CREATE INDEX IF NOT EXISTS idx_snapshots_version ON snapshots (version);