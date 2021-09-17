-- Table: public.insumo

-- DROP TABLE public.insumo;

CREATE TABLE public.insumo
(
    idInsumo integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    dsInsumo character varying(50) NOT NULL,
    nrQtdBase integer NOT NULL,
    dsMedida character varying(20) NOT NULL,
    vlPreco double precision NOT NULL,
    dtCadastro date NOT NULL,
    dtAtualizacao date,
    CONSTRAINT insumo_pkey PRIMARY KEY (idInsumo)
)

TABLESPACE pg_default;

ALTER TABLE public.insumo
    OWNER to postgres;