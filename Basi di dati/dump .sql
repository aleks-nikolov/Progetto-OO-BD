--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.13
-- Dumped by pg_dump version 12.0

-- Started on 2020-04-22 18:54:13

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
-- TOC entry 1326 (class 1247 OID 7768095)
-- Name: Categorie; Type: TYPE; Schema: public; Owner: nwiojydu
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


ALTER TYPE public."Categorie" OWNER TO nwiojydu;

--
-- TOC entry 1329 (class 1247 OID 7768120)
-- Name: Colori; Type: TYPE; Schema: public; Owner: nwiojydu
--

CREATE TYPE public."Colori" AS ENUM (
    'Nero',
    'Bianco',
    'Rosso',
    'Blu',
    'Verde',
    'Grigio'
);


ALTER TYPE public."Colori" OWNER TO nwiojydu;

--
-- TOC entry 1332 (class 1247 OID 7768134)
-- Name: Marche; Type: TYPE; Schema: public; Owner: nwiojydu
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


ALTER TYPE public."Marche" OWNER TO nwiojydu;

--
-- TOC entry 1335 (class 1247 OID 7768154)
-- Name: Taglie; Type: TYPE; Schema: public; Owner: nwiojydu
--

CREATE TYPE public."Taglie" AS ENUM (
    'XS',
    'S',
    'M',
    'L',
    'XL',
    'XXL'
);


ALTER TYPE public."Taglie" OWNER TO nwiojydu;

--
-- TOC entry 895 (class 1255 OID 7791731)
-- Name: calcolavaloretotale(integer); Type: FUNCTION; Schema: public; Owner: nwiojydu
--

CREATE FUNCTION public.calcolavaloretotale(x integer) RETURNS void
    LANGUAGE plpgsql
    AS $$BEGIN
DECLARE
cursore cursor FOR 
SELECT *
FROM Composizionetransazione AS CT
WHERE CT.CodiceTransazione = currval('transazione_codicetransazione_seq') AND CT.SKU = x;

BEGIN
FOR riga IN cursore 
LOOP

UPDATE Transazione
SET Valoretotale = valoretotale + riga.valore
WHERE Transazione.CodiceTransazione = riga.CodiceTransazione ;

END LOOP;

END;
END;
$$;


ALTER FUNCTION public.calcolavaloretotale(x integer) OWNER TO nwiojydu;

--
-- TOC entry 898 (class 1255 OID 7789162)
-- Name: procedureforaggiornacodicetransazione(); Type: FUNCTION; Schema: public; Owner: nwiojydu
--

CREATE FUNCTION public.procedureforaggiornacodicetransazione() RETURNS trigger
    LANGUAGE plpgsql
    AS $$BEGIN
NEW.CodiceTransazione = currval('transazione_codicetransazione_seq');
RETURN NEW;
END;$$;


ALTER FUNCTION public.procedureforaggiornacodicetransazione() OWNER TO nwiojydu;

--
-- TOC entry 893 (class 1255 OID 7789622)
-- Name: procedureforaggiornaquantitaarticolo(); Type: FUNCTION; Schema: public; Owner: nwiojydu
--

CREATE FUNCTION public.procedureforaggiornaquantitaarticolo() RETURNS trigger
    LANGUAGE plpgsql
    AS $$BEGIN
DECLARE
riga Transazione%ROWTYPE ;
	BEGIN
	SELECT * INTO riga
	FROM Transazione AS T
	WHERE T.CodiceTransazione = NEW.CodiceTransazione ;
IF (riga.PartitaIva IS NULL) THEN
	UPDATE Articolo
	SET Quantita = Quantita - NEW.Quantita
	WHERE SKU = NEW.SKU ;
	ELSE 
	UPDATE Articolo
	SET Quantita = Quantita + NEW.Quantita
	WHERE SKU = NEW.SKU ;
	END IF;
	RETURN NEW;
	END;
END;
	$$;


ALTER FUNCTION public.procedureforaggiornaquantitaarticolo() OWNER TO nwiojydu;

--
-- TOC entry 894 (class 1255 OID 7789620)
-- Name: procedureforcalcolavalore(); Type: FUNCTION; Schema: public; Owner: nwiojydu
--

