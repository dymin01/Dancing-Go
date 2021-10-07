FROM python:3.8
RUN apt-get update 
RUN apt-get -y install libgl1-mesa-glx

RUN pip install django


WORKDIR /usr/src/app

COPY . .

WORKDIR /usr/src/app/ai_back

RUN pip install -r requirement.txt

RUN pip install django-sslserver

EXPOSE 8000
CMD ["python3", "manage.py", "runsslserver", "0.0.0.0:8000", "--certificate", "/usr/src/app/ai_back/sslkey/fullchain.pem", "--key", "/usr/src/app/ai_back/sslkey/privkey.pem"]
# RUN python3 manage.py runsslserver 0.0.0.0:8000 --noreload --certificate /usr/src/app/ai_back/sslkey/fullchain.pem --key /usr/src/app/ai_back/sslkey/privkey.pem

