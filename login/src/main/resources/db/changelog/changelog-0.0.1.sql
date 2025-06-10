-- Table: public.k_smart_user

-- DROP TABLE IF EXISTS public.k_smart_user;

CREATE TABLE IF NOT EXISTS public.k_smart_user
(
    user_id uuid NOT NULL,
    email character varying(255) COLLATE pg_catalog."default",
    aadhaar_no character varying(255) COLLATE pg_catalog."default",
    created_at bytea,
    dob character varying(255) COLLATE pg_catalog."default",
    gender character varying(255) COLLATE pg_catalog."default",
    is_active boolean,
    is_first_login character varying(255) COLLATE pg_catalog."default",
    is_kkyc_verified boolean,
    name character varying(255) COLLATE pg_catalog."default",
    phone_number character varying(255) COLLATE pg_catalog."default",
    tenant_id integer,
    updated_at bytea,
    user_type character varying(255) COLLATE pg_catalog."default",
    whatsapp_number character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT k_smart_user_pkey PRIMARY KEY (user_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.k_smart_user
    OWNER to postgres;