CREATE FUNCTION public.procedureforcalcolavalore() RETURNS trigger
    LANGUAGE plpgsql
    AS $$BEGIN
DECLARE
	riga articolicondescrittori%ROWTYPE ;
	rigaTransazione Transazione%ROWTYPE ;
	BEGIN
	SELECT * INTO riga
	FROM articolicondescrittori AS A
	WHERE A.SKU = NEW.SKU ;
	SELECT * INTO rigaTransazione
	FROM Transazione AS T
	WHERE T.CodiceTransazione = NEW.CodiceTransazione ;
	IF(rigaTransazione.PartitaIva IS NULL) THEN	
	UPDATE ComposizioneTransazione 	
	SET Valore = NEW.quantita * (riga.PrezzoDiListino -(riga.PrezzoDiListino * (NEW.Saldo / 100.0)))
	WHERE CodiceTransazione = NEW.CodiceTransazione AND sku = NEW.sku;
	ELSE
	UPDATE ComposizioneTransazione 
	SET Valore = NEW.quantita * (riga.PrezzoMagazzino -(riga.PrezzoMagazzino * (NEW.Saldo / 100.0)))
	WHERE CodiceTransazione = NEW.CodiceTransazione AND sku = NEW.sku;
	END IF;
	RETURN NEW;
	END; 
END;
	$$;


ALTER FUNCTION public.procedureforcalcolavalore() OWNER TO nwiojydu;

--
-- TOC entry 896 (class 1255 OID 7789627)
-- Name: procedureforcalcolavaloretotaleCT(); Type: FUNCTION; Schema: public; Owner: nwiojydu
--

CREATE FUNCTION public."procedureforcalcolavaloretotaleCT"() RETURNS trigger
    LANGUAGE plpgsql
    AS $$BEGIN	
PERFORM calcolavaloretotale(NEW.SKU);
RETURN NEW;
END;
	


	
$$;


ALTER FUNCTION public."procedureforcalcolavaloretotaleCT"() OWNER TO nwiojydu;

--
-- TOC entry 897 (class 1255 OID 7789534)
-- Name: procedureforvalorequantitactestremosuperiore(); Type: FUNCTION; Schema: public; Owner: nwiojydu
--

CREATE FUNCTION public.procedureforvalorequantitactestremosuperiore() RETURNS trigger
    LANGUAGE plpgsql
    AS $$BEGIN
DECLARE
riga1 articolicondescrittori%ROWTYPE ;
riga2 Transazione%ROWTYPE ;
BEGIN
SELECT * INTO riga1
FROM articolicondescrittori AS A
WHERE A.SKU = NEW.SKU ;
SELECT * INTO riga2
FROM Transazione AS T
WHERE T.CodiceTransazione = (SELECT MAX(T.CodiceTransazione)
							 FROM Transazione AS T);
IF (riga2.PartitaIva IS NULL) THEN
IF (NEW.Quantita > riga1.Quantita) THEN
RAISE EXCEPTION 'Quantità di % specificata superiore a disponibilità magazzino', riga1.nome;
END IF ;
END IF ;
RETURN NEW;
END;
END;
$$;


ALTER FUNCTION public.procedureforvalorequantitactestremosuperiore() OWNER TO nwiojydu;

SET default_tablespace = '';

--
-- TOC entry 213 (class 1259 OID 7779815)
-- Name: articolo; Type: TABLE; Schema: public; Owner: nwiojydu
--

CREATE TABLE public.articolo (
    sku integer NOT NULL,
    taglia public."Taglie" NOT NULL,
    colore public."Colori" NOT NULL,
    quantita integer NOT NULL,
    pathimmagine character varying(200) DEFAULT 'res\\images\\noimage.png'::character varying NOT NULL,
    codiceabarre character(11) NOT NULL,
    CONSTRAINT "valorequantitàarticolo" CHECK ((quantita >= 0))
);


ALTER TABLE public.articolo OWNER TO nwiojydu;

--
-- TOC entry 214 (class 1259 OID 7779835)
-- Name: descrittorearticolo; Type: TABLE; Schema: public; Owner: nwiojydu
--

