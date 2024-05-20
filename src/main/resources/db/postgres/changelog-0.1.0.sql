CREATE TABLE meal (
  chat_id INT NOT NULL,
  message_id INT NOT NULL,
  date TIMESTAMP WITH TIME ZONE NOT NULL, -- Assuming offset date time can be stored in TIMESTAMP
  breakfast BOOLEAN,
  lunch BOOLEAN,
  dinner BOOLEAN,
  type VARCHAR(255),
  PRIMARY KEY (chat_id, message_id)
);