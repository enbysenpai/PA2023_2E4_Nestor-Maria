CREATE TABLE playlists(
id int not null primary key,
name varchar(256) not null,
creation_timestamp timestamp default current_timestamp
);

CREATE TABLE playlist_albums(
playlist_id int not null,
album_id int not null,
primary key (playlist_id,album_id),
foreign key (album_id) references albums(id),
foreign key (playlist_id) references playlists(id)
);

drop table playlis_albums;
drop table playlists;