package ru.agrage.project.DAO;

/**
 * Created by dmitry on 1/4/17.
 */
public interface AbstractDAO <PK, T>
{
    // Save object
    void create (T o);
    // Delete object
    void delete (T o);
    // Match
}
