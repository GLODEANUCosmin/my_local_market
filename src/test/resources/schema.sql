
-- Table: public.Users

-- DROP TABLE IF EXISTS public."Users";
CREATE TABLE IF NOT EXISTS public."Users"
(
    "UserID" integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "Name" text ,
    "Wallet" double precision NOT NULL DEFAULT 0,
    "Password" text
);


-- Table: public.Markets

-- DROP TABLE IF EXISTS public."Markets";

CREATE TABLE IF NOT EXISTS public."Markets"
(
    "MarketID" integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "Name" text NOT NULL,
    "Description" character varying(200),
    CONSTRAINT "Markets_pkey" PRIMARY KEY ("MarketID")
);



-- Table: public.Providers

-- DROP TABLE IF EXISTS public."Providers";

CREATE TABLE IF NOT EXISTS public."Providers"
(
    "UserID" integer NOT NULL,
    "CodCUI" text ,
    "Rating" integer DEFAULT 0,
    CONSTRAINT "Providers_pkey" PRIMARY KEY ("UserID"),
    CONSTRAINT "Providers_Users_FK" FOREIGN KEY ("UserID")
        REFERENCES public."Users" ("UserID") 
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

-- Index: fki_Providers_Users_FK

-- DROP INDEX IF EXISTS public."fki_Providers_Users_FK";

CREATE INDEX IF NOT EXISTS "fki_Providers_Users_FK"
    ON public."Providers" USING btree
    ("UserID" ASC NULLS LAST);


-- Table: public.Stands

-- DROP TABLE IF EXISTS public."Stands";

CREATE TABLE IF NOT EXISTS public."Stands"
(
    "StandID" integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "ProviderID" integer,
    "MarketID" integer,
    "Name" text,
    CONSTRAINT "Stands_pkey" PRIMARY KEY ("StandID"),
    CONSTRAINT "Stand_Provider_FK" FOREIGN KEY ("ProviderID")
        REFERENCES public."Providers" ("UserID") 
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT "Stands_Market_FK" FOREIGN KEY ("MarketID")
        REFERENCES public."Markets" ("MarketID") 
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

-- Index: fki_Stand_Provider_FK

-- DROP INDEX IF EXISTS public."fki_Stand_Provider_FK";

CREATE INDEX IF NOT EXISTS "fki_Stand_Provider_FK"
    ON public."Stands" USING btree
    ("ProviderID" ASC NULLS LAST);
-- Index: fki_Stands_Market_FK

-- DROP INDEX IF EXISTS public."fki_Stands_Market_FK";

CREATE INDEX IF NOT EXISTS "fki_Stands_Market_FK"
    ON public."Stands" USING btree
    ("MarketID" ASC NULLS LAST);



-- Table: public.Products

-- DROP TABLE IF EXISTS public."Products";

CREATE TABLE IF NOT EXISTS public."Products"
(
    "ProductID" integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "ProviderID" integer,
    "Price" double precision,
    "Amount" integer,
    "StandID" integer,
    "Name" text ,
    "Type" text ,
    CONSTRAINT "Products_pkey" PRIMARY KEY ("ProductID"),
    CONSTRAINT "Products_Providers_ID" FOREIGN KEY ("ProviderID")
        REFERENCES public."Providers" ("UserID") 
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT "Products_Stands_FK" FOREIGN KEY ("StandID")
        REFERENCES public."Stands" ("StandID") 
        ON UPDATE CASCADE
        ON DELETE SET NULL
);

-- Index: fki_Products_Providers_ID

-- DROP INDEX IF EXISTS public."fki_Products_Providers_ID";

CREATE INDEX IF NOT EXISTS "fki_Products_Providers_ID"
    ON public."Products" USING btree
    ("ProviderID" ASC NULLS LAST);
-- Index: fki_Products_Stands_FK

-- DROP INDEX IF EXISTS public."fki_Products_Stands_FK";

CREATE INDEX IF NOT EXISTS "fki_Products_Stands_FK"
    ON public."Products" USING btree
    ("StandID" ASC NULLS LAST);