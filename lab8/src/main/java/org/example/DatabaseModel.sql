create table Artists(
id int not null primary key,
name varchar(256)
);

create table Albums(
id int not null primary key,
release_year int,
title varchar(256),
id_artist int
);

create table Genres(
id int not null primary key,
name varchar(256)
);

create table Album_Genres(
album_id int,
genre_id int,
primary key (album_id, genre_id),
foreign key (album_id) references albums(id),
foreign key (genre_id) references genres(id)
);