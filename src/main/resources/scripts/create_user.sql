-- Бухгалтер
CREATE USER jannaVasiluk WITH PASSWORD '$2a$10$gKcGkeAWdp3KftVYY9rTWuCkQe8R.T3KDqfFaRcxNNqeUKkdD4oFK';
GRANT SELECT, INSERT, UPDATE, DELETE ON manufacture.public.archive,
    manufacture.public.arrival,
    manufacture.public.consumption,
    manufacture.public.need TO jannaVasiluk;
-- Снабженцы
CREATE USER alekseyStepanov WITH PASSWORD '$2a$10$5CF0djvzehDbdWQC3CUBPOVvIWNwYgcGJma572r05sS5rpIAOeRRy';
GRANT SELECT ON manufacture.public.* TO alekseyStepanov;

CREATE USER petrKoval WITH PASSWORD '$2a$10$pQYIVcxmTlUIgjBGQtR4a.zlbr.cjR3XRf/UL5/YWN/XCOyRxghr6';
GRANT SELECT ON manufacture.public.* TO petrKoval;

--Кладовщики
CREATE USER larisaBortnik WITH PASSWORD '$2a$10$PeJIXerWZwWD3umyUdAAwOFJhKJcXe37MCA8HnS.aCL.B1PXF54vq';
GRANT SELECT, INSERT, UPDATE ON manufacture.public.arrival,
    manufacture.public.consumption TO larisaBortnik;

CREATE USER viacheslavStrochuk WITH PASSWORD '$2a$10$3XbfbigKBK6IWQ2IpMem7eRe.PSaaaabe8J1.Q/kYY6BDqieR8bo.';
GRANT SELECT, INSERT, UPDATE ON manufacture.public.arrival,
    manufacture.public.consumption TO viacheslavStrochuk;

--Цеховые
CREATE USER viktorBeresta WITH PASSWORD '$2a$10$q3SZ9RVQ0kWFCfGldv2hQ.zfOyWwXRpV/X78B7TBrEN/Texd0mBIm';
GRANT SELECT, INSERT, UPDATE ON manufacture.public.write_off TO viktorBeresta;

CREATE USER svetlanaGarbar WITH PASSWORD '$2a$10$MkJGwtwViIhV25w4OTsod.iXB4l02EsNn56Gnu5o10o45x2SeLgC2';
GRANT SELECT, INSERT, UPDATE ON manufacture.public.write_off TO svetlanaGarbar;
