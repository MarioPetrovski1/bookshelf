--
-- PostgreSQL database dump
--

-- Dumped from database version 12.6 (Ubuntu 12.6-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.6 (Ubuntu 12.6-0ubuntu0.20.04.1)

-- Started on 2021-03-05 13:49:31 CET

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
-- TOC entry 202 (class 1259 OID 33075)
-- Name: authors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.authors (
    id integer NOT NULL,
    first_name character varying(100) NOT NULL,
    last_name character varying(100) NOT NULL
);


ALTER TABLE public.authors OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 33080)
-- Name: book_authors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.book_authors (
    book_id integer NOT NULL,
    author_id integer NOT NULL
);


ALTER TABLE public.book_authors OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 33083)
-- Name: book_publisher; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.book_publisher (
    publisher_id integer,
    book_id integer NOT NULL
);


ALTER TABLE public.book_publisher OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 33088)
-- Name: books; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.books (
    id integer NOT NULL,
    category character varying(255),
    file character varying(255),
    isbn character varying(100) NOT NULL,
    title character varying(100) NOT NULL
);


ALTER TABLE public.books OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 33103)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 33096)
-- Name: publishers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.publishers (
    id integer NOT NULL,
    country character varying(100) NOT NULL,
    name character varying(100) NOT NULL
);


ALTER TABLE public.publishers OWNER TO postgres;

--
-- TOC entry 2850 (class 2606 OID 33079)
-- Name: authors authors_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authors
    ADD CONSTRAINT authors_pkey PRIMARY KEY (id);


--
-- TOC entry 2852 (class 2606 OID 33087)
-- Name: book_publisher book_publisher_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_publisher
    ADD CONSTRAINT book_publisher_pkey PRIMARY KEY (book_id);


--
-- TOC entry 2854 (class 2606 OID 33095)
-- Name: books books_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (id);


--
-- TOC entry 2856 (class 2606 OID 33100)
-- Name: publishers publishers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.publishers
    ADD CONSTRAINT publishers_pkey PRIMARY KEY (id);


--
-- TOC entry 2858 (class 2606 OID 33102)
-- Name: publishers uk_an1ucpx8sw2qm194mlok8e5us; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.publishers
    ADD CONSTRAINT uk_an1ucpx8sw2qm194mlok8e5us UNIQUE (name);


--
-- TOC entry 2862 (class 2606 OID 33120)
-- Name: book_publisher fk6o0qiydt51w8qxu05ph8fugl5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_publisher
    ADD CONSTRAINT fk6o0qiydt51w8qxu05ph8fugl5 FOREIGN KEY (book_id) REFERENCES public.books(id);


--
-- TOC entry 2861 (class 2606 OID 33115)
-- Name: book_publisher fk7k3x35mgj9l58o8tugbuc9fy8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_publisher
    ADD CONSTRAINT fk7k3x35mgj9l58o8tugbuc9fy8 FOREIGN KEY (publisher_id) REFERENCES public.publishers(id);


--
-- TOC entry 2860 (class 2606 OID 33110)
-- Name: book_authors fkbhqtkv2cndf10uhtknaqbyo0a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_authors
    ADD CONSTRAINT fkbhqtkv2cndf10uhtknaqbyo0a FOREIGN KEY (book_id) REFERENCES public.books(id);


--
-- TOC entry 2859 (class 2606 OID 33105)
-- Name: book_authors fko86065vktj3hy1m7syr9cn7va; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_authors
    ADD CONSTRAINT fko86065vktj3hy1m7syr9cn7va FOREIGN KEY (author_id) REFERENCES public.authors(id);


-- Completed on 2021-03-05 13:49:31 CET

--
-- PostgreSQL database dump complete
--

