#python 
from multiprocessing import connection
import pandas as pd
import psycopg2
from psycopg2 import Error
from sqlalchemy import create_engine, inspect, text
from sqlalchemy.orm import sessionmaker, scoped_session
from time import sleep


sleep(3)

# Prepare DataFrames from CSVs before insert into postgres DB 
# For the purposes of quickly testing this API - we are only inserting a subset of the Jan taxi data
# Prepare Borough Cab Zone CSV
# data_file = "test.csv"
# food_truck_df = pd.read_csv(data_file)
# # Make all columns standardized lowercase so we don't run into any column key issues with hibernate
# food_truck_df.columns= food_truck_df.columns.str.strip().str.lower()

# print(food_truck_df)


# Configure psycopg2 to connect to local database
param_dic = {
    "host"      : "db",
    "database"  : "postgres",
    "user"      : "postgres",
    "password"  : "postgres"
    # "database"  : "foodtruckdb",
    # "user"      : "foodtruckuser",
    # "password"  : "password"
}

connect = "postgresql+psycopg2://%s:%s@%s:5432/%s" % (
    param_dic['user'],
    param_dic['password'],
    param_dic['host'],
    param_dic['database']
)

# SqlAlchemy Only to insert data
engine = create_engine(connect)
print(connect)

# Push cab data to local postgres in database = taxidatabase 
# food_truck_df.to_sql('trucks', con=engine,if_exists='append')
# print("Food truck data to_sql() completed via sqlalchemy")

print("First row from table")
user_table = pd.read_sql_table(table_name="trucks", con=engine)
print(user_table.head(1))
print(user_table.shape)


print("get table metadata")
print("..")
insp = inspect(engine)

columns_table = insp.get_columns("trucks") #schema is optional

for c in columns_table :
    print(c['name'], c['type'])


statement3 = text("SELECT applicant FROM trucks ORDER BY geom <-> ST_GeomFromText ('POINT(-122.4273064 37.7620192)', 4326) LIMIT 5;")
# statement = text("SELECT * FROM trucks WHERE longitude=-122.4041544")
print("testing query by lat, long")
Session = scoped_session(sessionmaker(bind=engine))
s= Session()
result = s.execute(statement3)
print(result.first()[0])

statement = text("SELECT applicant from trucks WHERE locationid=1336737")
# statement = text("SELECT * FROM trucks WHERE longitude=-122.4041544")
print("testing query")
Session = scoped_session(sessionmaker(bind=engine))
s= Session()
result = s.execute(statement)
print(result.first()[0])

statement2 = text("SELECT ST_Distance(ST_GeomFromText('POINT(-122.4273064 37.7620192)', 4326), trucks.the_geom) AS planar_degrees, applicant FROM trucks ORDER BY planar_degrees ASC LIMIT 5;")
# statement = text("SELECT * FROM trucks WHERE longitude=-122.4041544")
print("testing geom")
Session = scoped_session(sessionmaker(bind=engine))
s= Session()
result = s.execute(statement2)
print(result.first()[0])