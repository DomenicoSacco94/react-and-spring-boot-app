INSERT INTO "chat_schema"."message" (id,sender_name,text,send_date)
VALUES ('29e0f5a1-77ee-45c4-9bc1-13ab2998444a','John Doe','This is a test message',CURRENT_TIMESTAMP) ON CONFLICT DO NOTHING;

INSERT INTO "chat_schema"."message" (id,sender_name,text,send_date)
VALUES ('3c25e249-7e3a-4b7e-ad7b-d36deea8ee5a','Mina Marinetti','This is a test message send after 5 minutes',CURRENT_TIMESTAMP + (5 ||' minutes')::interval) ON CONFLICT DO NOTHING;

INSERT INTO "chat_schema"."message" (id,sender_name,text,send_date)
VALUES ('21fe105b-1b9a-4ba4-8300-09a90e5ced18','Silvio Chiesa','This is a test message sent after 10 years',CURRENT_TIMESTAMP + (10 ||' years')::interval) ON CONFLICT DO NOTHING;