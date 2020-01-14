--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: Categorie; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public."Categorie" AS ENUM (
    'magliette',
    'maglioni',
    'felpe',
    'giacche',
    'cappotti',
    'pantaloni',
    'jeans',
    'camicie',
    'intimo',
    'accessori',
    'pantaloncini',
    'gonne',
    'top'
);


ALTER TYPE public."Categorie" OWNER TO postgres;

--
-- Name: Colori; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public."Colori" AS ENUM (
    'nero',
    'bianco',
    'rosso',
    'blu',
    'verde',
    'grigio'
);


ALTER TYPE public."Colori" OWNER TO postgres;

--
-- Name: Marche; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public."Marche" AS ENUM (
    'adidas',
    'alcott',
    'bershka',
    'gucci',
    'h&m',
    'nike',
    'piazzaItalia',
    'versace',
    'napapijiri'
);


ALTER TYPE public."Marche" OWNER TO postgres;

--
-- Name: Sessi; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public."Sessi" AS ENUM (
    'M',
    'F',
    'U'
);


ALTER TYPE public."Sessi" OWNER TO postgres;

--
-- Name: Taglie; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public."Taglie" AS ENUM (
    'xs',
    's',
    'm',
    'l',
    'xl',
    'xxl'
);


ALTER TYPE public."Taglie" OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: Articolo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Articolo" (
    codice character varying(25) NOT NULL,
    nome character varying(30) NOT NULL,
    descrizione text NOT NULL,
    marca public."Marche" NOT NULL,
    taglia public."Taglie" NOT NULL,
    colore public."Colori" NOT NULL,
    "prezzoDiListino" double precision NOT NULL,
    saldo integer NOT NULL,
    "quantità" integer NOT NULL,
    categoria public."Categorie" NOT NULL,
    "pathImmagine" text NOT NULL,
    sesso public."Sessi" NOT NULL,
    CONSTRAINT valoreprezzodilistino CHECK (("prezzoDiListino" >= (0)::double precision)),
    CONSTRAINT "valorequantità" CHECK (("quantità" > 0)),
    CONSTRAINT valoresaldo CHECK (((saldo >= 0) AND (saldo <= 100)))
);


ALTER TABLE public."Articolo" OWNER TO postgres;

--
-- Name: ComposizioneTransazione; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."ComposizioneTransazione" (
    "codiceArticolo" character varying(25) NOT NULL,
    "codiceTransazione" character varying(25) NOT NULL,
    "quantità" integer NOT NULL,
    valore double precision NOT NULL
);


ALTER TABLE public."ComposizioneTransazione" OWNER TO postgres;

--
-- Name: Fornitore; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Fornitore" (
    "partitaIva" character varying(11) NOT NULL,
    nome character varying(30) NOT NULL,
    via character varying(30) NOT NULL,
    "numeroCivico" character varying(5) NOT NULL,
    cap character varying(5) NOT NULL,
    "numeroDiTelefono" character varying(10)
);


ALTER TABLE public."Fornitore" OWNER TO postgres;

--
-- Name: Transazione; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Transazione" (
    "codiceTransazione" character varying(25) NOT NULL,
    data date NOT NULL,
    "valoreTotale" double precision NOT NULL,
    "partitaIva" character varying(11)
);


ALTER TABLE public."Transazione" OWNER TO postgres;

--
-- Data for Name: Articolo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Articolo" (codice, nome, descrizione, marca, taglia, colore, "prezzoDiListino", saldo, "quantità", categoria, "pathImmagine", sesso) FROM stdin;
\.


--
-- Data for Name: ComposizioneTransazione; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."ComposizioneTransazione" ("codiceArticolo", "codiceTransazione", "quantità", valore) FROM stdin;
\.


--
-- Data for Name: Fornitore; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Fornitore" ("partitaIva", nome, via, "numeroCivico", cap, "numeroDiTelefono") FROM stdin;
\.


--
-- Data for Name: Transazione; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Transazione" ("codiceTransazione", data, "valoreTotale", "partitaIva") FROM stdin;
\.


--
-- Name: Articolo pkArticolo; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Articolo"
    ADD CONSTRAINT "pkArticolo" PRIMARY KEY (codice);


--
-- Name: ComposizioneTransazione pkComposizioneTransazione; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."ComposizioneTransazione"
    ADD CONSTRAINT "pkComposizioneTransazione" PRIMARY KEY ("codiceArticolo", "codiceTransazione");


--
-- Name: Fornitore pkFornitore; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Fornitore"
    ADD CONSTRAINT "pkFornitore" PRIMARY KEY ("partitaIva");


--
-- Name: Transazione pkTransazione; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Transazione"
    ADD CONSTRAINT "pkTransazione" PRIMARY KEY ("codiceTransazione");


--
-- Name: Articolo u1Articolo; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Articolo"
    ADD CONSTRAINT "u1Articolo" UNIQUE ("pathImmagine");


--
-- Name: ComposizioneTransazione fk1ComposizioneTransazione; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."ComposizioneTransazione"
    ADD CONSTRAINT "fk1ComposizioneTransazione" FOREIGN KEY ("codiceArticolo") REFERENCES public."Articolo"(codice) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: Transazione fk1Transazione; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Transazione"
    ADD CONSTRAINT "fk1Transazione" FOREIGN KEY ("partitaIva") REFERENCES public."Fornitore"("partitaIva") ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: ComposizioneTransazione fk2ComposizioneTransazione; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."ComposizioneTransazione"
    ADD CONSTRAINT "fk2ComposizioneTransazione" FOREIGN KEY ("codiceTransazione") REFERENCES public."Transazione"("codiceTransazione") ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

