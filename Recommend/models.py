from sqlalchemy import Column, TEXT, INT, BIGINT
from sqlalchemy.ext.declarative import declarative_base

Base = declarative_base()

class Song(Base):
    __tablename__ = "song"

    song_id = Column(BIGINT, nullable=False, autoincrement=True, primary_key=True)
    name = Column(TEXT)
