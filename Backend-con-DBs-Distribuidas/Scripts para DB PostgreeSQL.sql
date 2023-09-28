--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: alumnos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.alumnos (
    id bigint NOT NULL,
    apellido character varying(255),
    create_at timestamp(6) without time zone,
    email character varying(255),
    foto oid,
    nombre character varying(255)
);


ALTER TABLE public.alumnos OWNER TO postgres;

--
-- Name: alumnos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.alumnos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.alumnos_id_seq OWNER TO postgres;

--
-- Name: alumnos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.alumnos_id_seq OWNED BY public.alumnos.id;


--
-- Name: alumnos id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alumnos ALTER COLUMN id SET DEFAULT nextval('public.alumnos_id_seq'::regclass);


--
-- Data for Name: alumnos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.alumnos (id, apellido, create_at, email, foto, nombre) FROM stdin;
1	Borja	2023-09-17 22:11:38	mborja@gmail.com	\N	Miguel
2	Maradona	2023-09-17 22:11:38	maradona@gmail.com	\N	Diego
3	Solari	2023-09-17 22:11:38	psolari@gmail.com	\N	Pablo
4	Diaz	2023-09-17 22:11:38	pdiaz@gmail.com	\N	Paulo
5	Suarez	2023-09-17 22:11:38	msuarez@gmail.com	\N	Matias
7	Lopez	2023-09-17 22:11:38	mlopez@gmail.com	\N	Lorena
8	Gomez	2023-09-17 22:11:38	fgomez@gmail.com	\N	Fernanda
9	Zalazar	2023-09-17 22:11:38	luli@gmail.com	\N	Luciana
10	Mendel	2023-09-17 22:11:38	pmendel@gmail.com	\N	Pedro
11	Messi	2023-09-17 22:11:38	pulga@gmail.com	\N	Lionel
12	Perez	2023-09-17 22:11:38	enzop@gmail.com	\N	Enzo
13	Fernández	2023-09-17 22:11:38	fern@gmail.com	\N	Alberto
\.


--
-- Name: alumnos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.alumnos_id_seq', 13, true);


--
-- Name: alumnos alumnos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alumnos
    ADD CONSTRAINT alumnos_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

