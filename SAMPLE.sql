CREATE TABLE animal (
    id BIGSERIAL PRIMARY KEY,
    foo VARCHAR(40) NOT NULL,
    bar INT DEFAULT 0,
    bars INT[],
    foos INT ARRAY
);
