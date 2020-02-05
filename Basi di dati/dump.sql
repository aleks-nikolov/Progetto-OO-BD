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
    'Magliette',
    'Maglioni',
    'Felpe',
    'Giacche',
    'Cappotti',
    'Pantaloni',
    'Jeans',
    'Camicie',
    'Intimo',
    'Accessori',
    'Pantaloncini',
    'Gonne'
);


ALTER TYPE public."Categorie" OWNER TO postgres;

--
-- Name: Colori; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public."Colori" AS ENUM (
    'Nero',
    'Bianco',
    'Rosso',
    'Blu',
    'Verde',
    'Grigio'
);


ALTER TYPE public."Colori" OWNER TO postgres;

--
-- Name: Marche; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public."Marche" AS ENUM (
    'Adidas',
    'Nike',
    'H&M',
    'PiazzaItalia',
    'Alcott',
    'Bershka',
    'Versace',
    'Guppi',
    'Napapijiri'
);


ALTER TYPE public."Marche" OWNER TO postgres;

--
-- Name: Taglie; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public."Taglie" AS ENUM (
    'XS',
    'S',
    'M',
    'L',
    'XL',
    'XXL'
);


ALTER TYPE public."Taglie" OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: Articolo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Articolo" (
    "SKU" character(20) NOT NULL,
    "Taglia" public."Taglie" NOT NULL,
    "Colore" public."Colori" NOT NULL,
    "Quantità" integer NOT NULL,
    "PathImmagine" character varying(200) NOT NULL,
    "CodiceABarre" character(11) NOT NULL,
    CONSTRAINT "valorequantitàArticolo" CHECK (("Quantità" >= 0))
);


ALTER TABLE public."Articolo" OWNER TO postgres;

--
-- Name: ComposizioneTransazione; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."ComposizioneTransazione" (
    "SKU" character(20) NOT NULL,
    "CodiceTransazione" character(20) NOT NULL,
    "Quantità" integer NOT NULL,
    "Saldo" integer DEFAULT 0 NOT NULL,
    "Valore" double precision,
    CONSTRAINT "valorequantitàCT" CHECK (("Quantità" > 0))
);


ALTER TABLE public."ComposizioneTransazione" OWNER TO postgres;

--
-- Name: DescrittoreArticolo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."DescrittoreArticolo" (
    "CodiceABarre" character(11) NOT NULL,
    "Marca" public."Marche" NOT NULL,
    "Categoria" public."Categorie" NOT NULL,
    "Sesso" "char" NOT NULL,
    "PrezzoDiListino" double precision NOT NULL,
    "Nome" character varying(25) NOT NULL,
    "Descrizione" character varying(100) NOT NULL,
    CONSTRAINT valoreprezzodilistino CHECK (("PrezzoDiListino" >= (0)::double precision)),
    CONSTRAINT valoresesso CHECK ((("Sesso" = 'M'::"char") OR ("Sesso" = 'F'::"char") OR ("Sesso" = 'U'::"char")))
);


ALTER TABLE public."DescrittoreArticolo" OWNER TO postgres;

--
-- Name: Fornitore; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Fornitore" (
    "PartitaIva" character(11) NOT NULL,
    "Nome" character varying(50) NOT NULL,
    "Via" character varying(30) NOT NULL,
    "NumeroCivico" character varying(10) NOT NULL,
    "CAP" character varying(5) NOT NULL,
    "NumeroDiTelefono" character(10)
);


ALTER TABLE public."Fornitore" OWNER TO postgres;

--
-- Name: Transazione; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Transazione" (
    "CodiceTransazione" character(20) NOT NULL,
    "Data" date NOT NULL,
    "ValoreTotale" double precision,
    "PartitaIva" character(11)
);


ALTER TABLE public."Transazione" OWNER TO postgres;

--
-- Data for Name: Articolo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Articolo" ("SKU", "Taglia", "Colore", "Quantità", "PathImmagine", "CodiceABarre") FROM stdin;
\.


--
-- Data for Name: ComposizioneTransazione; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."ComposizioneTransazione" ("SKU", "CodiceTransazione", "Quantità", "Saldo", "Valore") FROM stdin;
\.


--
-- Data for Name: DescrittoreArticolo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."DescrittoreArticolo" ("CodiceABarre", "Marca", "Categoria", "Sesso", "PrezzoDiListino", "Nome", "Descrizione") FROM stdin;
\.


--
-- Data for Name: Fornitore; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Fornitore" ("PartitaIva", "Nome", "Via", "NumeroCivico", "CAP", "NumeroDiTelefono") FROM stdin;
\.


--
-- Data for Name: Transazione; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Transazione" ("CodiceTransazione", "Data", "ValoreTotale", "PartitaIva") FROM stdin;
\.


--
-- Name: Articolo pkArticolo; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Articolo"
    ADD CONSTRAINT "pkArticolo" PRIMARY KEY ("SKU");


--
-- Name: ComposizioneTransazione pkComposizioneTransazione; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."ComposizioneTransazione"
    ADD CONSTRAINT "pkComposizioneTransazione" PRIMARY KEY ("SKU", "CodiceTransazione");


--
-- Name: DescrittoreArticolo pkDescrittoreArticolo; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."DescrittoreArticolo"
    ADD CONSTRAINT "pkDescrittoreArticolo" PRIMARY KEY ("CodiceABarre");


--
-- Name: Fornitore pkFornitore; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Fornitore"
    ADD CONSTRAINT "pkFornitore" PRIMARY KEY ("PartitaIva");


--
-- Name: Transazione pkTransazione; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Transazione"
    ADD CONSTRAINT "pkTransazione" PRIMARY KEY ("CodiceTransazione");


--
-- Name: Fornitore uNumeroDiTelefono; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Fornitore"
    ADD CONSTRAINT "uNumeroDiTelefono" UNIQUE ("NumeroDiTelefono");


--
-- Name: ComposizioneTransazione fk1ComposizioneTransazione; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."ComposizioneTransazione"
    ADD CONSTRAINT "fk1ComposizioneTransazione" FOREIGN KEY ("SKU") REFERENCES public."Articolo"("SKU") ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: ComposizioneTransazione fk2ComposizioneTransazione; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."ComposizioneTransazione"
    ADD CONSTRAINT "fk2ComposizioneTransazione" FOREIGN KEY ("CodiceTransazione") REFERENCES public."Transazione"("CodiceTransazione") ON UPDATE CASCADE;


--
-- Name: Articolo fkArticolo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Articolo"
    ADD CONSTRAINT "fkArticolo" FOREIGN KEY ("CodiceABarre") REFERENCES public."DescrittoreArticolo"("CodiceABarre") ON UPDATE CASCADE;


--
-- Name: Transazione fkTransazione; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Transazione"
    ADD CONSTRAINT "fkTransazione" FOREIGN KEY ("PartitaIva") REFERENCES public."Fornitore"("PartitaIva") ON UPDATE CASCADE;


--
-- PostgreSQL database dump complete
--

