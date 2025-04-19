CREATE TABLE USERS (
                       USERNAME VARCHAR(255) NOT NULL,
                       PRODUCT_URL VARCHAR(255) NOT NULL,
                       DESIRED_PRICE DECIMAL(10,2) NOT NULL,
                       FREQUENCY LONG NOT NULL,
                       LAST_RUN_TIME TIMESTAMP
);