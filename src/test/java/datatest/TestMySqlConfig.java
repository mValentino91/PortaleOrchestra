package datatest;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import stubs.Table1Repository;
import test.entities.Table1;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author mekko
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/test/resources/testContext.xml")
public class TestMySqlConfig {

    @Autowired
    Table1Repository repository;

    Table1 entry;

    @Before
    public void setUp() {
        entry = new Table1();
        //Set random string values
        entry.setCol1(RandomStringUtils.randomAlphabetic(30));
        entry.setCol2(RandomStringUtils.randomAlphabetic(30));
    }

    @Test
    public void testInsert() {
        //Save new entry
        repository.save(entry);

        //Retrieve entry
        Table1 outEntry = repository.findOne(entry.getId());

        //Check that outEntry exist
        assertNotNull("outEntry is null", outEntry);

        //Check values
        assertEquals(entry.getCol1(), outEntry.getCol1());
        assertEquals(entry.getCol2(), outEntry.getCol2());
    }

    @Test
    public void testUpdate() {
        String value;
        //Save new entry
        repository.save(entry);

        //Set a new value to col1
        do {
            value = RandomStringUtils.randomAlphabetic(30);
        } while (value.equals(entry.getCol1()));
        entry.setCol1(value);

        //Update entry
        repository.save(entry);

        //Retrieve entry
        Table1 outEntry = repository.findOne(entry.getId());

        //Check that outEntry exist
        assertNotNull(outEntry);

        //Check col1 value
        assertEquals(value, outEntry.getCol1());
    }
    
    @Test
    public void testDelete() {
        //Save new entry
        repository.save(entry);
        
        //Delete entry
        repository.delete(entry.getId());
        
        //Try to retrieve deleted entry
        Table1 outEntry = repository.findOne(entry.getId());
        
        //Check null
        assertNull(outEntry);
    }
}