CREATE TABLE public.descrittorearticolo (
    codiceabarre character(11) NOT NULL,
    marca public."Marche" NOT NULL,
    categoria public."Categorie" NOT NULL,
    sesso character(1) NOT NULL,
    prezzodilistino double precision NOT NULL,
    prezzomagazzino double precision NOT NULL,
    nome character varying(25) NOT NULL,
    descrizione character varying(100) NOT NULL,
    CONSTRAINT valoreprezzodilistino CHECK ((prezzodilistino >= (0)::double precision)),
    CONSTRAINT valoresesso CHECK (((sesso = 'M'::bpchar) OR (sesso = 'F'::bpchar) OR (sesso = 'U'::bpchar)))
);


ALTER TABLE public.descrittorearticolo OWNER TO nwiojydu;

--
-- TOC entry 219 (class 1259 OID 7787249)
-- Name: articolicondescrittori; Type: VIEW; Schema: public; Owner: nwiojydu
--

CREATE VIEW public.articolicondescrittori AS
 SELECT a.codiceabarre,
    a.sku,
    a.taglia,
    a.colore,
    a.quantita,
    a.pathimmagine,
    dt.marca,
    dt.categoria,
    dt.sesso,
    dt.prezzodilistino,
    dt.prezzomagazzino,
    dt.nome,
    dt.descrizione
   FROM (public.articolo a
     JOIN public.descrittorearticolo dt USING (codiceabarre))
  ORDER BY a.sku;


ALTER TABLE public.articolicondescrittori OWNER TO nwiojydu;

--
-- TOC entry 212 (class 1259 OID 7779813)
-- Name: articolo_sku_seq; Type: SEQUENCE; Schema: public; Owner: nwiojydu
--

CREATE SEQUENCE public.articolo_sku_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.articolo_sku_seq OWNER TO nwiojydu;

--
-- TOC entry 3589 (class 0 OID 0)
-- Dependencies: 212
-- Name: articolo_sku_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nwiojydu
--

ALTER SEQUENCE public.articolo_sku_seq OWNED BY public.articolo.sku;


--
-- TOC entry 218 (class 1259 OID 7779893)
-- Name: composizionetransazione; Type: TABLE; Schema: public; Owner: nwiojydu
--

CREATE TABLE public.composizionetransazione (
    sku integer NOT NULL,
    codicetransazione integer,
    quantita integer NOT NULL,
    saldo integer DEFAULT 0 NOT NULL,
    valore double precision,
    CONSTRAINT "valorequantitàctestremoinferiore" CHECK ((quantita >= 0)),
    CONSTRAINT valoresaldo CHECK (((saldo >= 0) AND (saldo <= 100))),
    CONSTRAINT valorevalore CHECK ((valore >= (0)::double precision))
);


ALTER TABLE public.composizionetransazione OWNER TO nwiojydu;

--
-- TOC entry 215 (class 1259 OID 7779840)
-- Name: fornitore; Type: TABLE; Schema: public; Owner: nwiojydu
--

CREATE TABLE public.fornitore (
    partitaiva character(11) NOT NULL,
    nome character varying(50) NOT NULL,
    via character varying(30) NOT NULL,
    numerocivico character varying(10) NOT NULL,
    cap character varying(5) NOT NULL,
    numeroditelefono character(10)
);


ALTER TABLE public.fornitore OWNER TO nwiojydu;

--
-- TOC entry 217 (class 1259 OID 7779881)
-- Name: transazione; Type: TABLE; Schema: public; Owner: nwiojydu
--

CREATE TABLE public.transazione (
    codicetransazione integer NOT NULL,
    data date NOT NULL,
    valoretotale double precision DEFAULT 0.0,
    partitaiva character(11),
    CONSTRAINT valorevaloretotale CHECK ((valoretotale >= (0)::double precision))
);


ALTER TABLE public.transazione OWNER TO nwiojydu;

--
-- TOC entry 223 (class 1259 OID 7788998)
-- Name: fornitori; Type: VIEW; Schema: public; Owner: nwiojydu
--

CREATE VIEW public.fornitori WITH (security_barrier='false') AS
 SELECT f.nome AS fornitore,
    f.partitaiva,
    string_agg(((t.codicetransazione)::character varying)::text, ', '::text ORDER BY t.codicetransazione) AS transazioni,
    sum(t.valoretotale) AS valoretotale
   FROM (public.fornitore f
     JOIN public.transazione t USING (partitaiva))
  GROUP BY f.nome, f.partitaiva
  ORDER BY f.nome;


