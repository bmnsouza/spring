CREATE TABLE public.autor
(
    codigo integer NOT NULL,
    primeiro_nome character varying(20) COLLATE pg_catalog."default" NOT NULL,
    inicial_meio_nome character varying(1) COLLATE pg_catalog."default" NULL,
    ultimo_nome character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_128 PRIMARY KEY (codigo)
)

TABLESPACE pg_default;

ALTER TABLE public.autor
    OWNER to postgres;


CREATE TABLE public.editora
(
    nome character varying(50) COLLATE pg_catalog."default" NOT NULL,
    codigo integer NOT NULL,
    CONSTRAINT pk_54 PRIMARY KEY (codigo)
)

TABLESPACE pg_default;

ALTER TABLE public.editora
    OWNER to postgres;


CREATE TABLE public.usuario
(
    matricula integer NOT NULL,
    nome character varying(50) COLLATE pg_catalog."default" NOT NULL,
    cpf character varying(11) COLLATE pg_catalog."default" NOT NULL,
    endereco character varying(255) COLLATE pg_catalog."default" NOT NULL,
    telefone character varying(9) COLLATE pg_catalog."default" NOT NULL,
    renda numeric(12,2),
    CONSTRAINT pk_27 PRIMARY KEY (matricula)
)

TABLESPACE pg_default;

ALTER TABLE public.usuario
    OWNER to postgres;


CREATE TABLE public.emprestimo
(
    numero integer NOT NULL,
    mat_usuario integer NOT NULL,
    data date NOT NULL,
    CONSTRAINT pk_87 PRIMARY KEY (numero),
    CONSTRAINT fk_88 FOREIGN KEY (mat_usuario)
        REFERENCES public.usuario (matricula) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.emprestimo
    OWNER to postgres;

CREATE INDEX fkidx_90
    ON public.emprestimo USING btree
    (mat_usuario ASC NULLS LAST)
    TABLESPACE pg_default;
	
	
CREATE TABLE public.local
(
    codigo integer NOT NULL,
    nome character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_17 PRIMARY KEY (codigo)
)

TABLESPACE pg_default;

ALTER TABLE public.local
    OWNER to postgres;


CREATE TABLE public.livro
(
    titulo character varying(255) COLLATE pg_catalog."default" NOT NULL,
    subtitulo character varying(255) COLLATE pg_catalog."default" NOT NULL,
    num_paginas integer NOT NULL,
    edicao integer NOT NULL,
    volume integer NOT NULL,
    cod_editora integer NOT NULL,
    cod_local integer NOT NULL,
    isbn character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_8 PRIMARY KEY (isbn),
    CONSTRAINT fk_79 FOREIGN KEY (cod_editora)
        REFERENCES public.editora (codigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_82 FOREIGN KEY (cod_local)
        REFERENCES public.local (codigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.livro
    OWNER to postgres;

CREATE INDEX fkidx_81
    ON public.livro USING btree
    (cod_editora ASC NULLS LAST)
    TABLESPACE pg_default;

CREATE INDEX fkidx_84
    ON public.livro USING btree
    (cod_local ASC NULLS LAST)
    TABLESPACE pg_default;

CREATE TABLE public.livro_autor
(
    cod_autor integer NOT NULL,
    isbn_livro character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_136 PRIMARY KEY (cod_autor, isbn_livro),
    CONSTRAINT fk_133 FOREIGN KEY (cod_autor)
        REFERENCES public.autor (codigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_145 FOREIGN KEY (isbn_livro)
        REFERENCES public.livro (isbn) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.livro_autor
    OWNER to postgres;

CREATE INDEX fkidx_135
    ON public.livro_autor USING btree
    (cod_autor ASC NULLS LAST)
    TABLESPACE pg_default;

CREATE INDEX fkidx_147
    ON public.livro_autor USING btree
    (isbn_livro COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;


CREATE TABLE public.emprestimo_livro
(
    num_emprestimo integer NOT NULL,
    isbn_livro character varying(20) COLLATE pg_catalog."default" NOT NULL,
    data_prev_dev date NOT NULL,
    data_dev date NOT NULL,
    valor_multa numeric(12,2),
    CONSTRAINT pk_116 PRIMARY KEY (num_emprestimo, isbn_livro),
    CONSTRAINT fk_113 FOREIGN KEY (num_emprestimo)
        REFERENCES public.emprestimo (numero) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_120 FOREIGN KEY (isbn_livro)
        REFERENCES public.livro (isbn) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.emprestimo_livro
    OWNER to postgres;

CREATE INDEX fkidx_115
    ON public.emprestimo_livro USING btree
    (num_emprestimo ASC NULLS LAST)
    TABLESPACE pg_default;

CREATE INDEX fkidx_122
    ON public.emprestimo_livro USING btree
    (isbn_livro COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;
