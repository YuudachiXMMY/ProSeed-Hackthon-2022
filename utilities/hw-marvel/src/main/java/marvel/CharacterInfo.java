package marvel;

import com.opencsv.bean.CsvBindByName;

/**
 * CharacterInfo represents data presented in the .tsv file
 */
public class CharacterInfo {

    // Representation invariant:
    //      hero and book should not be null

    // Abstraction function:
    //      AF(this) = a CharacterInfo where it contains the a hero name and its book

    /** Binding to the character column in the .tsv file */
    @CsvBindByName
    private String hero;

    /** Binding to the book column in the .tsv file */
    @CsvBindByName
    private String book;

    /**
     * Get the current character.
     *
     * @return String representing the current character
     */
    public String getCharacter() {
        return hero;
    }

    /**
     * Get the books the character appeared in.
     *
     * @return String representing books the character appeared in
     */
    public String getBook() {
        return book;
    }

    /**
     * Set the current character
     *
     * @param character a character needed to be set
     * @spec.requires character is a valid name and is not null
     */
    public void setCharacter(String character) {
        this.hero = character;
    }

    /**
     * Set the books the current character appeared in
     *
     * @param book a book needed to be set
     * @spec.requires book is a valid name and is not null
     */
    public void setBook(String book) {
        this.book = book;
    }
}