ALTER TABLE public.fornitori OWNER TO nwiojydu;

--
-- TOC entry 222 (class 1259 OID 7788208)
-- Name: rifornimenti; Type: VIEW; Schema: public; Owner: nwiojydu
--

CREATE VIEW public.rifornimenti WITH (security_barrier='false') AS
 SELECT z.codicetransazione AS id,
    string_agg((((z.quantita)::text || 'x '::text) || (z.nome)::text), ', '::text ORDER BY (z.nome)::text) AS contenuto,
    f.nome AS fornitore,
    z.data,
    z.valoretotale AS costo
   FROM (((public.transazione
     JOIN public.composizionetransazione USING (codicetransazione)) y
     JOIN (public.descrittorearticolo
     JOIN public.articolo USING (codiceabarre)) x ON ((x.sku = y.sku))) z(codicetransazione, data, valoretotale, partitaiva, sku, quantita, saldo, valore, codiceabarre, marca, categoria, sesso, prezzodilistino, prezzomagazzino, nome, descrizione, sku_1, taglia, colore, quantita_1, pathimmagine)
     JOIN public.fornitore f ON ((z.partitaiva = f.partitaiva)))
  GROUP BY z.codicetransazione, z.data, z.valoretotale, f.nome
  ORDER BY z.codicetransazione;


ALTER TABLE public.rifornimenti OWNER TO nwiojydu;

--
-- TOC entry 224 (class 1259 OID 7790289)
-- Name: riga2; Type: TABLE; Schema: public; Owner: nwiojydu
--

CREATE TABLE public.riga2 (
    codicetransazione integer,
    data date,
    valoretotale double precision,
    partitaiva character(11)
);


ALTER TABLE public.riga2 OWNER TO nwiojydu;

--
-- TOC entry 216 (class 1259 OID 7779879)
-- Name: transazione_codicetransazione_seq; Type: SEQUENCE; Schema: public; Owner: nwiojydu
--

CREATE SEQUENCE public.transazione_codicetransazione_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transazione_codicetransazione_seq OWNER TO nwiojydu;

--
-- TOC entry 3590 (class 0 OID 0)
-- Dependencies: 216
-- Name: transazione_codicetransazione_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nwiojydu
--

ALTER SEQUENCE public.transazione_codicetransazione_seq OWNED BY public.transazione.codicetransazione;


--
-- TOC entry 220 (class 1259 OID 7787253)
-- Name: transazioniconcomposizioni; Type: VIEW; Schema: public; Owner: nwiojydu
--

CREATE VIEW public.transazioniconcomposizioni AS
 SELECT t.codicetransazione,
    t.data,
    t.valoretotale,
    t.partitaiva,
    ct.sku,
    ct.quantita,
    ct.saldo,
    ct.valore
   FROM (public.transazione t
     JOIN public.composizionetransazione ct USING (codicetransazione))
  ORDER BY t.codicetransazione;


ALTER TABLE public.transazioniconcomposizioni OWNER TO nwiojydu;

--
-- TOC entry 221 (class 1259 OID 7788194)
-- Name: vendite; Type: VIEW; Schema: public; Owner: nwiojydu
--

CREATE VIEW public.vendite WITH (security_barrier='false') AS
 SELECT x.codicetransazione AS id,
    string_agg((((x.quantita)::text || 'x '::text) || (y.nome)::text), ', '::text ORDER BY (y.nome)::text) AS contenuto,
    x.data,
    x.valoretotale AS costo
   FROM ((public.transazione
     JOIN public.composizionetransazione USING (codicetransazione)) x
     JOIN (public.descrittorearticolo da
     JOIN public.articolo a USING (codiceabarre)) y ON ((y.sku = x.sku)))
  WHERE (x.partitaiva IS NULL)
  GROUP BY x.codicetransazione, x.data, x.valoretotale
  ORDER BY x.codicetransazione;


ALTER TABLE public.vendite OWNER TO nwiojydu;

--
-- TOC entry 3399 (class 2604 OID 7779818)
-- Name: articolo sku; Type: DEFAULT; Schema: public; Owner: nwiojydu
--

