CREATE TABLE time_card (
  sender_id BIGINT NOT NULL,
  date VARCHAR(255) NOT NULL,
  start_time VARCHAR(255) NOT NULL,
  end_time VARCHAR(255),
  PRIMARY KEY (sender_id, date)
);