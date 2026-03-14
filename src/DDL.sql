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
(seriesName VARCHAR (50) not null primary key,
 partNumber INT not null);

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
language VARCHAR (50) not null,
seriesName VARCHAR (50) null,
foreign key (itemType) references itemType (type),
foreign key (seriesName) references seriesInfo (seriesName),
foreign key (language) references language (language));

create table visualmedia
(itemId int not null primary key,
director Varchar (50) null,
mediaFormat Varchar (50) not null,
translationInfo Varchar (50) null,
foreign key (itemId) references libraryitem (itemId),
foreign key (mediaFormat) references mediaFormat (format),
foreign key (translationInfo) references translationInfo (translationTypeOrLanguage));



create table film
(itemId int not null primary key,
filmType VARCHAR (50) not null,
foreign key (itemId) references visualmedia (itemId),
foreign key (filmType) references filmType (type));


create table book 
(itemId int not null primary key,
fanficType VARCHAR (50) null,
bookFormat VARCHAR (50) not null,
foreign key (itemId) references libraryitem (itemId),
foreign key (fanficType) references fanficType (type),
foreign key (bookFormat) references bookFormat (bformat));

create table game
(itemId int not null primary key,
creator VARCHAR (50) null,
foreign key (itemId) references libraryitem (itemId));

create table tvseries
(itemId int not null primary key,
foreign key (itemId) references visualmedia (itemId));

create table visualmedia_actors
(visualmediaId int not null ,
actorName Varchar (50) not null,
primary key (visualmediaId, actorName),
foreign key (visualmediaId) references visualmedia(itemId));

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

CREATE TABLE item_genres
(itemId INT NOT NULL,
 genre VARCHAR(50) NOT NULL,
 PRIMARY KEY (itemId, genre),
 FOREIGN KEY (itemId) REFERENCES libraryitem(itemId),
 FOREIGN KEY (genre) REFERENCES genre(genre));

CREATE TABLE book_fandoms
(bookId INT NOT NULL,
 fandom VARCHAR(100) NOT NULL,
 PRIMARY KEY (bookId, fandom),
 FOREIGN KEY (bookId) REFERENCES book(itemId),
 FOREIGN KEY (fandom) REFERENCES fandom(fandom));
  
SELECT * FROM itemType;
SELECT * FROM mediaFormat;
SELECT * FROM fanficType;
SELECT * FROM filmType;
SELECT * FROM bookFormat;
SELECT * FROM seriesInfo;
SELECT * FROM translationInfo;
SELECT * FROM libraryitem;
SELECT * FROM visualmedia;
SELECT * FROM film;
SELECT * FROM book;
SELECT * FROM game;
SELECT * FROM tvseries;
SELECT * FROM visualmedia_actors;
SELECT * FROM Book_Authors;
SELECT * FROM season;
SELECT * FROM episode;
SELECT * FROM fandom;
SELECT * FROM genre;
SELECT * FROM language;
SELECT * FROM item_genres;
SELECT * FROM book_fandoms;