CREATE TABLE "users" (
  "id" bigint PRIMARY KEY,
  "username" varchar UNIQUE,
  "email" varchar UNIQUE,
  "password_hash" varchar,
  "first_name" varchar,
  "last_name" varchar,
  "role" "enum(USER,WORKER,ADMIN)",
  "is_active" boolean,
  "created_at" timestamp
);

CREATE TABLE "report_statuses" (
  "id" bigint PRIMARY KEY,
  "name" varchar UNIQUE,
  "label" varchar,
  "display_order" int
);

CREATE TABLE "reports" (
  "id" bigint PRIMARY KEY,
  "user_id" bigint,
  "assigned_worker_id" bigint,
  "status_id" bigint,
  "title" varchar,
  "description" text,
  "category" "enum(ROAD_DAMAGE,ILLEGAL_DUMPING,BROKEN_POWER_LINE,GRAFFITI,OTHER)",
  "latitude" decimal(9,6),
  "longitude" decimal(9,6),
  "is_anonymous" boolean,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "report_images" (
  "id" bigint PRIMARY KEY,
  "report_id" bigint,
  "image_path" varchar,
  "uploaded_at" timestamp
);

CREATE TABLE "report_status_history" (
  "id" bigint PRIMARY KEY,
  "report_id" bigint,
  "status_id" bigint,
  "changed_by" bigint,
  "changed_at" timestamp,
  "comment" text
);

ALTER TABLE "reports" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "reports" ADD FOREIGN KEY ("assigned_worker_id") REFERENCES "users" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "reports" ADD FOREIGN KEY ("status_id") REFERENCES "report_statuses" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "report_images" ADD FOREIGN KEY ("report_id") REFERENCES "reports" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "report_status_history" ADD FOREIGN KEY ("report_id") REFERENCES "reports" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "report_status_history" ADD FOREIGN KEY ("status_id") REFERENCES "report_statuses" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "report_status_history" ADD FOREIGN KEY ("changed_by") REFERENCES "users" ("id") DEFERRABLE INITIALLY IMMEDIATE;
