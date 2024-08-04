-- Table: public.Markets

-- DROP TABLE IF EXISTS public."Markets";

CREATE TABLE IF NOT EXISTS public."Markets"
(
    "MarketID" integer NOT NULL DEFAULT nextval('"Markets_MarketID_seq"'::regclass),
    "Name" text COLLATE pg_catalog."default" NOT NULL,
    "Description" character varying(200) COLLATE pg_catalog."default",
    CONSTRAINT "Markets_pkey" PRIMARY KEY ("MarketID")
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Markets"
    OWNER to postgres;