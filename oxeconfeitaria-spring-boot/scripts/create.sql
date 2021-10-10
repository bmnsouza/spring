CREATE TABLE public.insumo
(
    idinsumo integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    dsinsumo character varying(50) COLLATE pg_catalog."default" NOT NULL,
    nrqtdbase integer NOT NULL,
    dsmedida character varying(20) COLLATE pg_catalog."default" NOT NULL,
    vlpreco double precision NOT NULL,
    dtcadastro date NOT NULL,
    dtatualizacao date,
    CONSTRAINT insumo_pkey PRIMARY KEY (idinsumo)
)

TABLESPACE pg_default;

ALTER TABLE public.insumo
    OWNER to postgres;