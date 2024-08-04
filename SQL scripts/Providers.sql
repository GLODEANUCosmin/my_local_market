-- Table: public.Providers

-- DROP TABLE IF EXISTS public."Providers";

CREATE TABLE IF NOT EXISTS public."Providers"
(
    "UserID" integer NOT NULL,
    "CodCUI" text COLLATE pg_catalog."default",
    "Rating" integer DEFAULT 0,
    CONSTRAINT "Providers_pkey" PRIMARY KEY ("UserID"),
    CONSTRAINT "Providers_Users_FK" FOREIGN KEY ("UserID")
        REFERENCES public."Users" ("UserID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Providers"
    OWNER to postgres;
-- Index: fki_Providers_Users_FK

-- DROP INDEX IF EXISTS public."fki_Providers_Users_FK";

CREATE INDEX IF NOT EXISTS "fki_Providers_Users_FK"
    ON public."Providers" USING btree
    ("UserID" ASC NULLS LAST)
    TABLESPACE pg_default;