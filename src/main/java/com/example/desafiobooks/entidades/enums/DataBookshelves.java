package com.example.desafiobooks.entidades.enums;


public enum DataBookshelves {
    FICTION_GOTICA ("Gothic Fiction"),
    PELICULAS_LIBROS("Movie Books"),
    CIENCIA_FICCION("Precursors of Science Fiction"),
    CIENCIA_FICCION_BYWOMAN ("Science Fiction by Women"),
    CLASICOS_HARVARD("Harvard Classics"),
    MEJORES_LIBROS("Best Books Ever Listings"),
    CIENCIA_FICCION_HISTORICA("Historical Fiction"),
    ITALIANOS("Italy"),
    JUEGOS("Plays"),
    BEST_SELLERS_AMERICANOS("Bestsellers, American, 1895-1923"),
    BANEADOS("Banned Books from Anne Haight's list"),
    AVENTURAS("Adventure"),
    LITERATURA_FOR_KIDS("Children's Literature"),
    CRIMEN_FICCION("Crime Fiction");

    private String bookshelves;

    DataBookshelves(String bookshelves){
        this.bookshelves = bookshelves;
    }

    public String getBookshelves() {
        return bookshelves;
    }

    public static DataBookshelves getNameOfEnum(String nombre){
        try{
            return  DataBookshelves.valueOf(nombre);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("No se encontró la categoría: " + nombre);

        }
    }

    public static DataBookshelves fromString(String text){
        for (DataBookshelves shelves: DataBookshelves.values()){
            if(shelves.bookshelves.equalsIgnoreCase(text)){
                return shelves;
            }
        }
        throw new IllegalArgumentException("Niguna categoría encontrada: " + text);
    }
}
