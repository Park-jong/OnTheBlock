FROM python:3.9-slim

WORKDIR /app

COPY . .

RUN pip install --upgrade pip

RUN pip install -r requirements.txt

RUN mkdir -p /usr/local/lib/nltk_data

COPY ./nltk_data/ /usr/local/lib/nltk_data/

RUN python3 ElasticIndexer.py

CMD ["uvicorn", "app:app", "--host", "0.0.0.0", "--port", "8000"]