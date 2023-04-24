package org.example;

public class Album
{
    private int id;
    private int releaseYear;
    private String title;
    private int artistId;

    public Album(int id,int releaseYear,String title,int artistId)
    {
        this.id=id;
        this.releaseYear=releaseYear;
        this.title=title;
        this.artistId=artistId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString()
    {
        StringBuilder sb=new StringBuilder();
        sb.append("ALBUM: ");
        sb.append("id=").append(id);
        sb.append(" release year=").append(releaseYear);
        sb.append(" title=").append(title);
        sb.append(" artist id=").append(artistId);
        return sb.toString();
    }
}

