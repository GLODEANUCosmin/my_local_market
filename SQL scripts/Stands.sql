-- Table: public.Stands

-- DROP TABLE IF EXISTS public."Stands";

CREATE TABLE IF NOT EXISTS public."Stands"
(
    "StandID" integer NOT NULL DEFAULT nextval('"Stands_StandID_seq"'::regclass),
    "ProviderID" integer,
    "MarketID" integer,
    "Name" text COLLATE pg_catalog."default",
    CONSTRAINT "Stands_pkey" PRIMARY KEY ("StandID"),
    CONSTRAINT "Stand_Provider_FK" FOREIGN KEY ("ProviderID")
        REFERENCES public."Providers" ("UserID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "Stands_Market_FK" FOREIGN KEY ("MarketID")
        REFERENCES public."Markets" ("MarketID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Stands"
    OWNER to postgres;
-- Index: fki_Stand_Provider_FK

-- DROP INDEX IF EXISTS public."fki_Stand_Provider_FK";

CREATE INDEX IF NOT EXISTS "fki_Stand_Provider_FK"
    ON public."Stands" USING btree
    ("ProviderID" ASC NULLS LAST)
    TABLESPACE pg_default;
-- Index: fki_Stands_Market_FK

-- DROP INDEX IF EXISTS public."fki_Stands_Market_FK";

CREATE INDEX IF NOT EXISTS "fki_Stands_Market_FK"
    ON public."Stands" USING btree
    ("MarketID" ASC NULLS LAST)
    TABLESPACE pg_default;