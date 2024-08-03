-- Table: public.Users

-- DROP TABLE IF EXISTS public."Users";

CREATE TABLE IF NOT EXISTS public."Users"
(
    "UserID" integer NOT NULL DEFAULT nextval('"Users_UserID_seq"'::regclass),
    "Name" text COLLATE pg_catalog."default",
    "Wallet" double precision NOT NULL DEFAULT 0,
    "Password" text COLLATE pg_catalog."default",
    CONSTRAINT "Users_pkey" PRIMARY KEY ("UserID")
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Users"
    OWNER to postgres;