ALTER TABLE ONLY public.articolo ALTER COLUMN sku SET DEFAULT nextval('public.articolo_sku_seq'::regclass);


--
-- TOC entry 3405 (class 2604 OID 7779884)
-- Name: transazione codicetransazione; Type: DEFAULT; Schema: public; Owner: nwiojydu
--

ALTER TABLE ONLY public.transazione ALTER COLUMN codicetransazione SET DEFAULT nextval('public.transazione_codicetransazione_seq'::regclass);


--
-- TOC entry 3555 (class 0 OID 7779815)
-- Dependencies: 213
-- Data for Name: articolo; Type: TABLE DATA; Schema: public; Owner: nwiojydu
--

COPY public.articolo (sku, taglia, colore, quantita, pathimmagine, codiceabarre) FROM stdin;
10	M	Verde	16	res\\\\images\\\\pantaloni\\\\pants_green.png	65313587624
22	L	Nero	2	res\\\\images\\\\giacche\\\\giacca.png	74839258432
23	M	Bianco	0	res\\images\\noimage.png	55555555555
24	M	Verde	0	res\\\\images\\\\giacche\\\\giacca_green.png	12312312345
2	M	Nero	95	res\\\\images\\\\magliette\\\\adidas_black.png	25674562362
3	M	Bianco	5	res\\\\images\\\\magliette\\\\adidas_white.png	25674562362
25	M	Verde	0	res\\\\images\\\\giacche\\\\giacca_green.png	12312312312
7	L	Verde	1	res\\\\images\\\\magliette\\\\nike_green.png	12345678912
5	L	Nero	16	res\\\\images\\\\magliette\\\\nike_black.png	12345678912
8	M	Nero	28	res\\\\images\\\\pantaloni\\\\pants_black.png	65313587624
6	L	Bianco	24	res\\\\images\\\\magliette\\\\nike_white.png	12345678912
4	M	Rosso	26	res\\\\images\\\\magliette\\\\adidas_red.png	25674562362
9	M	Rosso	36	res\\\\images\\\\pantaloni\\\\pants_red.png	65313587624
\.


--
-- TOC entry 3560 (class 0 OID 7779893)
-- Dependencies: 218
-- Data for Name: composizionetransazione; Type: TABLE DATA; Schema: public; Owner: nwiojydu
--

COPY public.composizionetransazione (sku, codicetransazione, quantita, saldo, valore) FROM stdin;
2	103	1	0	15
2	105	1	0	15
2	68	2	0	15
5	68	2	0	12
2	69	2	20	12
5	69	2	0	12
2	137	1	0	7.5
3	137	5	0	37.5
2	71	2	0	30
3	71	1	0	15
3	105	2	0	30
4	105	5	0	75
7	107	2	0	12
3	107	1	0	7.5
9	107	8	0	120
2	112	1	0	7.5
2	78	2	0	15
7	79	2	0	25
2	124	3	0	22.5
4	124	2	0	15
9	124	1	0	15
3	125	4	0	30
9	125	1	0	15
3	126	1	0	7.5
\.


--
-- TOC entry 3556 (class 0 OID 7779835)
-- Dependencies: 214
-- Data for Name: descrittorearticolo; Type: TABLE DATA; Schema: public; Owner: nwiojydu
--

COPY public.descrittorearticolo (codiceabarre, marca, categoria, sesso, prezzodilistino, prezzomagazzino, nome, descrizione) FROM stdin;
25674562362	Adidas	Magliette	U	15	7.5	Maglietta Adidas	Maglietta a maniche corte Adidas.
12345678912	Nike	Magliette	M	12.5	6	Maglietta Nike	Maglietta a maniche corte Nike.
65313587624	H&M	Pantaloni	M	20	15	Pantaloni H&M	Pantaloni H&M formali.
10000000000	Bershka	Accessori	F	100	20	sefunzionagodo	upupup
74839258432	Bershka	Giacche	M	40	25	Giacca	Giacca sportiva casual
55555555555	Bershka	Maglioni	F	20	10		prova
12312312345	Adidas	Giacche	M	20	10	giacca	giacca verde
12312312312	Adidas	Giacche	M	20	10	Giacca	Giacca Adidas
\.


