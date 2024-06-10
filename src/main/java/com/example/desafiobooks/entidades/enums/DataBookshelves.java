package com.example.desafiobooks.entidades.enums;


public enum DataBookshelves {
    FICTION_GOTICA ("Gothic Fiction", "Ficción Gótica"),
    PELICULAS_LIBROS("Movie Books", "Películas de libros"),
    CIENCIA_FICCION("Precursors of Science Fiction", "Precursores de la Ciencia Ficción"),
    CIENCIA_FICCION_BYWOMAN ("Science Fiction by Women", "Ciencia Ficción escrita por mujeres"),
    CLASICOS_HARVARD("Harvard Classics", "Clásicos de Harvard"),
    MEJORES_LIBROS("Best Books Ever Listings", "Mejores libros nunca listados"),
    CIENCIA_FICCION_HISTORICA("Historical Fiction", "Ficción histórica"),
    ITALIANOS("Italy", "Italia"),
    JUEGOS("Plays", "Juegos"),
    BEST_SELLERS_AMERICANOS("Bestsellers, American, 1895-1923", "Mejores vendidos, Americanos"),
    BANEADOS("Banned Books from Anne Haight's list", "Libros baneados de Anne Haight's"),
    AVENTURAS("Adventure", "Aventura"),
    LITERATURA_FOR_KIDS("Children's Literature", "Literatura para niños"),
    HORROR("Horror", "Horror"),
    CRIMEN_FICCION("Crime Fiction", "Crimen de ficción"),
    MISTERIO_FICCION("Mystery Fiction", "Misterio de ficción");

    private String bookshelves;
    private String categorieSpanish;

    DataBookshelves(String bookshelves, String categorieSpanish){
        this.bookshelves = bookshelves;
        this.categorieSpanish = categorieSpanish;
    }

    public String getBookshelves() {
        return bookshelves;
    }

    public String getCategorieSpanish() {
        return categorieSpanish;
    }

    public static DataBookshelves getNameOfEnum(String nombre){
        return fromString(nombre);
    }

    public static DataBookshelves fromString(String text){
        for (DataBookshelves shelves: DataBookshelves.values()){
            if(shelves.bookshelves.equalsIgnoreCase(text)){
                return shelves;
            }
        }
        throw new IllegalArgumentException("Niguna categoría encontrada: " + text);
    }

    public static DataBookshelves fromSpanish(String text){
        for (DataBookshelves shelves: DataBookshelves.values()){
            if(shelves.categorieSpanish.equalsIgnoreCase(text)){
                return shelves;
            }
        }
        throw new IllegalArgumentException("Niguna categoría en español encontrada: " + text);
    }
}
