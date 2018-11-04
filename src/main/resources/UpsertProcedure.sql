-- FUNCTION: public.add_person_details(character varying, bigint)

-- DROP FUNCTION public.add_person_details(character varying, bigint);

CREATE OR REPLACE FUNCTION public.add_person_details(
	nam character varying,
	ag bigint)
    RETURNS void
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE
AS $BODY$

	declare
    BEGIN
      WITH upsert AS (UPDATE record SET age=ag WHERE name=nam RETURNING *) INSERT INTO record (name, age) SELECT nam, ag WHERE NOT EXISTS (SELECT * FROM upsert);
    END;


$BODY$;

ALTER FUNCTION public.add_person_details(character varying, bigint)
    OWNER TO postgres;
