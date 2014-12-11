/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datatest;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang.RandomStringUtils;
import stubs.TestDocument;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import stubs.AbstractTestComponent;
import stubs.Test1Component;
import stubs.Test2Component;
import stubs.PoiTest;

/**
 *
 * @author mekko
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/test/resources/testContext.xml")
public class TestMongoConfig {

    @Autowired
    MongoOperations mongoOps;

    TestDocument document;

    @Before
    public void setUp() {
        //Create new document
        document = new TestDocument();
        //Set random values
        document.setAtt1(RandomStringUtils.randomAlphabetic(20));
        document.setAtt2(RandomStringUtils.randomAlphabetic(20));
    }

    @Test
    public void testInsert() {
        //Insert new document
        mongoOps.save(document);

        //Retrieve document
        TestDocument outDocument = mongoOps.findById(document.getId(),
                TestDocument.class);

        //Check not null
        assertNotNull(outDocument);

        //Check values
        assertEquals(document.getAtt1(), outDocument.getAtt1());
        assertEquals(document.getAtt2(), outDocument.getAtt2());
    }

    @Test
    public void testUpdate() {
        String value;

        //Insert new document
        mongoOps.save(document);

        //Set a new random value to att1
        do {
            value = RandomStringUtils.randomAlphabetic(20);
        } while (value.equals(document.getAtt1()));
        document.setAtt1(value);

        //Update document
        mongoOps.save(document);

        //Retrieve document
        TestDocument outDocument = mongoOps.findById(document.getId(),
                TestDocument.class);

        //Check not null
        assertNotNull(outDocument);

        //Check value
        assertEquals(value, outDocument.getAtt1());
    }

    @Test
    public void testDelete() {
        //Insert new document
        mongoOps.save(document);

        //Delete document
        mongoOps.remove(document);

        //Try to retrieve deleted document
        TestDocument outDocument = mongoOps.findById(document.getId(),
                TestDocument.class);

        //Check null
        assertNull(outDocument);
    }

    @Test
    public void testOnEmbeddedDocumentWithDifferentStructure() {
        //Create new Poi
        PoiTest poi = new PoiTest();
        //Set random values
        poi.setName(RandomStringUtils.randomAlphabetic(10));
        poi.setDescription(RandomStringUtils.randomAlphabetic(10));

        //Create components list
        List<AbstractTestComponent> components
                = new LinkedList<AbstractTestComponent>();

        //Create components
        Test1Component componentTest1 = new Test1Component();
        Test2Component componentTest2 = new Test2Component();
        //Set random values
        componentTest1.setTitle(RandomStringUtils.randomAlphabetic(10));
        componentTest2.setName(RandomStringUtils.randomAlphabetic(10));
        componentTest2.setSurname(RandomStringUtils.randomAlphabetic(10));

        //Add components to component list
        components.add(componentTest2);
        components.add(componentTest1);

        //Add components to Poi
        poi.setComponents(components);

        //Insert Poi in database
        mongoOps.save(poi);
        
        
        //Retrieve Poi from database
        PoiTest outPoi = mongoOps.findById(poi.getId(), PoiTest.class);

        //Check not null
        assertNotNull(outPoi);

        //Get components
        List<AbstractTestComponent> outComponents = outPoi.getComponents();
        
        //Check not null
        assertNotNull(outComponents);
        //Check size
        assertEquals(components.size(), outComponents.size());
        
        //Check runtime type of single components
        for (AbstractTestComponent comp : outComponents) {
            //Check slug
            assertNotNull(comp.slug());
            
            //Execute a cast based on slug value
            if ("test_com1".equals(comp.slug())) {
                //Check runtime type
                assertTrue(comp instanceof Test1Component);
                
                //Cast comp to Test1Component using reflection
                Test1Component casted = Test1Component.class.cast(comp);
                
                //Check values
                assertEquals(componentTest1.getTitle(), casted.getTitle());
                
            } else if ("test_com2".equals(comp.slug())) {
                //Check runtime type
                assertTrue(comp instanceof Test2Component);
                
                //Cast comp to Test2Component using explicit casting
                Test2Component casted = (Test2Component) comp;
                
                //Check values
                assertEquals(componentTest2.getName(), casted.getName());
                assertEquals(componentTest2.getSurname(), casted.getSurname());
                
            } else {
                fail("unknown slug");
            }
        }
    }
}
