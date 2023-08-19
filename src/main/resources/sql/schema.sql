DROP TABLE IF EXISTS result;
CREATE TABLE result (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    client_id VARCHAR(255),
    period DATE,
    suspicious_value BIGINT,
    customer_mean FLOAT,
    CONSTRAINT PK_result PRIMARY KEY (id)
);