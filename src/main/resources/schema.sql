
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