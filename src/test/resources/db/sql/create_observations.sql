CREATE OR REPLACE TABLE OBSERVATIONS (
  id         BIGINT NOT NULL PRIMARY KEY,
  LOADER_ID BIGINT NOT NULL, 
  SITE_ID BIGINT NOT NULL,
  SAMPLE_ID BIGINT NOT NULL,
  FILE_NAME VARCHAR(50) DEFAULT NULL NOT NULL,
  ROW_NUMBER VARCHAR(10) NOT NULL, 
  IGSN VARCHAR(25) NOT NULL,
  OCCUR_TIME TIMESTAMP,
  PARAM VARCHAR(100) NOT NULL,
  DEPTH_FROM DECIMAL NOT NULL,
  DEPTH_TO DECIMAL NOT NULL,
  RESULT VARCHAR(1000) NOT NULL,
  OBSERVER VARCHAR(50) NOT NULL,
  "TYPE" VARCHAR(100) NOT NULL,
);