--
-- TOC entry 3557 (class 0 OID 7779840)
-- Dependencies: 215
-- Data for Name: fornitore; Type: TABLE DATA; Schema: public; Owner: nwiojydu
--

COPY public.fornitore (partitaiva, nome, via, numerocivico, cap, numeroditelefono) FROM stdin;
92859216431	FornitoreY	via Qualsiasi	24	80127	0817643274
04962473216	FornitoreX	via Generica	10	80126	0812523764
55555555555	prova	via spiaggia	13	80070	          
12312312332	prova	via spiaggia	13	80070	3333333333
21321321322	fornitoreZ	spiaggia	13	80070	1111111111
\.


--
-- TOC entry 3561 (class 0 OID 7790289)
-- Dependencies: 224
-- Data for Name: riga2; Type: TABLE DATA; Schema: public; Owner: nwiojydu
--

COPY public.riga2 (codicetransazione, data, valoretotale, partitaiva) FROM stdin;
7	2020-02-14	\N	04962473216
\.


--
-- TOC entry 3559 (class 0 OID 7779881)
-- Dependencies: 217
-- Data for Name: transazione; Type: TABLE DATA; Schema: public; Owner: nwiojydu
--

COPY public.transazione (codicetransazione, data, valoretotale, partitaiva) FROM stdin;
78	2020-02-15	15	92859216431
79	2020-02-15	25	\N
112	2020-02-15	7.5	92859216431
122	2020-02-15	0	\N
123	2020-02-15	0	\N
124	2020-02-15	52.5	92859216431
103	2020-02-15	15	\N
68	2020-02-15	27	92859216431
69	2020-02-15	24	92859216431
125	2020-02-15	45	04962473216
71	2020-02-15	45	\N
126	2020-02-15	7.5	04962473216
127	2020-02-15	0	\N
105	2020-02-15	120	\N
128	2020-02-15	0	\N
131	2020-02-15	0	\N
132	2020-02-15	0	\N
107	2020-02-15	139.5	04962473216
108	2020-02-15	0	\N
133	2020-03-31	0	\N
134	2020-03-31	0	\N
137	2020-04-01	45	04962473216
\.


--
-- TOC entry 3591 (class 0 OID 0)
-- Dependencies: 212
-- Name: articolo_sku_seq; Type: SEQUENCE SET; Schema: public; Owner: nwiojydu
--

SELECT pg_catalog.setval('public.articolo_sku_seq', 25, true);


--
-- TOC entry 3592 (class 0 OID 0)
-- Dependencies: 216
-- Name: transazione_codicetransazione_seq; Type: SEQUENCE SET; Schema: public; Owner: nwiojydu
--

SELECT pg_catalog.setval('public.transazione_codicetransazione_seq', 137, true);


--
-- TOC entry 3413 (class 2606 OID 7779850)
-- Name: articolo pkarticolo; Type: CONSTRAINT; Schema: public; Owner: nwiojydu
--

ALTER TABLE ONLY public.articolo
    ADD CONSTRAINT pkarticolo PRIMARY KEY (sku);


--
-- TOC entry 3415 (class 2606 OID 7779854)
-- Name: descrittorearticolo pkdescrittorearticolo; Type: CONSTRAINT; Schema: public; Owner: nwiojydu
--

ALTER TABLE ONLY public.descrittorearticolo
    ADD CONSTRAINT pkdescrittorearticolo PRIMARY KEY (codiceabarre);


--
-- TOC entry 3417 (class 2606 OID 7779856)
-- Name: fornitore pkfornitore; Type: CONSTRAINT; Schema: public; Owner: nwiojydu
--

ALTER TABLE ONLY public.fornitore
    ADD CONSTRAINT pkfornitore PRIMARY KEY (partitaiva);


--
-- TOC entry 3421 (class 2606 OID 7779905)
-- Name: transazione pktransazione; Type: CONSTRAINT; Schema: public; Owner: nwiojydu
--

ALTER TABLE ONLY public.transazione
    ADD CONSTRAINT pktransazione PRIMARY KEY (codicetransazione);


--
-- TOC entry 3419 (class 2606 OID 7779844)
-- Name: fornitore unumeroditelefono; Type: CONSTRAINT; Schema: public; Owner: nwiojydu
--

ALTER TABLE ONLY public.fornitore
    ADD CONSTRAINT unumeroditelefono UNIQUE (numeroditelefono);


