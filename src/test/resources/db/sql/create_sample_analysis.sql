CREATE OR REPLACE TABLE SAMP_ANALYSIS (
  id         BIGINT NOT NULL PRIMARY KEY,
  LOADER_ID BIGINT NOT NULL, 
  SAMPLE_ID BIGINT NOT NULL,
  IGSN VARCHAR(100) NOT NULL,
  LAB_SAMP_NO VARCHAR(100),
  ANAL_DATE TIMESTAMP,
  PARAM VARCHAR(100) NOT NULL,
  UOM VARCHAR(25),
  RESULT VARCHAR(25) NOT NULL,
  ANAL_METH VARCHAR(50),
  LOR VARCHAR(10),
);