ALTER TABLE "users"
ALTER COLUMN profession TYPE varchar(20)
USING profession::text;