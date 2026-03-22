

insert into itemType (type)
	values ('Bok'), ('Film'), ('Spel'), ('TV-serie');
	
insert into filmType (type)
	values ('Film'), ('TV-serie'), ('Annat');

insert into mediaFormat (format)
	values ('Digitalt'), ('DVD'), ('Blu-ray');
	
insert into bookFormat (bFormat)
	values ('Bok'), ('Ebook'), ('Ljudbok'), ('Fanfiction');

insert into fanficType (type)
	values ('Canon'), ('AU'), ('Über'), ('Original/Okänt');

insert into translationinfo (translationTypeOrLanguage)
    values ('Svenska'), ('Engelska'), ('Spanska');

ALTER TABLE libraryitem
    ADD UNIQUE (title, itemType, language);

ALTER TABLE libraryitem
    ADD UNIQUE (title, itemType, language);

Alter Table libraryitem
    add publishYear int null;

Alter Table libraryitem
    drop Index title,
    add UNIQUE (title , itemType, language, publishYear);