-- create user foodtruckuser with password 'password';
-- create database foodtruckdb with template=template0 owner=foodtruckuser;

CREATE EXTENSION postgis;

CREATE TABLE trucks
(
  id SERIAL,
  locationid BIGINT,
  applicant TEXT,
  address TEXT,
  fooditems TEXT,
  latitude double precision,
  longitude double precision,
  zipcodes double precision
  -- the_geom geometry,
  -- CONSTRAINT trucks_pkey PRIMARY KEY (id),
  -- CONSTRAINT enforce_dims_the_geom CHECK (st_ndims(the_geom) = 2),
  -- CONSTRAINT enforce_geotype_geom CHECK (geometrytype(the_geom) = 'POINT'::text OR the_geom IS NULL),
  -- CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = 4326)
);

-- CREATE INDEX trucks_the_geom_gist
--   ON trucks
--   USING gist
--   (the_geom );

\copy trucks(locationid,applicant,address,fooditems,latitude,longitude,zipcodes) FROM '/data/test.csv' DELIMITERS ',' CSV HEADER;

-- UPDATE trucks
-- SET the_geom = ST_GeomFromText('POINT(' || longitude || ' ' || latitude || ')',4326);


ALTER TABLE trucks ADD COLUMN geom geometry(Point, 4326);

UPDATE trucks SET geom=st_SetSrid(st_MakePoint(longitude, latitude), 4326);