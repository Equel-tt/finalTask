-- Бухгалтер
CREATE USER jannaVasiluk WITH PASSWORD '1111';
GRANT SELECT, INSERT, UPDATE, DELETE ON manufacture.public.archive,
    manufacture.public.arrival,
    manufacture.public.consumption,
    manufacture.public.need TO jannaVasiluk;
-- Снабженцы
CREATE USER alekseyStepanov WITH PASSWORD '2222';
GRANT SELECT ON manufacture.public.* TO alekseyStepanov;

CREATE USER petrKoval WITH PASSWORD '6666';
GRANT SELECT ON manufacture.public.* TO petrKoval;

--Кладовщики
CREATE USER larisaBortnik WITH PASSWORD '6666';
GRANT SELECT, INSERT, UPDATE ON manufacture.public.arrival,
    manufacture.public.consumption TO larisaBortnik;

CREATE USER viacheslavStrochuk WITH PASSWORD '5555';
GRANT SELECT, INSERT, UPDATE ON manufacture.public.arrival,
    manufacture.public.consumption TO viacheslavStrochuk;

--Цеховые
CREATE USER viktorBeresta WITH PASSWORD '4444';
GRANT SELECT, INSERT, UPDATE ON manufacture.public.write_off TO viktorBeresta;

CREATE USER svetlanaGarbar WITH PASSWORD '7777';
GRANT SELECT, INSERT, UPDATE ON manufacture.public.write_off TO svetlanaGarbar;
