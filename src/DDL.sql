drop DATABASE if EXISTS archive;
create database archive;
use archive;

create table itemType
(type VARCHAR (50) not null primary key);

create table mediaFormat 
(format VARCHAR (50) not null primary key);

create table fanficType
(type VARCHAR (50) not null primary key);

create table filmType
(type VARCHAR (50) not null primary key);

create table seriesInfo
(seriesName VARCHAR (50) not null primary key);

create table bookFormat
(bFormat VARCHAR (50) not null primary key);

create table translationInfo
(translationTypeOrLanguage VARCHAR (50) not null primary key);

create table genre
(genre VARCHAR (50)not null primary key);

create table language
(language VARCHAR (50) not null primary key);

create table fandom
(fandom VARCHAR (50) not null primary key);

create table libraryitem
(itemId int not null auto_increment primary key,
title VARCHAR (100) not null,
itemType VARCHAR (50) not null,
genre VARCHAR (50) not null,
language VARCHAR (50) not null,
seriesName VARCHAR (50) null,
foreign key (itemType) references itemType (type),
foreign key (seriesName) references seriesInfo (seriesName),
foreign key (genre) references genre (genre),
foreign key (language) references language (language));


create table film
(itemId int not null primary key,
director VARCHAR (50) null,
filmType VARCHAR (50) not null,
mediaFormat VARCHAR (50) not null,
translationInfo VARCHAR (50) null,
foreign key (itemId) references libraryitem (itemId),
foreign key (filmType) references filmType (type),
foreign key (mediaFormat) references mediaFormat (format),
foreign key  (translationInfo) references translationInfo (translationTypeOrLanguage));


create table book 
(itemId int not null primary key,
fandom VARCHAR (100) null,
fanficType VARCHAR (50) null,
bookFormat VARCHAR (50) not null,
foreign key (itemId) references libraryitem (itemId),
foreign key (fanficType) references fanficType (type),
foreign key (bookFormat) references bookFormat (bformat),
foreign key (fandom) references fandom (fandom));


create table game
(itemId int not null primary key,
creator VARCHAR (50) null,
foreign key (itemId) references libraryitem (itemId));

create table tvseries
(itemId int not null primary key,
foreign key (itemId) references libraryitem (itemId));

create table film_actors
(filmId int not null ,
actorName Varchar (50) not null,
primary key (filmId, actorName),
foreign key (filmId) references film (ItemId));


CREATE TABLE Book_Authors
(bookId INT NOT NULL,
  authorName VARCHAR(100) NOT NULL,
  PRIMARY KEY (bookId, authorName),
  FOREIGN KEY (bookId) REFERENCES Book(itemId));
  
  create table season
  (tvseriesId int not null,
  seasonNumber int not null,
  primary key (tvseriesId, seasonNumber),
  foreign key (tvseriesId) references tvseries (itemId));
  
  create table episode
  (tvseriesId int not null,
  seasonNumber int not null,
  episodeNumber int not null,
  episodeName VARCHAR (50) null,
  primary key (tvseriesId, seasonNumber, episodeNumber),
  foreign key (tvseriesId, seasonNumber) references season (tvseriesId, seasonNumber));
  
SELECT * FROM itemType;
SELECT * FROM mediaFormat;
SELECT * FROM fanficType;
SELECT * FROM filmType;
SELECT * FROM seriesInfo;
SELECT * FROM bookFormat;
SELECT * FROM translationInfo;
SELECT * FROM libraryitem;
SELECT * FROM film;
SELECT * FROM book;
SELECT * FROM game;
SELECT * FROM tvseries;
SELECT * FROM film_actors;
SELECT * FROM Book_Authors;
SELECT * FROM season;
SELECT * FROM episode;
SELECT * FROM fandom;
SELECT * FROM genre;
SELECT * FROM language;