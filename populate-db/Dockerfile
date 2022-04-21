FROM python:latest

# Any working directory can be chosen as per choice like '/' or '/home' etc
# i have chosen /usr/app/src
WORKDIR /app

#to COPY the remote file at working directory in container
COPY populate_data.py ./
COPY test.csv ./
COPY requirements.txt requirements.txt

RUN pip3 install -r requirements.txt

CMD [ "python", "./populate_data.py"]