/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stubs;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author mekko
 */
@Document(collection = "poitest")
public class PoiTest {

    @Id
    private String id;
    private String name;
    private String description;

    private List<AbstractTestComponent> components;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AbstractTestComponent> getComponents() {
        return components;
    }

    public void setComponents(List<AbstractTestComponent> components) {
        this.components = components;
    }

}
