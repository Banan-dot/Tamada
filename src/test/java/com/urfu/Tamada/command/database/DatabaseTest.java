package com.urfu.Tamada.command.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    @Test
    void getAnecdoteById() {
        var anek = new Database().getAnecdoteById(10);
        assertNotEquals(anek, "");
    }

    @Test
    void getAnecdoteNotEmpty() {
        var anek = "Учительница: \\n- Дети угадайте, как зовут всем известного персонажа, начинающегося " +
                "\\nна букву \"Б\", живущего в стране дураков, который всюду совал свой \\nдлиный нос? " +
                "\\n- Березовский! - дружно кричат дети.";
        var databaseAnek = new Database().getAnecdoteById(10);
        assertEquals(anek, databaseAnek);
    }
}