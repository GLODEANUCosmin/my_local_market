-- Table: public.Products

-- DROP TABLE IF EXISTS public."Products";

CREATE TABLE IF NOT EXISTS public."Products"
(
    "ProductID" integer NOT NULL DEFAULT nextval('"Products_ProductID_seq"'::regclass),
    "ProviderID" integer,
    "Price" double precision,
    "Amount" integer,
    "StandID" integer,
    "Name" text COLLATE pg_catalog."default",
    "Type" text COLLATE pg_catalog."default",
    CONSTRAINT "Products_pkey" PRIMARY KEY ("ProductID"),
    CONSTRAINT "Products_Providers_ID" FOREIGN KEY ("ProviderID")
        REFERENCES public."Providers" ("UserID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "Products_Stands_FK" FOREIGN KEY ("StandID")
        REFERENCES public."Stands" ("StandID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Products"
    OWNER to postgres;
-- Index: fki_Products_Providers_ID

-- DROP INDEX IF EXISTS public."fki_Products_Providers_ID";

CREATE INDEX IF NOT EXISTS "fki_Products_Providers_ID"
    ON public."Products" USING btree
    ("ProviderID" ASC NULLS LAST)
    TABLESPACE pg_default;
-- Index: fki_Products_Stands_FK

-- DROP INDEX IF EXISTS public."fki_Products_Stands_FK";

CREATE INDEX IF NOT EXISTS "fki_Products_Stands_FK"
    ON public."Products" USING btree
    ("StandID" ASC NULLS LAST)
    TABLESPACE pg_default;