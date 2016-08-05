CREATE TABLE animal (
    id BIGSERIAL PRIMARY KEY,
    foo TEXT,
    bar INT DEFAULT 7,
    bars INT[],
    foos INT ARRAY
);
