package com.example.bookshelf.repository;

import com.example.bookshelf.model.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends CrudRepository<Book, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE book " +
            "SET title=:title, " +
            "description=:description, " +
            "isbn=:isbn, " +
            "printyear=:printYear, " +
            "readalready=0 " +
            "WHERE id=:id",
            nativeQuery = true)
    void updateBook(@Param("id") Long id,
                    @Param("title") String title,
                    @Param("description") String description,
                    @Param("isbn") String isbn,
                    @Param("printYear") Integer printYear);

    @Modifying
    @Transactional
    @Query(value = "UPDATE book SET readalready = 1 WHERE id=:id", nativeQuery = true)
    void readBook(@Param("id") Long id);

}