--
-- TOC entry 3403 (class 2606 OID 7784171)
-- Name: descrittorearticolo valoreprezzomagazzino; Type: CHECK CONSTRAINT; Schema: public; Owner: nwiojydu
--

ALTER TABLE public.descrittorearticolo
    ADD CONSTRAINT valoreprezzomagazzino CHECK ((prezzomagazzino >= (0)::double precision)) NOT VALID;


--
-- TOC entry 3426 (class 2620 OID 7789624)
-- Name: composizionetransazione aggiornaquantitaarticolo; Type: TRIGGER; Schema: public; Owner: nwiojydu
--

CREATE TRIGGER aggiornaquantitaarticolo AFTER INSERT ON public.composizionetransazione FOR EACH ROW EXECUTE PROCEDURE public.procedureforaggiornaquantitaarticolo();


--
-- TOC entry 3427 (class 2620 OID 7790640)
-- Name: composizionetransazione calcolavalore; Type: TRIGGER; Schema: public; Owner: nwiojydu
--

CREATE TRIGGER calcolavalore AFTER INSERT ON public.composizionetransazione FOR EACH ROW EXECUTE PROCEDURE public.procedureforcalcolavalore();


--
-- TOC entry 3428 (class 2620 OID 7791551)
-- Name: composizionetransazione calcolavaloretotale; Type: TRIGGER; Schema: public; Owner: nwiojydu
--

CREATE TRIGGER calcolavaloretotale AFTER INSERT ON public.composizionetransazione FOR EACH ROW EXECUTE PROCEDURE public."procedureforcalcolavaloretotaleCT"();


--
-- TOC entry 3430 (class 2620 OID 7792141)
-- Name: composizionetransazione inseriscicodicetransazioneinCT; Type: TRIGGER; Schema: public; Owner: nwiojydu
--

CREATE TRIGGER "inseriscicodicetransazioneinCT" BEFORE INSERT ON public.composizionetransazione FOR EACH ROW EXECUTE PROCEDURE public.procedureforaggiornacodicetransazione();


--
-- TOC entry 3429 (class 2620 OID 7789535)
-- Name: composizionetransazione valorequantitactestremosuperiore; Type: TRIGGER; Schema: public; Owner: nwiojydu
--

CREATE TRIGGER valorequantitactestremosuperiore BEFORE INSERT ON public.composizionetransazione FOR EACH ROW EXECUTE PROCEDURE public.procedureforvalorequantitactestremosuperiore();


--
-- TOC entry 3425 (class 2606 OID 7779911)
-- Name: composizionetransazione fk1composizionetransazione; Type: FK CONSTRAINT; Schema: public; Owner: nwiojydu
--

ALTER TABLE ONLY public.composizionetransazione
    ADD CONSTRAINT fk1composizionetransazione FOREIGN KEY (sku) REFERENCES public.articolo(sku) ON UPDATE CASCADE;


--
-- TOC entry 3424 (class 2606 OID 7779906)
-- Name: composizionetransazione fk2composizionetransazione; Type: FK CONSTRAINT; Schema: public; Owner: nwiojydu
--

ALTER TABLE ONLY public.composizionetransazione
    ADD CONSTRAINT fk2composizionetransazione FOREIGN KEY (codicetransazione) REFERENCES public.transazione(codicetransazione) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3422 (class 2606 OID 7779869)
-- Name: articolo fkarticolo; Type: FK CONSTRAINT; Schema: public; Owner: nwiojydu
--

ALTER TABLE ONLY public.articolo
    ADD CONSTRAINT fkarticolo FOREIGN KEY (codiceabarre) REFERENCES public.descrittorearticolo(codiceabarre) ON UPDATE CASCADE;


--
-- TOC entry 3423 (class 2606 OID 7779916)
-- Name: transazione fktransazione; Type: FK CONSTRAINT; Schema: public; Owner: nwiojydu
--

ALTER TABLE ONLY public.transazione
    ADD CONSTRAINT fktransazione FOREIGN KEY (partitaiva) REFERENCES public.fornitore(partitaiva) ON UPDATE CASCADE;


-- Completed on 2020-04-22 18:55:59

--
-- PostgreSQL database dump complete
--

